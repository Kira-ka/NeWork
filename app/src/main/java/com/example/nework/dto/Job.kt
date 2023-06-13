package com.example.nework.dto

data class Job(
    override val id: Long,
    val name: String,
    val position: String,
    val start: String,
    val finish: String? = null,
    val link: String? = null,
): Minimal
