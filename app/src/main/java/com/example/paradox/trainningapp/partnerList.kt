package com.example.paradox.trainningapp

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class partnerList(private val context: Activity, internal var partnership: List<partnership>) : ArrayAdapter<partnership>(context, R.layout.partnership_layout_view, partnership) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem = inflater.inflate(R.layout.partnership_layout_view, null, true)

        val textViewSchool = listViewItem.findViewById(R.id.txtchoolId) as TextView
        val textViewOrganization = listViewItem.findViewById(R.id.txtorganizationId) as TextView
        val textViewStartDate = listViewItem.findViewById(R.id.txtstartdateId) as TextView
        val textViewEndDate = listViewItem.findViewById(R.id.txtenddateId) as TextView
        val textViewNumber = listViewItem.findViewById(R.id.txtnumbersId) as TextView
        val textViewId=listViewItem.findViewById(R.id.idId) as TextView

        //val spOption=listViewItem.findViewById(R.id.sp_option) as Spinner

        val artist = partnership[position]
        textViewSchool.text = artist.school
        textViewOrganization.text = artist.organization
        textViewStartDate.text = artist.start_date
        textViewEndDate.text = artist.end_date
        textViewNumber.text = artist.number
        textViewId.text = artist.idp

        return listViewItem
    }

}