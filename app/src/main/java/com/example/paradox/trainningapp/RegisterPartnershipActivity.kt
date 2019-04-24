package com.example.paradox.trainningapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.paradox.trainningapp.Common.Common
import com.example.paradox.trainningapp.Model.APIResponse
import com.example.paradox.trainningapp.Remonte.IMyAPI
import kotlinx.android.synthetic.main.activity_register_partnership.*
import retrofit2.Call
import retrofit2.Response

class RegisterPartnershipActivity : AppCompatActivity() {
    internal lateinit var mService: IMyAPI

    internal lateinit var sOption: ListView
    internal lateinit var adapter: MutableList<student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_partnership)

        val itemsSchool = arrayOf("ISIG", "ISC", "ULPGL", "ISAM")
        sp_school.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsSchool)

        val itemsOrganization = arrayOf("UNPP", "DGI", "DGRAD", "SNEL", "DPS")
        sp_organization.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemsOrganization)



        mService = Common.api


        btn_new_partenership.setOnClickListener {
            try {
                createNewPArtner(

                    sp_school.selectedItem.toString(),
                    sp_organization.selectedItem.toString(),
                    start_date.text.toString(),
                    end_date.text.toString(),
                    txt_number.text.toString()
                )
            } catch (ex: Exception) {
            Toast.makeText(this@RegisterPartnershipActivity, ex.message, Toast.LENGTH_SHORT).show()
        }

        }
        txt_return.setOnClickListener {
            finish()
        }

    }

    private fun createNewPArtner(
        school: String,
        organization: String,
        startdate: String,
        enddate: String,
        number: String
    ) {
        mService.registerParnership(school, organization, startdate, enddate, number)
            .enqueue(object : retrofit2.Callback<APIResponse> {
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterPartnershipActivity, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if (response.body()!!.error) {
                        Toast.makeText(this@RegisterPartnershipActivity, response.body()!!.error_msg, Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            this@RegisterPartnershipActivity,
                            "Register Success !" + response.body()!!.uid,
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }

            })
    }
}
