package com.kareem.gyms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
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
import com.kareem.gyms.ui.theme.GymsTheme
import com.kareem.gyms.ui.theme.Purple40
import com.kareem.gyms.ui.theme.Purple80

@Composable
fun GymsScreen() {

    LazyColumn() {
        items(listOfGyms) { gym ->
            GymItem(gym)

        }
    }

}

@Composable
fun GymItem(gym: Gym) {
    Card(elevation = CardDefaults.cardElevation(4.dp), modifier = Modifier.padding(8.dp)) {

        //content of the card
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            //image

            GymIcon(Icons.Filled.Place, Modifier.weight(0.15f))

            //vertical stuff
            GymDetails(gym, Modifier.weight(0.85f))


        }
    }
}

@Composable
fun GymIcon(vector: ImageVector, modifier: Modifier) {
    Image(
        imageVector = vector,
        contentDescription = "Gym Icon",
        modifier = modifier,
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

