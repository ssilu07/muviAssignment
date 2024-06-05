package com.royals.muviassignment.model

data class TvResponse(
    val results: List<Show>,
)

data class Show(
    val id: Int,
    val name: String,
    val original_name: String,
    val overview: String,
    val poster_path: String
)