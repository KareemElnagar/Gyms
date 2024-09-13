package com.kareem.gyms

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kareem.gyms.ui.theme.GymsTheme
import com.kareem.gyms.ui.theme.Purple40

@Composable
fun GymsScreen() {
    val vm: GymsViewModel = viewModel()
    LazyColumn() {
        items(vm.state) { gym ->
            GymItem(gym) { gymId ->
                vm.toggleFavouriteState(gymId)
            }
        }
    }
}

@Composable
fun GymItem(gym: Gym, onclick: (Int) -> Unit) {

    val icon = if (gym.isFavourite) {
        Icons.Filled.Favorite
    } else {
        Icons.Filled.FavoriteBorder
    }

    Card(elevation = CardDefaults.cardElevation(4.dp), modifier = Modifier.padding(8.dp)) {
        //content of the card
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            //image

            DefaultIcon(Icons.Filled.Place, Modifier.weight(0.15f), "Gym Icon")

            //vertical stuff
            GymDetails(gym, Modifier.weight(0.70f))

            DefaultIcon(icon, Modifier.weight(0.15f), "Favourite Icon") {
                onclick(gym.id)
            }


        }
    }
}

@Composable
fun DefaultIcon(
    icon: ImageVector,
    modifier: Modifier,
    contentDescription: String,
    onclick: () -> Unit = {},

    ) {

    Image(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onclick()
            },
        colorFilter = ColorFilter.tint(
            Color.DarkGray
        )
    )
}

@Composable
fun GymDetails(gym: Gym, modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = gym.name,
            color = Purple40,
            style = MaterialTheme.typography.headlineSmall,
        )
        CompositionLocalProvider(
            value = LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        ) {

            Text(
                text = gym.place,
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GymItemPreview() {
    GymsTheme {
        GymsScreen()
    }

}
