package com.example.tipapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tipapp.ui.theme.TipAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()
                   ,
                    color = MaterialTheme.colorScheme.background
                ) {

//                    Greeting("Android")
                    TipApp()
                }
            }
        }
    }
}


@Composable
fun TipApp() {

Column(modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally



) {
    var billAmt by remember {
        mutableStateOf("")
    }
    var tipAmt  by remember {
        mutableStateOf("")
    }
    var peopleCount by remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .weight(1.5f)
        .fillMaxWidth()
        .border(BorderStroke(1.dp, Color.Green))
        .padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ) {

//        Text(text = "Calculating Area")
        TextFieldBoxes(label="Bill"){
            billAmt = it
            it


        }
        Spacer(modifier = Modifier.height(17.dp))
        TextFieldBoxes(label="Tip %"){
            tipAmt = it
            it

        }
        Spacer(modifier = Modifier.height(17.dp))
        TextFieldBoxes(label="Number of People"){
            peopleCount = it
            it

        }

    }

    Column(modifier = Modifier
        .weight(2f)
        .fillMaxWidth()
        .border(BorderStroke(1.dp, Color.Red))
        .padding(6.dp)

    ) {
        Text(text = "Display Area")
        Text(text = billAmt)
        Text(text = tipAmt)
        Text(text = peopleCount)


    }

}

//        calculate Area


       //        show Area






}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable

fun TextFieldBoxes(
    label:String,
    onValuechange:(String) -> String




){

    var newval by remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier.width(350.dp),

        value = newval,
        onValueChange = {
            newval = onValuechange(it)
                        },
        label = { Text(text = label)},
//        placeholder = { Text(placeholder) },

        keyboardOptions = KeyboardOptions(

            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number

        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
                // do something here
            }
        ),
        maxLines = 1,
        leadingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Add Button" )

        }},
        trailingIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Add Button" )

            }

        }


    )


}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TipAppTheme {
        TipApp()
    }
}