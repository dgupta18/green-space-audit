package com.example.tuanquach.mainactivity

data class GreenSpace (
    val gsName: String,
    val gsCreator: String,
    val gsLat: Float,
    val gsLong: Float,
    val gsAcres: Float,
    val gsQuality: String, // low, med, high
    val gsType: String, // nature-based, people-powered
    val gsComments: MutableMap<String, String>,
    val isQuiet: Boolean,
    val isNearHazards: Boolean
)