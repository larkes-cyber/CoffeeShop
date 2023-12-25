package com.example.coffeeshop.android.screen.profile_screen

import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.coffeeshop.android.component.ItemsPicker
import com.example.coffeeshop.android.theme.AppTheme
import com.example.coffeeshop.android.theme.sora
import com.example.coffeeshop.android.untils.Constants.SELECT_LANG_TITLE
import com.example.coffeeshop.android.untils.Constants.SETTINGS_TITLE
import com.example.coffeeshop.untils.Constants

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel:ProfileScreenViewModel
) {

    val userUIState by viewModel.userUIState.collectAsState()
    val selectedImageUriUIState by viewModel.selectedImageUriUIState.collectAsState()
    val profileUIState by viewModel.profileUIState.collectAsState()
    val context = LocalContext.current


    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if(uri != null) {
            viewModel.uploadProfileImage(uri = uri, context = context)
        }
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.secondBackground)
    ){

        item {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                AppTheme.colors.secondGradientBackground,
                                AppTheme.colors.firstGradientBackground

                            )
                        )
                    )
                    .padding(horizontal = 30.dp)
                    .padding(vertical = 20.dp)
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if(userUIState != null) {
                        Button(
                            onClick = {
                                galleryLauncher.launch("image/*")
                            },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
                            contentPadding = PaddingValues(0.dp),
                            elevation = ButtonDefaults.elevation(0.dp)
                        ) {
                            Box {
                                if(selectedImageUriUIState != null){
                                    AsyncImage(
                                        model = selectedImageUriUIState,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(89.dp)
                                            .clip(RoundedCornerShape(100)),
                                        contentScale = ContentScale.Crop
                                        )
                                }else{
                                    SubcomposeAsyncImage(
                                        model =  Constants.USER_PHOTO_URL + userUIState?.login,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(89.dp)
                                            .clip(RoundedCornerShape(100)),
                                        contentScale = ContentScale.Crop,
                                        loading = {
                                            Box(
                                                modifier = Modifier.size(89.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Rounded.AccountCircle,
                                                    contentDescription = "",
                                                    tint = AppTheme.colors.primaryTitle,
                                                    modifier = Modifier.size(89.dp)
                                                )
                                            }
                                        }
                                    )
                                }
                                Icon(
                                    imageVector = Icons.Outlined.AddCircle,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .size(25.dp)
                                        .align(Alignment.BottomEnd),
                                    tint = AppTheme.colors.primaryTitle
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if(profileUIState.isNameFormActive){

                            }
                            Text(
                                text = viewModel.userUIState.value?.name ?: "",
                                fontSize = 17.sp,
                                color = AppTheme.colors.primaryTitle,
                                fontFamily = sora,
                                fontWeight = FontWeight.Bold
                            )

                            IconButton(onClick = {  }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "",
                                    tint = AppTheme.colors.primaryTitle
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 30.dp)
                    .padding(top = 34.dp)
            ) {
                Text(
                    text = SETTINGS_TITLE,
                    fontSize = 20.sp,
                    fontFamily = sora,
                    fontWeight = FontWeight.Bold,
                    color = AppTheme.colors.secondPrimaryTitle
                )
                Spacer(modifier = Modifier.height(19.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(3.dp)) {
                        Icon(
                            imageVector = Icons.Default.Phone,
                            contentDescription = "",
                            tint = AppTheme.colors.secondPrimaryTitle
                        )
                        Text(
                            text = userUIState?.number ?: "+79XXXXXXXXX",
                            fontFamily = sora,
                            color = AppTheme.colors.secondPrimaryTitle,
                            fontSize = 14.sp
                        )
                    }
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "",
                            tint = AppTheme.colors.secondPrimaryTitle
                        )
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = SELECT_LANG_TITLE,
                        fontFamily = sora,
                        color = AppTheme.colors.secondPrimaryTitle,
                        fontSize = 14.sp
                    )
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Row(
                            modifier = Modifier.clickable {
                                viewModel.switchShowingPicker()
                            }
                        ) {
                            Icon(
                               imageVector = if(profileUIState.isPickerActive) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                               contentDescription = "",
                               tint = AppTheme.colors.secondPrimaryTitle
                            )
                            Text(
                               text = profileUIState.selectedLang,
                               fontFamily = sora,
                               color = AppTheme.colors.secondPrimaryTitle,
                               fontSize = 14.sp
                            )
                        }
                        if(profileUIState.isPickerActive) {
                            ItemsPicker(
                                currentTitle = profileUIState.selectedLang,
                                options = viewModel.languages,
                                onClick = {
                                    viewModel.onTitleChange(it)
                                }
                            )
                        }
                    }
                }
            }
        }

    }
}