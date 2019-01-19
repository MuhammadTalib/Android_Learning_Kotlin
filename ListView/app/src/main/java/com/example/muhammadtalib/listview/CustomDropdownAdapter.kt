package com.example.muhammadtalib.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CustomDropdownAdapter( con: Context,var listItemsTxt: ArrayList<UserModal>): BaseAdapter()
{
        val mInflater: LayoutInflater = LayoutInflater.from(con)
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
        {
            val view: View
            val vh: ItemRowHolder
            if (convertView == null)
            {
                view = mInflater.inflate(R.layout.view_drop_down_menu, parent, false)
                vh = ItemRowHolder(view)
                view?.tag = vh
            }
            else
            {
                view = convertView
                vh = view.tag as ItemRowHolder
            }

            val params = view.layoutParams
            params.height = 60
            view.layoutParams = params
            val userData = listItemsTxt[position]
            vh.label.text = userData.name
            vh.labell.setImageResource(userData.img)
            return view
        }

    override fun getItem(position: Int): Any? {

        return null

    }

    override fun getItemId(position: Int): Long {

        return 0

    }

    override fun getCount(): Int {
        return listItemsTxt.size
    }

    private class ItemRowHolder(row: View?) {

        val labell:ImageView
        init {
            this.labell=row?.findViewById(R.id.imgDropDownMenuIcon) as ImageView
        }

        val label: TextView
        init {
            this.label = row?.findViewById(R.id.txtDropDownLabel) as TextView
        }
    }
}