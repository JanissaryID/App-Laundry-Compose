package com.example.mylaundryapp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ButtonView(title: String, modifier: Modifier = Modifier, enable: Boolean, onClick: () -> Unit ) {

    Button(onClick = { onClick() },
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(40.dp),
        elevation = null,
        enabled = enable
    ) {
        Text(text = title, fontWeight = FontWeight.SemiBold)
    }
}