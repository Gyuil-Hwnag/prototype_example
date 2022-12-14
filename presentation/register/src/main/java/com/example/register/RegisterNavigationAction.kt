package com.example.register


sealed class RegisterNavigationAction {
    class NavigateToLogin(val id: String, val password: String): RegisterNavigationAction()
    object NavigateToRegister: RegisterNavigationAction()
}