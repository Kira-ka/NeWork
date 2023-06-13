package com.example.nework.repository

import com.example.nework.api.ApiService
import com.example.nework.dto.Token
import com.example.nework.error.ApiError
import com.example.nework.error.AppError
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryAuthImpl @Inject constructor(private val apiService: ApiService) : RepositoryAuth {

    override suspend fun registration(login: String, pass: String, name: String): Token {
        var body = Token(0, "0", null)
        try {
            val response = apiService.registerUser(login, pass, name)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            body = response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: Throwable) {
            AppError.from(e)
        }
        return body
    }

    override suspend fun authentication(login: String, pass: String): Token {
        var body = Token(0, "0", null)
        try {
            val response = apiService.updateUser(login, pass)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            body = response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: Throwable) {
            AppError.from(e)
        }
        return body
    }


}
