package com.example.green_space_audits.mainactivity

data class User (
    val userName: String,
    val userEmail: String,
    val userPassword: String,
    val userIsAdmin: Boolean,
    val uPoints: Int,
    val uGreenSpaces: ArrayList<String>,
    val badges: ArrayList<String>,
    val favorites: ArrayList<String>,
    val checkins: MutableMap<String, ArrayList<String>>
)
