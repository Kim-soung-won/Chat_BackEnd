package com.kkk.kotlinapp.상속

class Dog (name:String, age:Int) : Animal(name,age, "개"){

    fun say(){
        println("나야나 $name $type 이라고!! ")
    }

    override fun introduce() {
        print("개가 소개합니다.")
        Counter.up()
    }
}

object Counter {
    var count: Int = 0

    fun up(){
        count++
    }
    fun down(){
        count--
    }
}