package com.example.paradox.trainningapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home_admin.*

class homeAdmin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_admin)

        frmschool.setOnClickListener {
            startActivity(Intent(this@homeAdmin,registerSchool::class.java))
        }

        frmOrganisation.setOnClickListener {
            startActivity(Intent(this@homeAdmin,RegisterOrganizationActivity::class.java))
        }

        frmPartner.setOnClickListener {
            startActivity(Intent(this@homeAdmin,RegisterPartnershipActivity::class.java))
        }
        frmLogout.setOnClickListener {
            finish()
        }

    }
}
