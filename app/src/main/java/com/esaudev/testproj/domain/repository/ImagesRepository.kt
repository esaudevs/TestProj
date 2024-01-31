package com.esaudev.testproj.domain.repository

import android.graphics.Bitmap

interface ImagesRepository {

    suspend fun getBitmapFromUrl(url: String): Result<Bitmap>
}