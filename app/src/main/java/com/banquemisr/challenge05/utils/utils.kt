package com.banquemisr.challenge05.utils

import androidx.compose.ui.graphics.Color

fun Color.Companion.fromHex(colorString: String) = Color(android.graphics.Color.parseColor("#$colorString"))
const val base_image="https://image.tmdb.org/t/p/w200/"
const val base_image_poster="https://image.tmdb.org/t/p/w500/"