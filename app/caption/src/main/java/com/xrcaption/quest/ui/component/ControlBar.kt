package com.xrcaption.quest.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xrcaption.quest.asr.AsrState
import com.xrcaption.quest.ui.theme.AccentBlue
import com.xrcaption.quest.ui.theme.AccentGreen
import com.xrcaption.quest.ui.theme.ErrorRed

@Composable
fun ControlBar(
    asrState: AsrState,
    fontSize: Int,
    statusText: String,
    onToggleListening: () -> Unit,
    onIncreaseFontSize: () -> Unit,
    onDecreaseFontSize: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
    ) {
        // Status text — always visible for debugging
        if (statusText.isNotBlank()) {
            Text(
                text = statusText,
                color = AccentBlue.copy(alpha = 0.8f),
                fontSize = 12.sp,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Font size controls
            Row(verticalAlignment = Alignment.CenterVertically) {
                FilledTonalButton(onClick = onDecreaseFontSize) {
                    Text("A-", fontSize = 14.sp)
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${fontSize}sp",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                FilledTonalButton(onClick = onIncreaseFontSize) {
                    Text("A+", fontSize = 14.sp)
                }
            }

            // Start/Stop button
            when (asrState) {
                is AsrState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(48.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                is AsrState.Listening -> {
                    Button(
                        onClick = onToggleListening,
                        colors = ButtonDefaults.buttonColors(containerColor = ErrorRed)
                    ) {
                        Text("Stop", fontSize = 16.sp)
                    }
                }
                is AsrState.Ready -> {
                    Button(
                        onClick = onToggleListening,
                        colors = ButtonDefaults.buttonColors(containerColor = AccentGreen)
                    ) {
                        Text("Start", fontSize = 16.sp, color = Color.Black)
                    }
                }
                is AsrState.Error -> {
                    Text(
                        text = "Error: ${asrState.message}",
                        color = ErrorRed,
                        fontSize = 12.sp
                    )
                }
                else -> {
                    Text(
                        text = "Initializing...",
                        color = Color.White.copy(alpha = 0.5f),
                        fontSize = 14.sp
                    )
                }
            }

            // Status indicator
            Text(
                text = when (asrState) {
                    is AsrState.Listening -> "● LIVE"
                    is AsrState.Ready -> "○ Ready"
                    is AsrState.Loading -> "Loading..."
                    else -> ""
                },
                color = when (asrState) {
                    is AsrState.Listening -> AccentGreen
                    else -> Color.White.copy(alpha = 0.5f)
                },
                fontSize = 14.sp
            )
        }
    }
}
