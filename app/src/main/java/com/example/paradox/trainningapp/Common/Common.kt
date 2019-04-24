package com.example.paradox.trainningapp.Common

import com.example.paradox.trainningapp.Remonte.IMyAPI
import com.example.paradox.trainningapp.Remonte.RetrofitClient


object Common {
    val BASE_URL = "http://192.168.137.1:8080/TrainingApp/"

    val api: IMyAPI
    get() = RetrofitClient.getClient(BASE_URL).create(IMyAPI::class.java)
}