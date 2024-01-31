package com.esaudev.testproj.data.remote

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ImageApi {

    @GET
    suspend fun getImagePayload(@Url url: String): Response<ResponseBody>
}