package com.example.paradox.trainningapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.paradox.trainningapp.Common.Common
import com.example.paradox.trainningapp.Model.APIResponse
import com.example.paradox.trainningapp.Remonte.IMyAPI
import kotlinx.android.synthetic.main.activity_register_school.*
import retrofit2.Call
import retrofit2.Response

class registerSchool : AppCompatActivity() {
    internal lateinit var mService: IMyAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_school)

        mService = Common.api

        btn_addSchool.setOnClickListener {
            try {
                createNewPArtner(

                    txt_school.text.toString(),
                    txt_rccm.text.toString(),
                    txt_address.text.toString()
                )
            } catch (ex: Exception) {
                Toast.makeText(this@registerSchool, ex.message, Toast.LENGTH_SHORT).show()
            }
        }

        txt_return.setOnClickListener {
            finish()
        }
    }

    private fun createNewPArtner(
        school: String,
        organization: String,
        startdate: String

    ) {
        mService.registerSchool(school, organization, startdate)
            .enqueue(object : retrofit2.Callback<APIResponse> {
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@registerSchool, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.body()!!.error) {
                        Toast.makeText(this@registerSchool, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this@registerSchool,
                            "Register Success !" + response.body()!!.uid,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }

            })
    }

}
