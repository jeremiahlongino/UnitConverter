package com.example.unitconverter

import android.os.Bundle
import android.widget.Space
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    UnitConverter( )
            }
        }
    }
}

@Composable
fun UnitConverter()
{
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Input") }
    var outputUnit by remember { mutableStateOf("Convert") }
    var inputTUnit by remember { mutableStateOf("Input") }
    var outputTUnit by remember { mutableStateOf("Convert") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    var iTExpanded by remember { mutableStateOf(false) }
    var oTExpanded by remember { mutableStateOf(false) }

    val conversionFactor = remember { mutableStateOf(0.01) }
    val oConversionFactor = remember { mutableStateOf(0.01) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 32.sp,
        color = Color.Black
    )


    fun convertUnits(){
        //?: - elvis operator; if null use on other side of :, if not use before
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = if(inputTUnit == "Celsius" && outputTUnit == "Fahrenheit") {
            (inputValueDouble * 9/5) + 32
        } else if(inputTUnit == "Fahrenheit" && outputTUnit == "Celsius") {
            (inputValueDouble - 32) * 5/9
        } else if(inputUnit != "Input" && outputUnit != "Convert") {
            // Only do length conversion if both length units are selected
            (inputValueDouble * conversionFactor.value * 100/
                    oConversionFactor.value).roundToInt()/100.0
        } else {
            0.0 // Default case
        }

        outputValue = result.toString()
    }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){


        // Here all UI elements will be stacked below each other
        Text("Unit Converter",
            style = customTextStyle

        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            inputValue = it
            // Here goes what should happen, when the Value of our OutliedTexField changes}

            },
            label = { Text("Enter Value") })

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            //Input Box
            Box {
                //Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(text = inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centimeters"
                            conversionFactor.value = 0.01
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            conversionFactor.value = 1.0
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()

                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Millimeters"
                            conversionFactor.value = 0.001
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Inches"
                            conversionFactor.value = 0.0254
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Output Box
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.00
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Millimeters"
                            oConversionFactor.value = 0.001
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Inches"
                            oConversionFactor.value = 0.0254
                            inputTUnit = "Input"
                            outputTUnit = "Convert"
                            convertUnits()
                        }
                    )
                }
            }
        }

        Row {
            Spacer(modifier = Modifier.height(16.dp))
            //Input Temp
            Box {
                Button(onClick = { iTExpanded = true }) {
                    Text(text = inputTUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iTExpanded, onDismissRequest = { iTExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Fahrenheit") },
                        onClick = {
                            iTExpanded = false
                            inputTUnit = "Fahrenheit"
                            conversionFactor.value = 1.00
                            inputUnit = "Input"
                            outputUnit = "Convert"
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Celsius") },
                        onClick = {
                            iTExpanded = false
                            inputTUnit = "Celsius"
                            conversionFactor.value = 1.00
                            inputUnit = "Input"
                            outputUnit = "Convert"
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            //Output Temp
            Box {
                Button(onClick = { oTExpanded = true }) {
                    Text(text = outputTUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oTExpanded, onDismissRequest = { oTExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Fahrenheit") },
                        onClick = {
                            oTExpanded = false
                            outputTUnit = "Fahrenheit"
                            oConversionFactor.value = 1.00
                            inputUnit = "Input"
                            outputUnit = "Convert"
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Celsius") },
                        onClick = {
                            oTExpanded = false
                            outputTUnit = "Celsius"
                            oConversionFactor.value = 1.00
                            inputUnit = "Input"
                            outputUnit = "Convert"
                            convertUnits()
                        }
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        //Result Text
        Text("Result: $outputValue ${
            when {
                outputTUnit != "Convert" -> outputTUnit  
                outputUnit != "Convert" -> outputUnit   
                else -> ""  
            }
        }",
                style = MaterialTheme.typography.headlineMedium

            )

    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()

}

}
