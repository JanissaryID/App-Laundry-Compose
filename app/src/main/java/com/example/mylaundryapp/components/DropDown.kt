package com.example.mylaundryapp.components


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.mylaundryapp.*
import com.example.mylaundryapp.api.price.PriceModel
import com.example.mylaundryapp.api.price.PriceModelMenu
import com.example.mylaundryapp.api.price.PriceModelView
import com.example.mylaundryapp.api.price.PriceViewModel

@Composable
fun DropDownMenu(
    menuHome: Boolean,
    priceViewModel: PriceViewModel
) {

//    val context = LocalContext.current

    TEMP_MENU_VALUE = MENU_VALUE

    var expanded by remember { mutableStateOf(false) }

    var price: List<PriceModel> by remember {
        mutableStateOf(listOf())
    }

    price = priceViewModel.priceListResponse
    val titleMenu:ArrayList<PriceModelMenu> by remember { mutableStateOf(arrayListOf()) }

    price.forEach { priceValue ->
        titleMenu.add(
            PriceModelMenu(priceTypeMenu = priceValue.priceTypeMenu)
        )
    }
    val menu = titleMenu.toSet()

    var selectedText by remember { mutableStateOf("") }
//    var selectedTextMenuMachine by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val rotationState by animateFloatAsState(
        targetValue = (
        if (expanded){
            180f
        } else{
            0f
        })
    )

    Column(Modifier.padding(start = 4.dp, end = 4.dp, top = 16.dp, bottom = 16.dp)) {
        OutlinedTextField(
            value = selectedText,
            onValueChange = {
                selectedText = it
                },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
//            label = {Text("Label")},
            trailingIcon = {
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "contentDescription",
                    Modifier
                        .clickable { expanded = !expanded }
                        .rotate(rotationState))
            },
            readOnly = true,
            enabled = true
        )
//        Log.d("debug", "$MENU_VALUE")
        if(!menuHome){
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current){textfieldSize.width.toDp()})
            ) {
                menu.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedText = label.priceTypeMenu.toString()
                        MENU_VALUE = selectedText
                        expanded = false

                        if(MENU_VALUE != TEMP_MENU_VALUE){
                            SELECTED_INDEX = -1
                            INDEX_CLASS_MACHINE = -1
                            TITLE_MENU_MACHINE.clear()
                        }
                        if(!expanded){
                            MENU_VALUE_MACHINE = ""
                        }
                    }) {
                        Text(text = label.priceTypeMenu.toString())
                    }
                }
            }
        }
        else{
            if(MENU_VALUE != "" && INDEX_CLASS_MACHINE != -1){
                price.forEach { priceValue ->
                    if(priceValue.priceTypeMenu == MENU_VALUE
                        &&
                        priceValue.priceClassMachine == (if(INDEX_CLASS_MACHINE == 0) false else true)) {
                        TITLE_MENU_MACHINE.add(
                            PriceModelMenu(priceTypeMenu = priceValue.priceTitle)
                        )
                    }
                }
//                if (!CLICKED_BUTTON){
//                    TITLE_MENU_MACHINE.clear()
//                }
                val menuTitle = TITLE_MENU_MACHINE.toSet().toMutableList()
                DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                            .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                    ) {
                        menuTitle.forEach { label ->
                            DropdownMenuItem(onClick = {
                                selectedText = label.priceTypeMenu.toString()
                                expanded = false

                                MENU_VALUE_MACHINE = selectedText
                            }) {
                                Text(text = label.priceTypeMenu.toString())
                            }
                        }
                }
            }
            if (!MENU_VALUE.isNullOrEmpty() && !MENU_VALUE_MACHINE.isNullOrEmpty() && INDEX_CLASS_MACHINE != -1){
                price.forEach { priceValue ->
                    if(priceValue.priceTypeMenu == MENU_VALUE
                        &&
                        priceValue.priceClassMachine == (if(INDEX_CLASS_MACHINE == 0) false else true)
                        &&
                        priceValue.priceTitle == MENU_VALUE_MACHINE
                    ) {
                        PRICE_VALUE.clear()
                        PRICE_VALUE.add(
                            PriceModelView(
                                price = priceValue.price,
                                priceTime = priceValue.priceTime,
                                priceTitle = priceValue.priceTitle,
                                id = priceValue.id,
                                isPacket = priceValue.isPacket
                            )
                        )
                    }
                }
            }
        }
    }
}

