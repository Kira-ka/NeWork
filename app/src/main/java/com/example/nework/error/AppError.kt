package com.example.nework.error

import java.io.IOException
import java.sql.SQLException

sealed class AppError(var code: String) : RuntimeException() {
    companion object {
        fun from(e: Throwable) = when(e){
            is ApiError -> e
            is IOException -> NetworkError
            is SQLException -> DbError
            else -> UnknownError

        }
    }

}

class ApiError(val status: Int, code: String) : AppError(code)
object NetworkError : AppError("error_network")
object DbError : AppError("error_db")
object UnknownError : AppError("unknown_error")

