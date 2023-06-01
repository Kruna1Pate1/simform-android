package com.krunal.demo.uicomponents.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import com.krunal.demo.R
import com.krunal.demo.uicomponents.models.Theme

class ThemeAdapter(
    private val context: Context,
    private val themes: List<Theme>
) : BaseAdapter() {
    override fun getCount(): Int = themes.size

    override fun getItem(position: Int): Any? = null

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.theme_layout, null)
        val tvName = view.findViewById<TextView>(R.id.tvThemeName)
        val viewTheme = view.findViewById<MaterialCardView>(R.id.cardThemeColor)

        tvName.text = themes[position].name
        viewTheme.setCardBackgroundColor(themes[position].color)
        return view
    }
}