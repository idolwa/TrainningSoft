package com.example.paradox.trainningapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class studentShowActivity : AppCompatActivity() {

    private val URL_ROOT = "http://192.168.137.1:8080/TrainingApp/v1/?op="
    val URL_GET_STUDENT = URL_ROOT + "getStudent"

    lateinit var mStudentListView:ListView
    //private var mStudentList:MutableList<student>? = null
    lateinit var mStudentList :MutableList<student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_show)

        mStudentListView = findViewById(R.id.studentlistView) as ListView
        mStudentList = mutableListOf<student>()
        loadArtists()

    }

    private fun loadArtists() {

        //val strinRequest = StringR

           // val stringRequest = StringRe

        val stringRequest = StringRequest(Request.Method.GET,
            URL_GET_STUDENT,
            Response.Listener<String>{ s ->
                try {
                    val obj = JSONObject(s)
                    if (!obj.getBoolean("error")){
                        val array = obj.getJSONArray("student")

                        for (i in 0..array.length() -1){
                            val objectArtist = array.getJSONObject(i)
                            val artist = student(
                                objectArtist.getString("fullname"),
                                objectArtist.getString("school")
                            )
                            mStudentList.add(artist)
                           // val adapterq = studentsList(this, mStudentList)
                            val adapter = studentsList(this,mStudentList)
                            mStudentListView.adapter =  adapter
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show()
                    }
                }catch (e: JSONException){e.printStackTrace()}
            }, Response.ErrorListener{volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })

        val requestQueue= Volley.newRequestQueue(this)
        requestQueue.add<String>(stringRequest)
    }
}
