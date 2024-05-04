package com.kevintorch.androidktx

fun lerp(
    outputMin: Float,
    outputMax: Float,
    inputMin: Float,
    inputMax: Float,
    value: Float
): Float {
    return if (value <= inputMin) {
        outputMin
    } else {
        if (value >= inputMax) outputMax else lerp(
            outputMin, outputMax,
            (value - inputMin) / (inputMax - inputMin)
        )
    }
}

fun lerp(startValue: Float, endValue: Float, fraction: Float): Float {
    return startValue + fraction * (endValue - startValue)
}

fun lerp(startValue: Int, endValue: Int, fraction: Float): Int {
    return startValue + Math.round(fraction * (endValue - startValue).toFloat())
}