package com.example.paradox.trainningapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.paradox.trainningapp.Common.Common
import com.example.paradox.trainningapp.Model.APIResponse
import com.example.paradox.trainningapp.Remonte.IMyAPI
import kotlinx.android.synthetic.main.activity_login_admin.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginAdmin : AppCompatActivity() {

    internal lateinit var mService: IMyAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_admin)

        mService = Common.api

        btn_login.setOnClickListener { authenticateAdmin(email.text.toString(),password.text.toString()) }

        txt_student.setOnClickListener {
            finish()
        }
    }

    private fun authenticateAdmin(email: String, password: String) {
        mService.loginAdmin(email,password)
            .enqueue(object: Callback<APIResponse> {
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {

                    Toast.makeText(this@LoginAdmin,t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.body()!!.error){
                        Toast.makeText(this@LoginAdmin,response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@LoginAdmin,"Login Success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginAdmin,homeAdmin::class.java))
                    }
                }

            })
    }
}
