package com.example.green_space_audits.mainactivity

data class User (
    val userName: String ="",
    val userEmail: String = "",
    val userPassword: String = "",
    val isAdmin: Boolean = false,
    val points: Int = 0,
    val greenspaces: ArrayList<String> = ArrayList(),
    val badges: ArrayList<String> = ArrayList(),
    val favorites: ArrayList<String> = ArrayList(),
    val checkins: Map<String, ArrayList<String>> = HashMap()
)