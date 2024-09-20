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
fun GymsScreen(onItemClick: (Int) -> Unit) {
    val vm: GymsViewModel = viewModel()

    LazyColumn(Modifier.padding(8.dp)) {
        items(vm.state) { gym ->
            GymItem(
                gym = gym,
                onFavouriteItemClick = {
                    vm.toggleFavouriteState(it)
                },
                onItemClick = {
                    onItemClick(it)
                }
            )
        }
    }
}

@Composable
fun GymItem(gym: Gym, onFavouriteItemClick: (Int) -> Unit, onItemClick: (Int) -> Unit) {

    val icon = if (gym.isFavourite) {
        Icons.Filled.Favorite
    } else {
        Icons.Filled.FavoriteBorder
    }

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemClick(gym.id) }
    ) {
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
                onFavouriteItemClick(gym.id)
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
fun GymDetails(
    gym: Gym,
    modifier: Modifier,
    horizontalAlign: Alignment.Horizontal = Alignment.CenterHorizontally
) {
    Column(modifier = modifier, horizontalAlignment = horizontalAlign) {
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

    }

}

