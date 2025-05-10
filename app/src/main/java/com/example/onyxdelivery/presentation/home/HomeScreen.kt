package com.example.onyxdelivery.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.onyxdelivery.R
import com.example.onyxdelivery.core.utils.Resource
import com.example.onyxdelivery.data.model.entity.DeliveryItemEntity
import com.example.onyxdelivery.domain.extensions.mapStatus
import com.example.onyxdelivery.domain.extensions.statusToColor
import com.example.onyxdelivery.ui.theme.DeepOceanBlue
import com.example.onyxdelivery.ui.theme.Firestorm
import com.example.onyxdelivery.ui.theme.SteelGray
import com.example.onyxdelivery.ui.theme.White

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val billsState by viewModel.billsState.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadBills("1010")
    }
    HomeContent(
        billsState = billsState,
        screenState = screenState,
        onTabSelected = viewModel::onChangeTab
    )
}

@Composable
fun HomeContent(
    screenState: HomeState,
    onTabSelected: (String) -> Unit,
    billsState: Resource<List<DeliveryItemEntity>>,
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        val (rider, language, circle, emptyHome, toggle, name, list, upperSide, loader) = createRefs()

        Row(
            modifier = Modifier
                .height(170.dp)
                .fillMaxWidth()
                .background(Firestorm)
                .constrainAs(upperSide) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {}

        Column(
            Modifier.constrainAs(name) {
                top.linkTo(parent.top, 48.dp)
                start.linkTo(parent.start, 16.dp)
            }
        ) {
            Text(
                "Ahmed",
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium)),
                color = Color.White
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                "Othman",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
                color = Color.White
            )
        }

        Image(
            modifier = Modifier
                .constrainAs(circle) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .height(160.dp)
                .width(160.dp),
            painter = painterResource(id = R.drawable.circle_blue),
            contentDescription = "blue circle",
        )

        Image(
            modifier = Modifier
                .constrainAs(rider) {
                    top.linkTo(parent.top, 24.dp)
                    start.linkTo(name.end, 56.dp)
                }
                .height(160.dp)
                .width(160.dp),
            painter = painterResource(id = R.drawable.deliveryman),
            contentDescription = "rider",
        )

        IconButton(
            onClick = { },
            modifier = Modifier
                .size(48.dp)
                .constrainAs(language) {
                    top.linkTo(parent.top, margin = 48.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.language),
                contentDescription = "Language",
                tint = Color.White
            )
        }

        ToggleSegmentedControl(
            modifier = Modifier.constrainAs(toggle) {
                top.linkTo(upperSide.bottom, 16.dp)
                start.linkTo(parent.start, 16.dp)
                end.linkTo(parent.end, 16.dp)
            },
            selectedTab = screenState.selectedTab,
            onTabSelected = onTabSelected
        )

        when (billsState) {

            is Resource.Loading -> Box(
                modifier = Modifier
                    .constrainAs(loader) {
                        top.linkTo(toggle.bottom, 32.dp)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp),
                    color = DeepOceanBlue,
                    strokeWidth = 4.dp
                )
            }

            is Resource.Success -> {
                val items = if (screenState.selectedTab == "New") billsState.data.filter {
                    it.status == "0"
                } else billsState.data.filter {
                    it.status != "0"
                }
                if (items.isEmpty()) {
                    EmptyHome(
                        modifier = Modifier
                            .constrainAs(emptyHome) {
                                top.linkTo(toggle.bottom, 16.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                            .fillMaxSize()
                    )
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .constrainAs(list) {
                                top.linkTo(toggle.bottom, 16.dp)
                                start.linkTo(parent.start, 16.dp)
                                end.linkTo(parent.end, 16.dp)
                            }
                            .padding(top = 16.dp, bottom = 320.dp)
                    ) {
                        items(items) { item ->
                            DeliveryCard(item = item)
                        }
                    }
                }
            }

            is Resource.Error -> {
                EmptyHome(
                    modifier = Modifier
                        .constrainAs(emptyHome) {
                            top.linkTo(toggle.bottom, 16.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                        .fillMaxSize()
                )
            }

            else -> {}
        }
    }
}

@Composable
fun EmptyHome(
    modifier: Modifier = Modifier
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
    ) {

        Image(
            modifier = Modifier
                .width(200.dp)
                .height(200.dp),
            painter = painterResource(id = R.drawable.search),
            contentDescription = "search",
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "No orders yet",
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_semibold)),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "You don't have any orders in your history.",
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_regular)),
            color = Color.Black
        )
    }

}


@Composable
fun ToggleSegmentedControl(
    modifier: Modifier = Modifier,
    selectedTab: String = "New",
    onTabSelected: (String) -> Unit
) {
    val options = listOf("New", "Others")

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(50))
            .background(Color.White)
            .padding(4.dp)
            .shadow(4.dp, RoundedCornerShape(50)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        options.forEach { option ->
            val isSelected = selectedTab == option
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .background(if (isSelected) Color(0xFF004D5F) else Color.White)
                    .clickable { onTabSelected(option) }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    color = if (isSelected) Color.White else DeepOceanBlue,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.montserrat_semibold))
                )
            }
        }
    }
}


@Composable
fun DeliveryCard(item: DeliveryItemEntity) {

    Card(
        modifier = Modifier
            .background(White)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
            .height(100.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .background(White)
                .fillMaxSize()
        ) {
            val (
                mobileNo,
                orderDetails,
                statusColumn,
                priceColumn,
                dateColumn,
                divider1,
                divider2,
            ) = createRefs()

            Text(
                "#${item.mobileNo}",
                modifier = Modifier.constrainAs(mobileNo) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 4.dp)
                },
                color = SteelGray,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.montserrat_medium))
            )

            Box(
                modifier = Modifier
                    .constrainAs(orderDetails) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .width(66.dp)
                    .fillMaxHeight()
                    .background(item.statusToColor()),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Order",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular))
                    )
                    Text(
                        "Details",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular))
                    )
                    Icon(
                        Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "Go",
                        tint = Color.White
                    )
                }
            }

            InfoColumn(
                modifier = Modifier.constrainAs(statusColumn) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 28.dp)
                },
                title = "Status",
                subTitle = item.mapStatus(),
                subtitleColor = item.statusToColor()
            )

            DividerLine(
                modifier = Modifier.constrainAs(divider1) {
                    top.linkTo(parent.top)
                    start.linkTo(statusColumn.end, 12.dp)
                }
            )

            InfoColumn(
                modifier = Modifier.constrainAs(priceColumn) {
                    start.linkTo(divider1.start, 16.dp)
                    top.linkTo(parent.top, 28.dp)
                },
                title = "Total Price",
                subTitle = "$${item.price}",
            )

            DividerLine(
                modifier = Modifier.constrainAs(divider2) {
                    top.linkTo(parent.top)
                    start.linkTo(priceColumn.end, 12.dp)
                }
            )

            InfoColumn(
                modifier = Modifier.constrainAs(dateColumn) {
                    start.linkTo(divider2.start, 16.dp)
                    top.linkTo(parent.top, 28.dp)
                },
                title = "Date",
                subTitle = item.date,
            )
        }
    }
}

@Composable
fun InfoColumn(
    modifier: Modifier = Modifier,
    title: String,
    subTitle: String,
    subtitleColor: Color = DeepOceanBlue
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(70.dp)
    ) {
        Text(
            title,
            color = SteelGray,
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            subTitle,
            color = subtitleColor,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_medium)),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun DividerLine(
    modifier: Modifier = Modifier
) {
    VerticalDivider(
        modifier = modifier
            .fillMaxHeight()
            .padding(top = 32.dp, bottom = 12.dp)
            .width(1.dp),
        color = SteelGray
    )
}