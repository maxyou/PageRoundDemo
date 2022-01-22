package com.maxporj.pagerounddemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlin.math.ceil

@Composable
fun PageRound(
    current: Int,
    ext: Int,
    totalDocs: Int,
    pageSize: Int,
    onClick: (Int) -> Unit,
    RoundButton: @Composable (Int, Boolean, (Int) -> Unit)->Unit,
    RoundInterval: @Composable () -> Unit,
) {

    val maxRight = ceil(totalDocs.toDouble() / pageSize.toDouble())
    val maxRightInt = maxRight.toInt()

    val ba = calcPaginateArray(current = current, ext = ext, maxRight = maxRightInt)

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

//        val modifier = Modifier.width(0.dp).weight(1f)
        val modifier = Modifier.weight(1f)

        if (ba[0] != 1) {
            Box(modifier = modifier) { RoundButton(1, 1 == current, onClick) }
            if (ba[0] != 2) {
                Box(modifier = modifier) { RoundInterval() }
            }
        }

        ba.map {
            Box(modifier = modifier) {
                RoundButton(
                    it,
                    it == current,
                    onClick
                )
            }
        }

        if (ba[ba.size - 1] != maxRightInt) {
            if (ba[ba.size - 1] != maxRightInt - 1) {
                Box(modifier = modifier) { RoundInterval() }
            }
            Box(modifier = modifier) {
                RoundButton(
                    maxRightInt,
                    maxRightInt == current,
                    onClick
                )
            }
        }
    }
}


fun calcPaginateArray(current: Int, ext: Int, maxRight: Int): MutableList<Int> {

    val ba = mutableListOf<Int>(current)
    for (i in 0 until ext) {
        ba.add((current + 1) + i)
    }
    for (i in 0 until ext) {
        ba.add(0, (current - 1) - i)
    }

    while (true) {
        if (ba[0] < 1) {
            ba.removeFirstOrNull()
            val addRight = ba[ba.size - 1] + 1
            if (addRight <= maxRight) {
                ba.add(addRight)
            }
        } else {
            break
        }
    }

    while (true) {
        if (ba[ba.size - 1] > maxRight) {
            ba.removeLastOrNull()
            val addLeft = ba[0] - 1
            if (addLeft >= 1) {
                ba.add(0, addLeft)
            }
        } else {
            break
        }
    }

    return ba
}