package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                ArtSpaceScreen()
            }
        }
    }
}

@Composable
fun ArtSpaceScreen() {
    val artworks = listOf(
        Artwork("Mona Lisa", "Leonardo da Vinci", "1503", R.drawable.monalisa),
        Artwork("Starry Night", "Vincent van Gogh", "1889", R.drawable.starrynight),
        Artwork("The Persistence of Memory", "Salvador Dalí", "1931", R.drawable.thepersistenceofmemory)
    )

    val currentArtwork = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = artworks[currentArtwork.value].imageId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = artworks[currentArtwork.value].title,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5
        )
        Text(
            text = "by ${artworks[currentArtwork.value].artist}",
            style = MaterialTheme.typography.body1
        )
        Text(
            text = "Year: ${artworks[currentArtwork.value].year}",
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (currentArtwork.value > 0) {
                        currentArtwork.value -= 1
                    } else {
                        currentArtwork.value = artworks.size - 1
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Précédent")
            }

            Button(
                onClick = {
                    if (currentArtwork.value < artworks.size - 1) {
                        currentArtwork.value += 1
                    } else {
                        currentArtwork.value = 0
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Suivant")
            }
        }
    }
}

data class Artwork(val title: String, val artist: String, val year: String, val imageId: Int)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}
