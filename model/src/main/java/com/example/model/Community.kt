package com.example.model

data class Community(
    val communityId: Int,
    val userName: String,
    val contents: String,
    val dateTime: String,
    val replyList: List<reply>
)

data class reply(
    val replyId: Int,
    val replyContents: String,
    val dateTime: String
)