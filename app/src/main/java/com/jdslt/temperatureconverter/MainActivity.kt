package com.jdslt.temperatureconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainActivityContent()
        }
    }
}

@Composable
fun MainActivityContent(){
    val celsius = remember { mutableStateOf(0) }
    val newCelsius = remember { mutableStateOf("") }
    Column (
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
            ){
            Header(image = R.drawable.photo_1616036740257_9449ea1f6605, description ="sunrise image" )
            EnterTemperature(temperature = newCelsius.value){newCelsius.value = it}
            ConvertButton {
                newCelsius.value.toIntOrNull()?.let{
                    celsius.value = it
                }
            }
            TemperatureText(celsius.value)
            }
}
@Composable
fun Header(image: Int, description:String){
    Image(
        painter = painterResource(image),
        contentDescription = description,
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop,
    )
    Spacer(modifier = Modifier.height(10.dp))
}
@Composable
fun TemperatureText(celsius:Int){
    val fahrenheit = (celsius.toDouble()*9/5)+32
    Text("$celsius Celsius is $fahrenheit Fahrenheit", textAlign = TextAlign.Center )

}
@Composable
fun ConvertButton(clicked: () -> Unit){
    Button(onClick = clicked) {
        Text("Convert")
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun EnterTemperature(temperature: String, changed: (String) -> Unit){
    TextField(value = temperature,
        singleLine = true,
        label= {Text("Enter a temperature in Celsius")},
        onValueChange = changed,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth() )
}


@Preview(showBackground = true)
@Composable
fun PreviewMainActivity() {
    MainActivityContent()
}