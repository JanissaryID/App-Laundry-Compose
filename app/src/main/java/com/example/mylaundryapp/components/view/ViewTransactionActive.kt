package com.example.mylaundryapp.components.view

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mylaundryapp.DRYER_INDEX_TRANSACTION
import com.example.mylaundryapp.INDEX_CLASS_MACHINE
import com.example.mylaundryapp.MENU_VALUE
import com.example.mylaundryapp.components.ButtonView
import com.example.mylaundryapp.navigation.Screens
import com.example.mylaundryapp.ui.theme.MyLaundryAppTheme

@Composable
fun ViewTransactionActive(
    title_Menu: String,
    menu_machine: String,
    class_machine: String,
    date: String,
    price: String,
    index: String,
    payment: String,
    is_packet: Boolean,
    is_list_transaction: Boolean,
    step_one: Boolean,
    navController: NavController,
    number_machine: Int,
//    idTransaction: Int,
    onClick: (String) -> Unit
) {
    val context = LocalContext.current

    var expandedState by remember { mutableStateOf(false) }

    val rotationState by animateFloatAsState(
        targetValue = (
        if (expandedState){
            180f
        } else{
            0f
        })
    )

    Card(shape = RoundedCornerShape(20.dp),
        modifier = Modifier.clickable {
            expandedState = !expandedState
            onClick.invoke(index)
        }
    ) {
        Column(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
            .fillMaxWidth()) {

            ConstraintLayout(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
            ) {

                val (NumberTitle, TitleMenu, MenuMachine, ClassMachine, Date, Price, Payment) = createRefs()

                Text(
                    text = number_machine.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(NumberTitle)
                        {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )

                Text(
                    text = title_Menu,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(TitleMenu)
                        {
                            top.linkTo(NumberTitle.bottom)
                            start.linkTo(parent.start, 4.dp)
                        }
                )
                Text(
                    text = class_machine,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(ClassMachine)
                        {
                            top.linkTo(TitleMenu.bottom)
                            start.linkTo(parent.start, 4.dp)
                        }
                )
                Text(
                    text = menu_machine,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = MaterialTheme.colors.primaryVariant,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(MenuMachine)
                        {
                            top.linkTo(ClassMachine.bottom)
                            start.linkTo(parent.start, 4.dp)
                        }
                )
                Text(
                    text = date,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(Date)
                        {
                            top.linkTo(NumberTitle.bottom, 4.dp)
                            end.linkTo(parent.end, 4.dp)
                        }
                )
                Text(
                    text = "$payment",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.Green,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(Payment)
                        {
                            bottom.linkTo(Price.top)
                            end.linkTo(parent.end, 4.dp)
                        }
                )
                Text(
                    text = "IDR $price",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Green,
                    modifier = Modifier
                        .wrapContentHeight()
                        .constrainAs(Price)
                        {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end, 4.dp)
                        }
                )
            }
            if(!is_list_transaction){
                if(is_packet){
                    if(expandedState) {
                        ButtonView(
                            title = "Active Dryer",
                            enable = if (step_one) true else false,
                            modifier = Modifier.padding(top = 16.dp)
                        ) {
                            INDEX_CLASS_MACHINE = if(class_machine == "Giant") 0 else 1
                            MENU_VALUE = "Dryer"
                            DRYER_INDEX_TRANSACTION = index
                            navController.navigate(route = Screens.MachineDryer.route)
//                            Toast.makeText(context, "Index ID $index", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TransactionPreview() {
//    MyLaundryAppTheme {
//        ViewTransactionActivePrev(
//            title_Menu = "Washer",
//            menu_machine = "Extra Wash",
//            class_machine = "Giant",
//            date = "02-04-2020",
//            price = "25000",
//            payment = "Qris",
//            is_packet = true,
//            is_list_transaction = true,
//            step_one = true,
//            index = 1,
//            number_machine = 6
//        )
//    }
//}