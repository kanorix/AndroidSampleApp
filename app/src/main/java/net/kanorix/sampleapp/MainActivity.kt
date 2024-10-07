package net.kanorix.sampleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import net.kanorix.sampleapp.ui.theme.SampleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SampleAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Row(modifier = Modifier.padding(innerPadding)) {
                        Information(modifier = Modifier.padding(20.dp))
                        val singapore = LatLng(1.35, 103.87)
                        val cameraPositionState = rememberCameraPositionState {
                            position = CameraPosition.fromLatLngZoom(singapore, 10f)
                        }
                        GoogleMap(
                            modifier = Modifier.fillMaxSize(),
                            cameraPositionState = cameraPositionState
                        ) {
                            Marker(
                                state = MarkerState(position = singapore),
                                title = "Singapore",
                                snippet = "Marker in Singapore"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Information(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text("ENV: ${BuildConfig.ENV}")
        Text("BUILD_TYPE: ${BuildConfig.BUILD_TYPE}")
        Text("FLAVOR: ${BuildConfig.FLAVOR}")
        Text("APPLICATION_ID: ${BuildConfig.APPLICATION_ID}")
        Text("VERSION_CODE: ${BuildConfig.VERSION_CODE}")
        Text("VERSION_NAME: ${BuildConfig.VERSION_NAME}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleAppTheme {
        Information()
    }
}
