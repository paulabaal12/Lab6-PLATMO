package com.example.lab6

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab6.ui.theme.Lab6Theme
class Galeria :ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {

                    ArtSpaceApp()
                }
            }
        }
    }
}

data class Artwork(
    val resourceId: Int,
    val name: String,
    val description: String
)

@Composable
fun ArtSpaceApp() {
    val context = LocalContext.current
    val artworkList = listOf(
        Artwork(R.drawable.debut, "TAYLOR SWIFT", "October 24, 2006"),
        Artwork(R.drawable.fearless, "FEARLESS (Taylor's Version)", "April 9, 2021"),
        Artwork(R.drawable.speaknoww, "SPEAK NOW(T.V)", "July 7, 2023"),
        Artwork(R.drawable.red, "RED (Taylor's Version) ", " November 12, 2021"),
        Artwork(R.drawable._989, "1989 (Taylor's Version) ", " October 27, 2023") ,
        Artwork(R.drawable.reputatio, "REPUTATION", " November 10, 2017"),
        Artwork(R.drawable.lover, "LOVER", " August 23, 2019"),
        Artwork(R.drawable.fol, "FOLKLORE", " July 24, 2020"),
        Artwork(R.drawable.evermore, "EVERMORE", " December 11, 2020"),
        Artwork(R.drawable.midnights___taylor_swift, "MIDNIGHTS", "October 21, 2022"),


        // Agrega más objetos Artwork según sea necesario
    )

    var currentIndex by remember { mutableStateOf(0) }
    val currentArtwork = artworkList[currentIndex]

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal =400.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally, // Centra horizontalmente en orientación vertical
            verticalArrangement = Arrangement.Center // Centra verticalmente en orientación horizontal
        ) {
            // Icono de logout
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Taylor Swift Albums",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 22.dp)
                )
                IconButton(
                    onClick = {
                        val intent = Intent(context, LoginPage::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        context.startActivity(intent)
                    },
                    modifier = Modifier
                        .padding(1.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = "Logout Icon"
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            ArtworkImage(currentArtwork)

            Spacer(modifier = Modifier.height(14.dp))

            ArtworkDescription(currentArtwork)

            Spacer(modifier = Modifier.weight(3f))

            ScreenController(
                currentIndex = currentIndex,
                artworkList = artworkList,
                onPreviousClick = {
                    currentIndex = (currentIndex - 1).coerceIn(0, artworkList.size - 1)
                },
                onNextClick = {
                    currentIndex = (currentIndex + 1) % artworkList.size
                }
            )
        }
    }
}
@Composable
fun ArtworkImage(artwork: Artwork) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp) // Ajusta la altura aquí para hacerla más larga
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(id = artwork.resourceId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .border(1.dp, Color.Black, shape = RoundedCornerShape(4.dp))
                .shadow(4.dp, shape = RoundedCornerShape(4.dp))
                .clip(shape = RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun ArtworkDescription(artwork: Artwork) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color(0xFFD0BCFF))
            .padding(12.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column {
            Text(
                text = artwork.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = artwork.description,
                style = MaterialTheme.typography.titleMedium,
            )
        }
    }
}
@Composable
fun ScreenController(
    currentIndex: Int,
    artworkList: List<Artwork>,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    Row{

        Button(onClick = {onPreviousClick()}, shape = RoundedCornerShape(30.dp)) {
            Text(text = "ANTERIOR")
        }
        Spacer(modifier = Modifier.width(200.dp))
        Button(onClick = {onNextClick()}, shape = RoundedCornerShape(30.dp)) {
            Text(text = "SIGUIENTE")
        }

        }
}


@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    Lab6Theme {
        ArtSpaceApp()
    }
}

