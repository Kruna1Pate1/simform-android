package com.krunal.demo.uicomponents.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.krunal.demo.R

class TimezoneAdapter(private val context: Context, private val timezones: Array<String>): BaseAdapter() {

    override fun getCount() = timezones.size

    override fun getItem(position: Int) = null

    override fun getItemId(position: Int): Long = 0

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.timezone_spinner, null)
        val tvName = view.findViewById<TextView>(R.id.tvTimezone)
        tvName.text = timezones[position]

        return view
    }
}