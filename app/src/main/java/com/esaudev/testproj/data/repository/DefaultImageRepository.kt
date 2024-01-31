package com.esaudev.testproj.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.esaudev.testproj.data.remote.ImageApi
import com.esaudev.testproj.domain.repository.ImagesRepository
import javax.inject.Inject

class DefaultImageRepository @Inject constructor(
    private val imageApi: ImageApi
) : ImagesRepository {

    override suspend fun getBitmapFromUrl(url: String): Result<Bitmap> {
        val response = imageApi.getImagePayload(url)
        if (response.isSuccessful) {
            val responseStream = response.body()?.byteStream() ?: return Result.failure(Exception())
            val bitmap = BitmapFactory.decodeStream(responseStream)
            return Result.success(bitmap)
        } else {
            return Result.failure(Exception())
        }
    }
}