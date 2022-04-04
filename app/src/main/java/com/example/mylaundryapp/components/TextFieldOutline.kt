package com.example.mylaundryapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import com.example.mylaundryapp.TEXT_FIELD

@Composable
fun TextFieldOutline(texts: String = "Port", modifier: Modifier = Modifier, valueItem: String) {

    var text_field by remember { mutableStateOf(TextFieldValue(valueItem)) }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.onSurface,
            focusedLabelColor = MaterialTheme.colors.onSurface,
            textColor = MaterialTheme.colors.onSurface,
            cursorColor = MaterialTheme.colors.onSurface
        ),
        value = text_field,
        label = { Text(text = texts) },
        onValueChange = {
            text_field = it
            TEXT_FIELD = text_field
        }
    )
}