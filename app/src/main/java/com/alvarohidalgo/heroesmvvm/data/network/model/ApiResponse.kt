package com.alvarohidalgo.heroesmvvm.data.network.model

data class ApiResponse<T>(
    val code: String, val status: String, val copyright: String, val data: Data<T>
) {
    fun getData(): List<T> = data.results
}

data class Data<T>(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<T>,
    val total: String
)