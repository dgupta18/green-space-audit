package com.example.green_space_audits.mainactivity

data class GreenSpace (val gsName: String,
                       val gsCreator: String,
                       val gsLat: Float,
                       val gsLong: Float,
                       val gsAcres: Float,
                       val gsQuality: Quality,
                       val gsType: Recreation,
                       val gsComments: ArrayList<Comment>,
                       val isQuiet: Boolean,
                       val isNearHazards: Boolean)

enum class Quality {
    LOW, MED, HIGH
}

enum class Recreation {
    PEOPLEPOWERED, NATUREBASED
}
