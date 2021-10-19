package com.signaltekno.ktorandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.signaltekno.data.remote.PostService
import com.signaltekno.data.remote.dto.PostResponse
import com.signaltekno.ktorandroid.ui.theme.KtorAndroidTheme

class MainActivity : ComponentActivity() {

    private val client = PostService.createPostService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val post by produceState<List<PostResponse>>(initialValue = emptyList(), producer = {
                value = client.getPosts()
            })
            LazyColumn{
                items(post){
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)) {
                        Text(text = it.title, fontSize = 20.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = it.body, fontSize = 14.sp)
                    }
                }
            }
        }
    }
}
