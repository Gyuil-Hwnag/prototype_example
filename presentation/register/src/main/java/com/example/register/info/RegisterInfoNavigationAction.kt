package com.example.register.info


sealed class RegisterInfoNavigationAction {
    object NavigateToPasswordNotMatched: RegisterInfoNavigationAction()
    object NavigateToEmpty: RegisterInfoNavigationAction()
    class NavigateToSuccess(val email: String, val password: String): RegisterInfoNavigationAction()
}