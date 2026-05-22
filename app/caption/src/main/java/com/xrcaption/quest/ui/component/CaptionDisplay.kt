package com.xrcaption.quest.ui.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.xrcaption.quest.caption.CaptionEntry

@Composable
fun CaptionDisplay(
    captions: List<CaptionEntry>,
    fontSize: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        if (captions.isEmpty()) {
            Text(
                text = "Captions will appear here...",
                color = Color.White.copy(alpha = 0.3f),
                fontSize = fontSize.sp,
                fontStyle = FontStyle.Italic
            )
        } else {
            captions.forEachIndexed { index, entry ->
                val opacity = calculateOpacity(index, captions.size)
                Text(
                    text = entry.text,
                    color = Color.White.copy(alpha = opacity),
                    fontSize = fontSize.sp,
                    lineHeight = (fontSize * 1.4).sp,
                    fontStyle = if (entry.isFinal) FontStyle.Normal else FontStyle.Italic,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .animateContentSize()
                )
            }
        }
    }
}

private fun calculateOpacity(index: Int, total: Int): Float {
    if (total <= 1) return 1f
    // Oldest line at 0.4, newest at 1.0
    val progress = index.toFloat() / (total - 1).toFloat()
    return 0.4f + (0.6f * progress)
}
