package com.example.coffeeshop.android.component

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toIcon
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.domain.model.Coffee
import com.example.coffeeshop.untils.Constants.COFFEE_PHOTOS_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

@Composable
fun CoffeeCart(coffee: Coffee) {

    val image = remember {
        mutableStateOf<ImageBitmap?>(null)
    }

    val coroutine = CoroutineScope(Dispatchers.IO)

    LaunchedEffect(Unit){
        coroutine.launch {
            val img = URL(COFFEE_PHOTOS_URL+coffee.id).readBytes()
            val bmp = BitmapFactory.decodeByteArray(img, 0, img.size)
            image.value = bmp.asImageBitmap()

        }
    }

    Card(
        backgroundColor = AppTheme.colors.thirdSubBackground,
        shape = RoundedCornerShape(16.dp)
    ){
        Box(modifier = Modifier.fillMaxWidth()){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp)
                    .padding(top = 4.dp, bottom = 12.dp)
            ) {
                if(image.value != null) {
                    Image(
                        image.value!!,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(132.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Column(modifier = Modifier.padding(horizontal = 5.dp)) {
                    Text(
                        text = coffee.categoryTitle,
                        fontSize = 16.sp,
                        color = AppTheme.colors.secondPrimaryTitle,
                        fontFamily = sora,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = coffee.subtitle,
                        fontSize = 12.sp,
                        color = AppTheme.colors.thirdGradientBackground,
                        fontFamily = sora,
                        fontWeight = FontWeight.Normal
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = "$${coffee.price}",
                            fontSize = 18.sp,
                            color = AppTheme.colors.fourthPrimaryTitle,
                            fontFamily = sora,
                            fontWeight = FontWeight.SemiBold
                        )
                        Button(
                            onClick = { },
                            modifier = Modifier.size(32.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = AppTheme.colors.thirdBackground),
                            elevation = ButtonDefaults.elevation(0.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "",
                                modifier = Modifier.size(15.dp),
                                tint = AppTheme.colors.primaryTitle
                            )
                        }
                    }
                }
            } 
        }

    }

}