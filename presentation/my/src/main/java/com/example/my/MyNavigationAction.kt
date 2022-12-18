package com.example.my


sealed class MyNavigationAction {
    object NavigateToLogout: MyNavigationAction()
    object NavigateToDelete: MyNavigationAction()
    object NavigateToSend: MyNavigationAction()
}