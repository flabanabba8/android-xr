package com.xrcaption.quest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.xrcaption.quest.ui.screen.CaptionScreen
import com.xrcaption.quest.ui.theme.XRCaptionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            XRCaptionTheme {
                CaptionScreen()
            }
        }
    }
}
