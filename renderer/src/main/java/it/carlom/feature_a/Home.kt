package it.carlom.feature_a

import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import it.carlom.feature_a.models.*

object Home {

    val content: Component =
        VerticalScrollComponent(
            childs = listOf(

                BoxComponent(
                    content = ColumnComponent(
                        content = listOf(

                            RowComponent(
                                modifier = RowModifier(horizontalArrangement = ArrangementModifier.SPACE_EVENLY, fillWidth = true ),
                                content = listOf(
                                    CareemTileComponent(
                                        image = "https://careem-prod-superapp-lts.s3-eu-west-1.amazonaws.com/assets/service_tile/ic_static_car_xxhdpi.png",
                                        text = "Ride"
                                    ),
                                    CareemTileComponent(
                                        image = "https://careem-prod-superapp-lts.s3-eu-west-1.amazonaws.com/assets/service_tile/ic_static_halataxi_xxhdpi.png",
                                        text = "Taxi"
                                    ),
                                    CareemTileComponent(
                                        image = "https://careem-prod-superapp-lts.s3-eu-west-1.amazonaws.com/assets/service_tile/ic_static_bike_xxhdpi.png",
                                        text = "Bike"
                                    )
                                )

                            ),

                            RowComponent(
                                modifier = RowModifier(horizontalArrangement = ArrangementModifier.SPACE_EVENLY, fillWidth = true),
                                content = listOf(
                                    CareemTileComponent(
                                        image = "https://careem-prod-superapp-lts.s3-eu-west-1.amazonaws.com/assets/service_tile/ic_static_food_xxhdpi.png",
                                        text = "Food"
                                    ),
                                    CareemTileComponent(
                                        image = "https://careem-prod-superapp-lts.s3-eu-west-1.amazonaws.com/assets/service_tile/ic_static_delivery_xxhdpi.png",
                                        text = "Delivery"
                                    ),
                                    CareemTileComponent(
                                        image = "https://careem-prod-superapp-lts.s3-eu-west-1.amazonaws.com/assets/service_tile/ic_static_shops_xxhdpi.png",
                                        text = "Shop"
                                    )
                                )

                            )
                        )
                    )
                )
            )

        )

    @Preview
    @Composable
    fun preview(){
        Renderer.render(component = content)
    }


}