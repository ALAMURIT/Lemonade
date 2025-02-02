package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlinx.coroutines.launch
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    ImageButton(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    LemonadeTheme {
//        Greeting("Android")
//    }
//}

@Composable
fun ImageButton(modifier: Modifier = Modifier) {
    val _scope = rememberCoroutineScope()
    val _snackbaeHostState = remember { SnackbarHostState() }
    var _index by remember { mutableStateOf(0) }
    var _clickCount by remember { mutableStateOf(0) }
    var _imageResource = R.drawable.lemon_drink
    _imageResource = when (_index % 4) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    var _imageContent = "Tap the lemon tree to select a lemon"
    _imageContent = when (_index % 4) {
        0 -> "Tap the lemon tree to select a lemon"
        1 -> "Keep squeezing the lemon $_clickCount times"
        2 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    _scope.launch {
                        _snackbaeHostState.showSnackbar("Clicked")
                    }
                    if ((_index % 4) != 1) {
                        _clickCount = (1..9).random()
                        _index++
                    } else {
                        if (_clickCount <= 0) {
                            _index++
                        } else {
                            _clickCount--
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                shape = RoundedCornerShape(6.dp)
            ) {
                Image(
                    painter = painterResource(_imageResource),
                    contentDescription = _imageResource.toString(),
                    contentScale = ContentScale.Fit,
                    modifier=Modifier
                        //.background(Color.Transparent)
                        .clip(RoundedCornerShape(2.dp))
                )
            }
            Spacer(Modifier.size(16.dp))
            Text(_imageContent)
        }
    }
}
@Preview
@Composable
fun PreviewIMageButton(){
    ImageButton()
}