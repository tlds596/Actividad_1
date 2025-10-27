package com.example.myapplication.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource


@Composable
fun main(){
    Box() {
        Column() {
            Text(
                text = "My name Is Jhon Harvey"
            )

            Image(
                painter = painterResource(id= R.drawable.ic_launcher_foreground),
                contentDescription = "Image person"


                )

            Text(text = "I am profecional in Android")

        }

        Box() {
            footerBar()
        }
    }
}
@Composable
fun footerBar (){
    Row() {
        items()
        items()
        items()
    }
}

@Composable
fun items(){
    Box(){}
}
