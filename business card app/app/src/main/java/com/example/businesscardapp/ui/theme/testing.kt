package com.example.businesscardapp.ui.theme

fun main() {
    val z=10
    val t=12
    val x=trick(z,t)
    println(x)
}

val trick:(Int,Int) -> (Int) = { x,y ->
    println("No treats!")
    10
}