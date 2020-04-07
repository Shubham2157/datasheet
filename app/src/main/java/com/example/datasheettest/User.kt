package com.example.datasheettest

class User{

    var name : String = ""
    var membership : Int = 0
    var openingLoan : Int = 0
    var repayment : Int = 0

    constructor(name:String,membership:Int,openingLoan:Int,repayment:Int)
    {
        this.name = name
        this.membership = membership
        this.openingLoan = openingLoan
        this.repayment = repayment
    }

    constructor()
    {
    }

}