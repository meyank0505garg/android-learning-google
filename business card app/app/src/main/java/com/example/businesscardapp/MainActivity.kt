package com.example.businesscardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Call
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    BusinessCardApp()
                }
            }
        }
    }
}



@Composable

fun BusinessCardApp() {
    Column(modifier = Modifier.fillMaxSize().background(Color(214,231,213)),
        verticalArrangement = Arrangement.SpaceAround

       ) {

        val image = painterResource(id =R.drawable.andy )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Image(painter = image, contentDescription = "andoid image")
            Text(text = "Mayank Garg",
                fontSize = 33.sp,
                color = Color(41, 151, 35, 255))
            Text(text = "Android Developer")

        }
//        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Rounded.Call,
                    contentDescription = "Call Icon",
                    tint = Color(41, 151, 35, 255))
                Spacer(modifier = Modifier.width(14.dp))
                Text(text = "9467170835")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Rounded.Share,
                    contentDescription = "Share Icon",
                    tint = Color(41, 151, 35, 255))
                Spacer(modifier = Modifier.width(14.dp))
                Text(text = "9467170835")
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Rounded.MailOutline,
                    contentDescription = "MailOutline Icon",
                    tint = Color(41, 151, 35, 255)
                )
                Spacer(modifier = Modifier.width(14.dp))
                Text(text = "9467170835")
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardAppTheme {
//        Greeting("Android")
        BusinessCardApp()
    }
}