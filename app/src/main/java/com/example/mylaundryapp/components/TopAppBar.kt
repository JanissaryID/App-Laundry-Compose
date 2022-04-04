package com.example.mylaundryapp.components

import android.widget.Toast
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mylaundryapp.PAYMENT_SUCCESS
import com.example.mylaundryapp.api.price.PriceApp
import com.example.mylaundryapp.navigation.Screens

@Composable
fun TopAppBarView(
    navController: NavController,
    title: String,
    screenBack: String
) {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.navigate(route = screenBack) {
                        if (screenBack == "machine_screen"){
                            PAYMENT_SUCCESS = true
                        }
                        popUpTo(screenBack) {
                            inclusive = true
                        }
                    }
//                        Toast.makeText(context, "Screen $screenBack", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = MaterialTheme.colors.primary
                )
            }
        }
    )
}

@Composable
fun TopAppBarViewHome(
    navController: NavController,
    title: String,
) {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary,
            )
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp,
        actions = {
            IconButton(
                onClick = {
                    navController.navigate(route = Screens.Setting.route)
//                    Toast.makeText(context, "Menu", Toast.LENGTH_SHORT).show()
                }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Menu", tint = MaterialTheme.colors.primary)
            }
        }
    )
}