package com.example.paradox.trainningapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class studentsList (private val context:Activity, internal var student: List<student>) : ArrayAdapter<student>(context, R.layout.student_layout_view, student)
{
   override fun getView(position: Int, convertView: View?, parent: ViewGroup):View{
       val inflater = context.layoutInflater
       val listViewItem = inflater.inflate(R.layout.student_layout_view, null,true)

       val textViewName = listViewItem.findViewById(R.id.studentNameId) as TextView
       val textViewSchool=listViewItem.findViewById(R.id.studentSchoold) as TextView

       //val spOption=listViewItem.findViewById(R.id.sp_option) as Spinner

       val mystudent = student[position]
       textViewName.text = mystudent.name
       textViewSchool.text = mystudent.school

       return listViewItem
   }


























}