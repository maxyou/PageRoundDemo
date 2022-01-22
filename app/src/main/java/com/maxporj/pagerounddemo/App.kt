package com.maxporj.pagerounddemo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun App() {

    var current by remember { mutableStateOf(1) }
    var totalDocs by remember { mutableStateOf(300) }
    var pageSize by remember { mutableStateOf(10) }
    var ext by remember { mutableStateOf(2) }

    Column(
        modifier = Modifier.padding(15.dp)
    ) {

        Text(text = "Hello PageRound!")

        Box(modifier = Modifier
            .fillMaxWidth(1f)
            .height(40.dp)
            .background(Color(0xffccccff)),
            contentAlignment = Alignment.Center
        ) {
            PageRound(
                current = current,
                ext = ext,
                totalDocs = totalDocs,
                pageSize = pageSize,
                onClick = { current = it },
                RoundButton = RoundButton,
                RoundInterval = RoundInterval
            )
        }
    }
}

val RoundInterval = @Composable {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .background(Color(0x22AC9C95)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "---")
    }
}

val RoundButton = @Composable { count: Int, isCurrent: Boolean, onClick: (Int) -> Unit ->
    Box(
        modifier = Modifier
            .padding(2.dp)
            .aspectRatio(1f)
            .clip(CircleShape)
            .fillMaxSize()
            .clickable { onClick(count) }
            .background(if (isCurrent) Color(0xFFACDC75) else Color(0xFF6C9C35)),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "${count}")
    }
}