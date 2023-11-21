package com.riti.data.dto


import com.fasterxml.jackson.annotation.JsonProperty
import com.riti.data.enums.ResultCode

data class ApiResponse<T>(
    @JsonProperty("data")
    var data: T,
    @JsonProperty("resultCode")
    var resultCode: String,
    @JsonProperty("resultMessage")
    var resultMessage: String
) {
    companion object {
        fun <T> ok(data: T): ApiResponse<T> {
            return ApiResponse(data, ResultCode.SUCCESS.resultCode, ResultCode.SUCCESS.resultMessage)
        }
    }
}

//data class ProfileWrapper(@JsonProperty("profile") val content: ProfileView)

data class CommentWrapper<T>(@JsonProperty("comment") val content: T)

data class ArticleWrapper<T>(@JsonProperty("article") val content: T)

//fun <T> T.toUserWrapper() = UserWrapper(this)

//fun ProfileView.toProfileWrapper() = ProfileWrapper(this)

fun <T> T.toCommentWrapper() = CommentWrapper(this)

fun <T> T.toArticleWrapper() = ArticleWrapper(this)