package com.example.launch


sealed class LaunchNavigationAction {
    class NavigateToLogin(val id: String, val password: String): LaunchNavigationAction()
    object NavigateToRegister: LaunchNavigationAction()
    object NavigateToEmpty: LaunchNavigationAction()
}