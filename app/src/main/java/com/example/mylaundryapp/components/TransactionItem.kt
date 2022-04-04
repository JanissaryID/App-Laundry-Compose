package com.example.mylaundryapp.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mylaundryapp.ui.theme.MyLaundryAppTheme

@Composable
fun TransactionItem(
    title: String = "Transaction Active",
    transactionActive: Int = 3
//     navController: NavController,
//     onClick: (Int) -> Unit
) {
    val context = LocalContext.current

    val modifier = Modifier

    Card(
        modifier = modifier.fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
        shape = RoundedCornerShape(40),
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.surface,
        elevation = 0.dp,
    ) {
        ConstraintLayout(modifier = modifier
            .height(68.dp)
            .padding(start = 16.dp, end = 16.dp)
        ) {

            val (titles,iconEnter) = createRefs()

            Text(
                text = "$transactionActive $title",
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                modifier = modifier.wrapContentHeight()
                    .constrainAs(titles)
                    {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, 12.dp)
                        bottom.linkTo(parent.bottom)
                    }
            )

            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Right Arrow Enter",
                modifier = modifier
                    .alpha(ContentAlpha.medium)
                    .constrainAs(iconEnter) {
                        end.linkTo(parent.end, 12.dp)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            )
        }
    }
}

