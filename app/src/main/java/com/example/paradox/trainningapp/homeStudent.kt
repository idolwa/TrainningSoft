package com.example.paradox.trainningapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

import kotlinx.android.synthetic.main.activity_home_student.*
import org.json.JSONException
import org.json.JSONObject

class homeStudent : AppCompatActivity() {

    private val URL_ROOT = "http://192.168.137.1:8080/TrainingApp/v1/?op="
    val URL_GET_PARTNERSHIP = URL_ROOT + "getPartnership"

    lateinit var mPartnerShipListView: ListView
    lateinit var mPartnerList :MutableList<partnership>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_student)

        mPartnerShipListView = findViewById(R.id.partnershiplistView) as ListView
        mPartnerList = mutableListOf<partnership>()
        loadArtists()

    }

    private fun loadArtists() {

        //val strinRequest = StringR

        // val stringRequest = StringRe

        val stringRequest = StringRequest(Request.Method.GET,
            URL_GET_PARTNERSHIP,
            Response.Listener<String>{ s ->
                try {
                    val obj = JSONObject(s)
                    if (!obj.getBoolean("error")){
                        val array = obj.getJSONArray("partnership")

                        for (i in 0..array.length() -1){
                            val objectArtist = array.getJSONObject(i)
                            val artist = partnership(
                                objectArtist.getString("school"),
                                objectArtist.getString("organization"),
                                 objectArtist.getString("start_date"),
                                objectArtist.getString("end_date"),
                                objectArtist.getString("numbers"),
                                objectArtist.getString("id")

                            )
                            mPartnerList.add(artist)
                            val adapter = partnerList(this,mPartnerList)
                            mPartnerShipListView.adapter =  adapter
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }catch (e: JSONException){e.printStackTrace()}
            }, Response.ErrorListener{ volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })

        val requestQueue= Volley.newRequestQueue(this)
        requestQueue.add<String>(stringRequest)
    }
}
