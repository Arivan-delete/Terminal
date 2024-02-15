package com.example.terminal.data

import retrofit2.http.GET

interface ApiService {

    @GET("aggs/ticker/AAPL/range/1/hour/2022-01-09/2023-01-09?adjusted=true&sort=desc&limit=50000&apiKey=M_XyMdWt5TsQfY_CdNoEYNqJJ1h9EvzQ")
    suspend fun loadBars(): Result
}