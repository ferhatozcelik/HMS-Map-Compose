package com.ferhatozcelik.firstcompose
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ferhatozcelik.firstcompose.ui.theme.ApplicationTheme
import com.huawei.hms.maps.MapsInitializer

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MapsInitializer.setApiKey("DAEDAGeEdU6sBZRlrHU/XHJB2IMEjd232Yu20rUmriGm9nENPGPxCwscya3ok9junUSqHMFphkt4bXZX8asZW6BJiDabhTls33oWzQ==")

            ApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.primary) {
                    Map()
                }
            }
        }
    }
}
