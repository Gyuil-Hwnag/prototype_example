package com.example.model

data class User(
    val userType: UserType,
    val id: String,
    val password: String,
    val birthDay: String
)

enum class UserType(type: String) {
    CHILDREN("child"), TEENAGER("teen"), ADULT("adu")
}