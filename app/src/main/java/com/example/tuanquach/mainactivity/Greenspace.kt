package com.example.green_space_audits.mainactivity

data class Greenspace (
    val user: String = "",
    val name: String = "",
    val latitude: Float = 0f,
    val longitude: Float = 0f,
    val acres: Int = 0,
    val comments: ArrayList<String> = ArrayList(),
    val ratings: Map<Int, String> = HashMap(),
    val isQuiet: Boolean = false,
    val isNearHazards: Boolean = false,
    val isNaturey: Boolean = true
)