package com.ioanavasile.evoteapp.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ioanavasile.evoteapp.presentation.ui.nav.EVoteNavHost
import com.ioanavasile.evoteapp.presentation.ui.theme.EVoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EVoteAppTheme {
                EVoteNavHost()
            }
        }
    }
}