package com.example.detail


sealed class DetailNavigationAction {
    object NavigateToOverCount: DetailNavigationAction()
    object NavigateToInvite: DetailNavigationAction()
}