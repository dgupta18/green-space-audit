package com.example.green_space_audits.mainactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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
    internal lateinit var database: DatabaseReference

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

        nameET = findViewById<EditText>(R.id.nameET)
        qualityRG = findViewById<RadioGroup>(R.id.qualityGroup)
        qualityTV = findViewById<TextView>(R.id.quality)
        recreationTV = findViewById<TextView>(R.id.recreation)
        recreationRG = findViewById<RadioGroup>(R.id.recreationGroup)
        commentET = findViewById<EditText>(R.id.comment)
        anonButton = findViewById<RadioButton>(R.id.anonComment)
        saveButton = findViewById<Button>(R.id.save)
        hazardsRG = findViewById<RadioGroup>(R.id.hazardsGroup)
        quietRG = findViewById<RadioGroup>(R.id.quietGroup)
        acresET = findViewById<EditText>(R.id.acresET)

        // TODO: What path do we want?
        database = FirebaseDatabase.getInstance().getReference("GreenSpaces")

        user = FirebaseAuth.getInstance().currentUser!!.uid


        saveButton.setOnClickListener{
            save()
        }
    }

    private fun save() {
        val name = nameET.text.toString()
        val comment = commentET.text.toString()
        val acresString = acresET.text.toString()
        var commentsMap = mutableMapOf<String, String>()

        // check to see if a name has been provided
        if(!TextUtils.isEmpty(name)) {

            // check to see if the acreage has been provided
            if(!TextUtils.isEmpty(acresString)) {
                val acres = acresString.toFloat()
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
                val newGS = GreenSpace(name, user, 0.toFloat(), 0.toFloat(), acres, quality, recreationType, commentsMap, isQuiet, isNearHazards)

                // TODO: add newGS to the database
                // I don't want to actually add anything until we decide what structure we want
                Log.i("NEW GREEN SPACE", "$newGS")

                //getting a unique id using push().getKey() method
                //it will create a unique id and we will use it as the primary key for our green space
                val greenSpaceId = database.push().key

                // add the new green space to the database
                database.child(greenSpaceId!!).setValue(newGS)

                Toast.makeText(this, "Green space added", Toast.LENGTH_LONG).show()

                // TODO: which activity do we want to launch?
                val enter = Intent(this@AddGreenSpaceActivity, MapsActivity::class.java)
                startActivity(enter)
            } else {
                Toast.makeText(this, "Please enter the acreage", Toast.LENGTH_LONG).show()
            }

        } else {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show()
        }

    }

}