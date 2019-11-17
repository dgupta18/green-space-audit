package com.example.green_space_audits.mainactivity

data class GreenSpace (val gsName: String,
                       val gsCreator: String,
                       val gsLat: Float,
                       val gsLong: Float,
                       val gsAcres: Float,
                       val gsQuality: Quality,
                       val gsType: Recreation,
                       val gsComments: MutableMap<String, String>,
                       val isQuiet: Boolean,
                       val isNearHazards: Boolean)

enum class Quality {
    LOW, MED, HIGH
}

enum class Recreation {
    PEOPLEPOWERED, NATUREBASED
}

// TODO: do we want to change comments to a map with the user who commented as the key and the comment as the value?
// I don't know if kotlin maps have mutable length or not