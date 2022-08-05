package com.example.hw_14.Model

import com.squareup.moshi.Json
import java.io.Serializable


data class LikusData(
    val content: List<Content>
) {
    data class Content(
        val id: String?,
        val descriptionKA: String?,
        val titleKA: String?,
        val cover: String?,
        @Json(name = "publish_date")
        val publishDate: String?,
        ):Serializable
}