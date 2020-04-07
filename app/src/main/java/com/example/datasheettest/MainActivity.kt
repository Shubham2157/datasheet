package com.example.datasheettest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val context = this

        var db = Database_handler(context)

        button.setOnClickListener({
            if (text1.text.toString().length > 0 && text2.text.toString().length > 0 && text3.text.toString().length > 0 && text4.text.toString().length > 0)
            {
                var user = User(
                    text1.text.toString(),
                    text2.text.toString().toInt(),
                    text3.text.toString().toInt(),
                    text4.text.toString().toInt()
                     )


                 db.insertdData(user)
            }

            else
            {
                Toast.makeText(context, "Please Fill All Data", Toast.LENGTH_SHORT).show()
            }
        })


        btn_read.setOnClickListener({
            var data = db.readData()
            tvResult.text =""
            for (i in 0..(data.size-1)){
                tvResult.append(data.get(i).membership.toString() + " " + data.get(i).name + " " + data.get(i).openingLoan + " " + data.get(i).repayment + "\n")
            }
        })


        btn_update.setOnClickListener({
            db.updateData()
            btn_read.performClick()
        })

        btn_delete.setOnClickListener({
            db.deletedata()
            btn_read.performClick()
        })
    }

}

