package com.example.paradox.trainningapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.paradox.trainningapp.Common.Common
import com.example.paradox.trainningapp.Model.APIResponse
import com.example.paradox.trainningapp.Remonte.IMyAPI
import kotlinx.android.synthetic.main.activity_register_student.*
import kotlinx.android.synthetic.main.spinner_layout.view.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RegisterStudent : AppCompatActivity() {

    internal lateinit var mService: IMyAPI

    private val URL_ROOT = "http://192.168.137.1:8080/TrainingApp/v1/?op="
    val URL_GET_STUDENT = URL_ROOT + "getStudent"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_student)

        //spinner
        //sOption = findViewById(R.id.) as ListView
        //mStudentList = mutableListOf<student>()
      //  loadArtists()

        //Init service

        val itemsOption = arrayOf("IG", "RTEL", "GIS", "GIMF", "SP")
        sp_option.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsOption)

        val itemsPromotion = arrayOf("G1", "G2", "G3", "L1", "L2")
        sp_promotion.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsPromotion)

        val itemsSchool = arrayOf("ISIG", "ISC", "ULPGL", "ISAM")
        sp_school.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsSchool)

        mService = Common.api

        txt_login.setOnClickListener { finish() }

        btn_new_student.setOnClickListener {
            try {

                createNewStudent(
                    fullname.text.toString(),
                   sp_promotion.selectedItem.toString(),
                    sp_option.selectedItem.toString(),
                    sp_school.selectedItem.toString(),
                    email.text.toString(),
                    password.text.toString()
                )
            } catch (ex: Exception) {
                Toast.makeText(this@RegisterStudent, ex.message, Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun createNewStudent(
        fullname: String,
        promotion: String,
        option: String,
        school: String,
        email: String,
        password: String
    ) {
        mService.registerStudent(fullname, promotion, option, school, email, password)
            .enqueue(object : retrofit2.Callback<APIResponse> {
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterStudent, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.body()!!.error) {
                        Toast.makeText(this@RegisterStudent, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this@RegisterStudent,
                            "Register Success !" + response.body()!!.uid,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }

            })
    }


}
