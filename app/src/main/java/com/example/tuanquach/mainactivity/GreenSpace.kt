package com.example.tuanquach.mainactivity

data class GreenSpace (val gsName: String,
                       val gsLat: Float,
                       val gsLong: Float,
                       val gsQuality: String,
                       val isGSActive: Boolean,
                       val gsComments: ArrayList<String>)

// TODO: do we want to change comments to a map with the user who commented as the key and the comment as the value?
// I don't know if kotlin maps have mutable length or not