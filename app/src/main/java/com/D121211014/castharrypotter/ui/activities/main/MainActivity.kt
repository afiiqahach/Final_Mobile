package com.D121211014.castharrypotter.ui.activities.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.D121211014.castharrypotter.data.models.Character
import com.D121211014.castharrypotter.ui.activities.detail.DetailActivity
import com.D121211014.castharrypotter.ui.theme.CastHarryPotterTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CastHarryPotterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            modifier = Modifier
                                .background(Color.Cyan),
                            title = {
                                Text(
                                    text = "CHARACTER HARRY POTTER",
                                    fontWeight = FontWeight.Bold,

                                )
                            }
                        )
                        val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                        ListCharacterScreen(mainViewModel.mainUiState)
                    }
                }
            }
        }
    }

    @Composable
    private fun ListCharacterScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
        Column (
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            when(mainUiState) {
                is MainUiState.Success -> ListCharacter(mainUiState.character)
                is MainUiState.Error -> Text(text = "Error", fontSize = 15.sp)
                is MainUiState.Loading ->  Text(text = "Loading...", fontSize = 15.sp)
            }
        }
    }

    @Composable
    fun ListCharacter(character: List<Character>, modifier: Modifier = Modifier) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            items(character) {character ->
                CharacterCard(character = character)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    @Composable
    fun CharacterCard(character: Character) {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("CHARACTER", character)
                    startActivity(intent)
                }) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
                ){
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(character.webformatURL)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Gambar Harry Potter",
                    contentScale = ContentScale.Crop,

                    modifier = Modifier
                        .width(280.dp)
                        .height(150.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .fillMaxWidth()
                    
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Difoto Oleh: ${character.user.toString()}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue
                    )

            }
        }

    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            CastHarryPotterTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }
}