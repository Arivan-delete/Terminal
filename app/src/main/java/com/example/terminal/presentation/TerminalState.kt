package com.example.terminal.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.terminal.data.Bar
import kotlin.math.roundToInt

data class TerminalState(
    val barList: List<Bar>,
    val visibleBarsCount: Int = 100,
    val terminalWidth: Float = 0f,
    val scrolledBy: Float = 0f
) {
    val barWidth: Float
        get() = terminalWidth / visibleBarsCount

    val visibleBars: List<Bar>
        get() {
            val startIndex = (scrolledBy / barWidth).roundToInt().coerceAtLeast(0)
            val endIndex = (startIndex + visibleBarsCount).coerceAtMost(barList.size)
            return barList.subList(startIndex, endIndex)
        }

    companion object {

        val Saver: Saver<MutableState<TerminalState>, Any> = listSaver(
            save = {
                val testData = it.value
                listOf(testData.barList, testData.visibleBarsCount, testData.terminalWidth, testData.scrolledBy)
            },
            restore = {
                val testData = TerminalState(
                    barList = it[0] as List<Bar>,
                    visibleBarsCount = it[1] as Int,
                    terminalWidth = it[2] as Float,
                    scrolledBy = it[3] as Float
                )
                mutableStateOf(testData)
            }
        )
    }
}

@Composable
fun rememberTerminalState(bars: List<Bar>): MutableState<TerminalState> {
    return rememberSaveable(saver = TerminalState.Saver) {
        mutableStateOf(TerminalState(barList = bars))
    }
}