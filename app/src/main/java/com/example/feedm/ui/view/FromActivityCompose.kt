package com.example.feedm.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feedm.R
import com.example.feedm.ui.view.ui.theme.FeedmTheme

class FromActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FeedmTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomAppBar {
                        Row(horizontalArrangement = Arrangement.Center,
                            modifier= Modifier.fillMaxWidth()){
                            FloatingActionButton(onClick = { /*TODO*/ },
                                containerColor = Color.Yellow,
                                modifier = Modifier.width(200.dp)) {
                                Text(text = "Agregar")
                            }
                        }
                    }}) { innerPadding ->
                    FormScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FormScreen(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    Column(
        modifier
            .fillMaxSize(1f)
            .padding(horizontal = 30.dp)) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = Color.White,
            elevation = FloatingActionButtonDefaults.elevation(0.dp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "", modifier = Modifier.background(color = Color.White)
            )
        }
        PetName()
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            val spacerPadding =15.dp
            CustomDropDownMenu(
                options = stringArrayResource(id = R.array.fa_arraySpinnerEdad).toList(),
                title = stringResource(id = R.string.ma_txtSpinnerEdad)
            )
            Spacer(modifier = Modifier.padding(spacerPadding))
            Text(text = stringResource(id = R.string.fa_txtSpinnerSexo),
                style = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 15.dp))
            val optionsSex = stringArrayResource(id = R.array.fa_arraySpinnerSexo).toList()
            var selectedOptionSex by remember { mutableStateOf(optionsSex[0])}
            CustomRadioGroup(selectedOption = selectedOptionSex,
                onOptionSelected = {selectedOptionSex = it},
                options = optionsSex, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.padding(spacerPadding))
            CustomSlider()
            Spacer(modifier = Modifier.padding(spacerPadding))
            Text(text = stringResource(id = R.string.fa_txtSpinnerEsterilizado),
                style = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 15.dp))
            val optionsSterilized = stringArrayResource(id = R.array.fa_arraySpinnerActividadFisica).toList()
            var selectedOptionSterilized by remember { mutableStateOf(optionsSterilized[0]) }
            CustomRadioGroup(selectedOption = selectedOptionSterilized,
                onOptionSelected = {selectedOptionSterilized = it},
                options = optionsSterilized, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.padding(10.dp))
            CustomDropDownMenu(
                options = stringArrayResource(id = R.array.fa_arraySpinnerActividadFisica).toList(),
                title = stringResource(id = R.string.fa_txtSpinnerActividadFisica)
            )
            CustomDropDownMenu(
                options = stringArrayResource(id = R.array.fa_arraySpinnerObjetivo).toList(),
                title = stringResource(id = R.string.fa_txtSpinnerObjetivo)
            )
        }
    }
}



@Composable
fun PetName(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(15.dp)
    ) {
        var text by remember { mutableStateOf("") }
        IconButton(
            onClick = { /*TODO*/ },
            Modifier
                .weight(0.3f)
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_dog_illustration),
                contentDescription = "",
                modifier = Modifier.padding(10.dp)
            )
        }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .weight(0.5f)
                .align(Alignment.CenterVertically)
                .padding(bottom = 10.dp)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropDownMenu(options: List<String>, title: String) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }
    ExposedDropdownMenuBox(expanded = expanded,
        onExpandedChange = { expanded = it }) {

        TextField(
            value = selectedOption,
            textStyle = TextStyle(fontSize = 16.sp),
            onValueChange = {},
            label = {
                Text(
                    text = title, style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
        )

        ExposedDropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false }) {
            options.forEach { option ->
                DropdownMenuItem(text = { Text(text = option) },
                    onClick = {
                        selectedOption = option
                        expanded = false
                    })
            }
        }


    }
}

@Preview
@Composable
fun DropDownMenuPreview(modifier: Modifier = Modifier) {
    CustomDropDownMenu(options = listOf("option1", "option2", "option3"), title = "Example")
}

@Preview
@Composable
fun CustomSlider(modifier: Modifier = Modifier){
    var sliderPosition by remember { mutableFloatStateOf(0f)}
    Column(modifier = modifier.padding(horizontal = 15.dp)) {
        Text(stringResource(id = R.string.fa_txtSpinnerPeso),
            style = TextStyle(fontSize = 19.sp, fontWeight = FontWeight.Bold)
        )
        Text(String.format("%.2f Kg",sliderPosition),
            style = TextStyle(fontSize = 19.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally))
        Slider(value = sliderPosition,
            onValueChange = {sliderPosition = it},
            valueRange = 0f..85f,
            modifier = Modifier.padding(top = 0.dp,
                bottom = 10.dp, start = 10.dp, end = 10.dp))
    }
}

@Composable
fun CustomRadioGroup(modifier: Modifier = Modifier,
                     options: List<String>,
                     selectedOption: String,
                     onOptionSelected: (String) -> Unit){
    Row(modifier = modifier.fillMaxWidth()){
        options.forEach{ option ->
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(0.3f),
                horizontalArrangement = Arrangement.Center) {
                RadioButton(selected = selectedOption == option, onClick = { onOptionSelected(option) })
                Text(text = option)
            }

        }
    }
}


