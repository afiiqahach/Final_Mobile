package com.D121211014.castharrypotter.ui.activities.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211014.castharrypotter.R
import com.D121211014.castharrypotter.data.models.Character
import com.D121211014.castharrypotter.ui.theme.CastHarryPotterTheme

class DetailActivity : ComponentActivity() {
    private var selectedCharacter: Character? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        selectedCharacter = intent.getParcelableExtra("CHARACTER")
        setContent {
            CastHarryPotterTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    DetailScreen()
                }
            }
        }
    }

    @Composable
    fun DetailScreen() {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())

        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(selectedCharacter?.webformatURL)
                    .crossfade(true)
                    .build(),
                contentDescription = "ini gambar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(400.dp)
                    .height(600.dp),
                )
            Spacer(modifier = Modifier.height(20.dp))

            //title
            Text(text = selectedCharacter?.tags.toString(),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace,
                fontSize = 20.sp,
                )
            Spacer(modifier = Modifier.height(10.dp))


            //desc
            Text(text = "Suka: ${selectedCharacter?.likes.toString()}")
            Spacer(modifier = Modifier.height(10.dp))
            //content
            Text(text = "Views: ${selectedCharacter?.views.toString()}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "URL: ${selectedCharacter?.previewURL.toString()}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Collection: ${selectedCharacter?.collections.toString()}")
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Download: ${selectedCharacter?.downloads.toString()}")
        }
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_detail)
//    }
}