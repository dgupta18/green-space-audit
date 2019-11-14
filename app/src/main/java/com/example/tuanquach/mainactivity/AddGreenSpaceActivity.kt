package com.example.tuanquach.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*

class AddGreenSpaceActivity : AppCompatActivity() {

    internal lateinit var nameET: EditText
    internal lateinit var qualityTV: TextView
    internal lateinit var qualityRG: RadioGroup
    internal lateinit var recreationTV: TextView
    internal lateinit var recreationRG: RadioGroup
    internal lateinit var commentET: EditText
    internal lateinit var anonButton: RadioButton
    internal lateinit var saveButton: Button

    private val quality: String
        get() {
            when (qualityRG.checkedRadioButtonId) {
                R.id.qualityLow -> {
                    return "LOW"
                }
                R.id.qualityHigh -> {
                    return "HIGH"
                }
                else -> {
                    return "MED"
                }
            }
        }

    private val recreationType: String
        get() {
            when (recreationRG.checkedRadioButtonId) {
                R.id.peopleRec -> {
                    return "people"
                }
                else -> {
                    return "nature"
                }
            }
        }

    private val isAnon: Boolean
        get() {
            return anonButton.isChecked
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super .onCreate(savedInstanceState)
        setContentView(R.layout.activity_addgreenspace)

        nameET = findViewById<View>(R.id.nameET) as EditText
        qualityRG = findViewById<View>(R.id.qualityGroup) as RadioGroup
        qualityTV = findViewById<View>(R.id.quality) as TextView
        recreationTV = findViewById<View>(R.id.recreation) as TextView
        recreationRG = findViewById<View>(R.id.recreationGroup) as RadioGroup
        commentET = findViewById<View>(R.id.commentET) as EditText
        anonButton = findViewById<View>(R.id.anonComment) as RadioButton
        saveButton = findViewById<View>(R.id.save) as Button

        saveButton.setOnClickListener{
            save()
        }
    }

    private fun save() {
        val name = nameET.text.toString()
        val comment = commentET.text.toString()
        val author = if(anonButton.isChecked){ "Anonymous"} else {"name here"}
        var commentsMap = mutableMapOf<String, String>()

        // check to see if a name has been provided
        if(!TextUtils.isEmpty(name)) {
            // if the user wrote a comment, then add the comment to the comments map
            if(!TextUtils.isEmpty(comment)){
                commentsMap[author] = comment
            }

            // create a green space object
            val newGS = GreenSpace(name, 0.toFloat(), 0.toFloat(), quality, recreationType, commentsMap)

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