package com.example.green_space_audits.mainactivity

data class GreenSpace (val gsName: String = "",
                       val gsCreator: String = "",
                       val gsLat: Float = 0.toFloat(),
                       val gsLong: Float = 0.toFloat(),
                       val gsAcres: Float = 0.toFloat(),
                       val gsQuality: Quality = Quality.LOW,
                       val gsType: Recreation = Recreation.NATUREBASED,
                       val gsComments: MutableMap<String, Comment> = mutableMapOf<String, Comment>(),
                       val isQuiet: Boolean = false,
                       val isNearHazards: Boolean = false)

enum class Quality(val displayStr: String) {
    LOW("Low"), MED("Medium"), HIGH("High")

}

enum class Recreation(val displayStr: String) {
    PEOPLEPOWERED("People-powered"), NATUREBASED("Nature-based")
}
