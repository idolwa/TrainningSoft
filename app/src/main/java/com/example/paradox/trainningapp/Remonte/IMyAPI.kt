package com.example.paradox.trainningapp.Remonte


import com.example.paradox.trainningapp.Model.APIResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface IMyAPI {
    @FormUrlEncoded
    @POST(value = "register.php")
    fun registerUser(@Field("name") name:String,@Field("email") email: String,
                     @Field("password") password: String):Call<APIResponse>

    @FormUrlEncoded
    @POST(value = "login.php")
    fun loginUser(@Field("email") email: String,@Field("password") password: String):Call<APIResponse>

    @FormUrlEncoded
    @POST(value = "register_student.php")
    fun registerStudent(@Field("fullname") fullname: String,@Field("promotion") promotion: String,
                        @Field("option") option: String,@Field("school") school: String,
                        @Field("email") email: String,@Field("password") password: String):Call<APIResponse>

    @FormUrlEncoded
    @POST(value = "register_partnership.php")
    fun registerParnership(@Field("school") schoolP: String,@Field("organization") organizationP: String,
                        @Field("start_date") startdateP: String,@Field("end_date") enddateP: String,
                        @Field("number") numberP: String):Call<APIResponse>


    @FormUrlEncoded
    @POST(value = "register_organization.php")
    fun registerOrganization(@Field("name") fullname: String,@Field("rccm") promotion: String,
                        @Field("adress") option: String):Call<APIResponse>

    @FormUrlEncoded
    @POST(value = "register_school.php")
    fun registerSchool(@Field("student") fullname: String,@Field("subject") promotion: String,
                       @Field("partnership") option: String):Call<APIResponse>

    @FormUrlEncoded
    @POST(value = "registered.php")
    fun registered(@Field("name") fullname: String,@Field("rccm") promotion: String,
                       @Field("adress") option: String):Call<APIResponse>

    @FormUrlEncoded
    @POST(value = "login_student.php")
    fun loginStudent(@Field("email") email: String,@Field("password") password: String):Call<APIResponse>

    @FormUrlEncoded
    @POST(value = "login.php")
    fun loginAdmin(@Field("email") email: String,@Field("password") password: String):Call<APIResponse>



/*
    @FormUrlEncoded
    @GET(value = "upload_student.php")
    fun uploadStudent(@Field(""): Call<APIResponse>)
*/
}