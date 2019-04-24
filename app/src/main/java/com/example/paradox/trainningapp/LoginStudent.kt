package com.example.paradox.trainningapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.paradox.trainningapp.Common.Common
import com.example.paradox.trainningapp.Model.APIResponse
import com.example.paradox.trainningapp.Remonte.IMyAPI
import kotlinx.android.synthetic.main.activity_login_student.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginStudent : AppCompatActivity() {

   // internal var list:ArrayList<String>

    internal lateinit var mService:IMyAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_student)

        mService = Common.api

        btn_login.setOnClickListener { authenticateStudent(email.text.toString(), password.text.toString()) }

        txt_register.setOnClickListener { startActivity(Intent(this@LoginStudent,RegisterStudent::class.java))}

        loginadmin.setOnClickListener {
            startActivity(Intent(this@LoginStudent,LoginAdmin::class.java))
        }
    }
    private fun authenticateStudent(email: String, password: String) {
        mService.loginStudent(email,password)
            .enqueue(object: Callback<APIResponse> {
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {

                    Toast.makeText(this@LoginStudent,t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.body()!!.error){
                        Toast.makeText(this@LoginStudent,response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@LoginStudent,"Login Success", Toast.LENGTH_SHORT).show()

                        startActivity(Intent(this@LoginStudent,PartnershipShowActivity::class.java))
                    }
                }

            })
    }
}
