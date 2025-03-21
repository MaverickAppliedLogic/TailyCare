package com.example.feedm.core.ui.components


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDropDownMenu(
    options: List<String>, title: String,
    selectedOption: String?,
    modifier: Modifier = Modifier,
    errorCommitting: Boolean,
    focusRequester: FocusRequester = remember { FocusRequester() },
    onSelectOption: (String) -> Unit,
    onDeleteIconClicked: (String) -> Unit,
    deletableOption: Boolean? = false
) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(expanded = expanded,
        onExpandedChange = { expanded = it }) {

        TextField(
            value = selectedOption ?: "",
            textStyle = TextStyle(fontSize = 16.sp),
            isError = errorCommitting,
            onValueChange = {},
            trailingIcon = {
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
            },
            label = {
                val textColor: Color = if (errorCommitting) Color.Red else Color.Black
                Text(
                    text = title, style = TextStyle(
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                    )
                )
            },
            readOnly = true,
            modifier = modifier
                .fillMaxSize()
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                errorTrailingIconColor = Color.Red,
                errorContainerColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = Color.White,
            modifier = Modifier
                .focusRequester(focusRequester)
                .clip(
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            options.forEach { option ->


                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            color = if (option == "Nueva comida") Color.LightGray else Color.Black,
                            style =
                            if (option == "Nueva comida")
                                TextStyle(
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Bold
                                )
                            else TextStyle(fontWeight = FontWeight.Normal)
                        )
                    },
                    onClick = { onSelectOption(option); expanded = false },
                    trailingIcon = {
                        if (deletableOption != null && deletableOption && option != "Nueva comida"){
                        IconButton(onClick = {onDeleteIconClicked(option)}) {
                            Icon(imageVector = Icons.Default.Clear, contentDescription = "",
                                modifier = Modifier.scale(0.8f))

                        }
                        }
                    },
                )

            }
        }
    }
}


@Preview
@Composable
fun CustomDropDownMenuPreview() {
    val options = listOf("Option 1", "Option 2", "Option 3")
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var errorCommitting by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.25.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(top = 20.dp)
            .height(60.dp)
            .width(300.dp)
    ) {
        CustomDropDownMenu(
            options = listOf("Nueva comida", "option2"),
            title = "",
            selectedOption = "Nueva comida",
            errorCommitting = false,
            onSelectOption = {},
            onDeleteIconClicked = {},
            deletableOption = true
        )
    }
}