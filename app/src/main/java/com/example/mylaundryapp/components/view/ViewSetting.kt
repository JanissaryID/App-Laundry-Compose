package com.example.mylaundryapp.components.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mylaundryapp.components.ButtonView
import com.example.mylaundryapp.components.TextFieldOutline

@Composable
fun ViewSetting(modifier: Modifier, valueItem: String,texts: String = "Address", onClick: () -> Unit) {
    Card(shape = RoundedCornerShape(20.dp)) {
        ConstraintLayout(modifier = Modifier
            .wrapContentHeight()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)) {

            val (inputField,button) = createRefs()

            TextFieldOutline(texts = texts,
                modifier = Modifier.constrainAs(inputField){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }, valueItem = valueItem)

            ButtonView(
                title = "Save",
                enable = true,
                modifier = Modifier.constrainAs(button){
                    top.linkTo(inputField.bottom, 10.dp)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            ) {
                onClick()
            }
        }
    }
}