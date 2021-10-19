package com.signaltekno.data.remote

import com.signaltekno.data.remote.dto.HttpRoutes
import com.signaltekno.data.remote.dto.PostRequest
import com.signaltekno.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.get
import io.ktor.client.request.*
import io.ktor.http.*
import java.lang.Exception

class PostServiceImpl(
    private val client: HttpClient
): PostService {
    override suspend fun getPosts(): List<PostResponse> {
        return try {
            client.get {
                url(HttpRoutes.POST_URL)
            }
        }catch(e: RedirectResponseException){
            // Error code 3xx
            println("Error: ${e.response.status.description}")
            emptyList()
        }catch(e: ClientRequestException){
            // Error code 4xx
            println("Error: ${e.response.status.description}")
            emptyList()
        }catch(e: ServerResponseException){
            // Error code 5xx
            println("Error: ${e.response.status.description}")
            emptyList()
        }catch (e: Exception){
            println("Error ${e.message}")
            emptyList()
        }
    }

    override suspend fun createPost(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse>() {
                url(HttpRoutes.POST_URL)
                contentType(ContentType.Application.Json)
                body = postRequest
            }
        }catch(e: RedirectResponseException){
            // Error code 3xx
            println("Error: ${e.response.status.description}")
            null
        }catch(e: ClientRequestException){
            // Error code 4xx
            println("Error: ${e.response.status.description}")
            null
        }catch(e: ServerResponseException){
            // Error code 5xx
            println("Error: ${e.response.status.description}")
            null
        }catch (e: Exception){
            println("Error ${e.message}")
            null
        }
    }
}