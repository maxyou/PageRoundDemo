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

    val _ext = if (ext < 1) 2 else ext
    val _pageSize = if (pageSize < 1) 10 else pageSize

    val maxRight = ceil(totalDocs.toDouble() / _pageSize.toDouble())
    val maxRightInt = maxRight.toInt()

    val _maxRightInt = if(maxRightInt < 1) 1 else maxRightInt
    val _current = if (current < 1) 1 else if (current > _maxRightInt) _maxRightInt else current

    val ba = calcPaginateArray(current = _current, ext = _ext, maxRight = _maxRightInt)

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

//        val modifier = Modifier.width(0.dp).weight(1f)
        val modifier = Modifier.weight(1f)

        if (ba[0] != 1) {
            Box(modifier = modifier) { RoundButton(1, 1 == _current, onClick) }
            if (ba[0] != 2) {
                Box(modifier = modifier) { RoundInterval() }
            }
        }

        ba.map {
            Box(modifier = modifier) {
                RoundButton(
                    it,
                    it == _current,
                    onClick
                )
            }
        }

        if (ba[ba.size - 1] != _maxRightInt) {
            if (ba[ba.size - 1] != _maxRightInt - 1) {
                Box(modifier = modifier) { RoundInterval() }
            }
            Box(modifier = modifier) {
                RoundButton(
                    _maxRightInt,
                    _maxRightInt == _current,
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