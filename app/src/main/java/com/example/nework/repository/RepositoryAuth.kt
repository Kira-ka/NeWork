package com.example.nework.repository

import com.example.nework.dto.Token

interface RepositoryAuth {
    suspend fun registration(login: String, pass: String, name: String): Token
    suspend fun authentication(login: String, pass: String): Token
}
