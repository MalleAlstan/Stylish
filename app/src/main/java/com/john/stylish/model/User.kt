package com.john.stylish.model

data class User(

    val id: Int,
    val provider: String,
    val name: String,
    val email: String,
    val picture: String
)