package com.signaltekno.data.remote

import com.signaltekno.data.remote.dto.PostRequest
import com.signaltekno.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*

interface PostService {
    suspend fun getPosts() : List<PostResponse>
    suspend fun createPost(postRequest: PostRequest): PostResponse?

    companion object {
        fun createPostService() = PostServiceImpl(HttpClient(Android){
            install(Logging){
                level = LogLevel.ALL
            }
            install(JsonFeature){
                serializer = KotlinxSerializer()
            }
        })
    }
}