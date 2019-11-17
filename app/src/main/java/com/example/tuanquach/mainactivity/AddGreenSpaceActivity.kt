package com.example.green_space_audits.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.google.firebase.auth.FirebaseAuth

class AddGreenSpaceActivity : AppCompatActivity() {

    internal lateinit var nameET: EditText
    internal lateinit var qualityTV: TextView
    internal lateinit var qualityRG: RadioGroup
    internal lateinit var recreationTV: TextView
    internal lateinit var recreationRG: RadioGroup
    internal lateinit var commentET: EditText
    internal lateinit var anonButton: RadioButton
    internal lateinit var saveButton: Button
    private lateinit var user: String
    internal lateinit var quietRG: RadioGroup
    internal lateinit var hazardsRG: RadioGroup
    internal lateinit var acresET: EditText

    private val quality: Quality
        get() {
            when (qualityRG.checkedRadioButtonId) {
                R.id.qualityLow -> {
                    return Quality.LOW
                }
                R.id.qualityHigh -> {
                    return Quality.HIGH
                }
                else -> {
                    return Quality.MED
                }
            }
        }

    private val recreationType: Recreation
        get() {
            when (recreationRG.checkedRadioButtonId) {
                R.id.peopleRec -> {
                    return Recreation.PEOPLEPOWERED
                }
                else -> {
                    return Recreation.NATUREBASED
                }
            }
        }

    private val isQuiet: Boolean
        get() {
            when (quietRG.checkedRadioButtonId) {
                R.id.yesQuiet -> {
                    return true
                }
                else -> {
                    return false
                }
            }
        }

    private val isNearHazards: Boolean
        get() {
            when (hazardsRG.checkedRadioButtonId) {
                R.id.yesHazards -> {
                    return true
                }
                else -> {
                    return false
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super .onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgreenspace)

        nameET = findViewById(R.id.nameET) as EditText
        qualityRG = findViewById(R.id.qualityGroup) as RadioGroup
        qualityTV = findViewById(R.id.quality) as TextView
        recreationTV = findViewById(R.id.recreation) as TextView
        recreationRG = findViewById(R.id.recreationGroup) as RadioGroup
        commentET = findViewById(R.id.comment) as EditText
        anonButton = findViewById(R.id.anonComment) as RadioButton
        saveButton = findViewById(R.id.save) as Button
        hazardsRG = findViewById(R.id.hazardsGroup) as RadioGroup
        quietRG = findViewById(R.id.quietGroup) as RadioGroup
        acresET = findViewById(R.id.acresET) as EditText

        user = FirebaseAuth.getInstance().currentUser!!.uid


        saveButton.setOnClickListener{
            save()
        }
    }

    private fun save() {
        val name = nameET.text.toString()
        val comment = commentET.text.toString()
//        val acres = commentET.text.toFloat()
        // TODO: figure out how to read in acres as a float instead of string
        var commentsMap = mutableMapOf<String, String>()

        // check to see if a name has been provided
        if(!TextUtils.isEmpty(name)) {
            // if the user wrote a comment, then add the comment to the comments map
            if(!TextUtils.isEmpty(comment)){
                if(anonButton.isChecked){
                    commentsMap["Anonymous"] = comment
                } else {
                    commentsMap[user] = comment
                }

            }

            // create a green space object
            // TODO: figure out how to get the lat long values
            val newGS = GreenSpace(name, user, 0.toFloat(), 0.toFloat(), 0.toFloat(), quality, recreationType, commentsMap, isQuiet, isNearHazards)

            // TODO: add newGS to the database
            // I don't want to actually add anything until we decide what structure we want

            Toast.makeText(this, "Green space added", Toast.LENGTH_LONG).show()

            // TODO: start intent to lauch another activity after green space is saved
            // which activity do we want to launch?
        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show()
        }

    }

}