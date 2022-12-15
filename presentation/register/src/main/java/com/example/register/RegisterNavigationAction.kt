package com.example.register


sealed class RegisterNavigationAction {
    object NavigateToAdult: RegisterNavigationAction()
    object NavigateToChild: RegisterNavigationAction()
    object NavigateToEnterprise: RegisterNavigationAction()
}