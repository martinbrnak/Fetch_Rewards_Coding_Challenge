package com.example.fetch_rewards_exercise

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET


@Parcelize
@JsonClass(generateAdapter = true)
data class DataApi(
    @Json(name = "id") val id: Long,
    @Json(name = "listId") val listId: Long,
    @Json(name = "name") val name: String?
) : Parcelable

interface FetchApi {
    @GET("hiring.json")
    suspend fun getData(): List<DataApi>
}

class DataRepository {
    private val fetchApi: FetchApi

    init {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        fetchApi = retrofit.create<FetchApi>()
    }
    suspend fun fetchData() = fetchApi.getData()
}

