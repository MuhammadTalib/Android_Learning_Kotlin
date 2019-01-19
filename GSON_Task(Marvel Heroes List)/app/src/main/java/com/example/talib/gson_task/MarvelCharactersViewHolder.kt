package com.example.talib.gson_task

import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MarvelCharactersViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val name:TextView=itemView.findViewById(R.id.name)
    val i_mage:ImageView=itemView.findViewById(R.id.image)
    val publisher:TextView=itemView.findViewById(R.id.publisher)
    val createdby:TextView=itemView.findViewById(R.id.createdby)
    val realname:TextView=itemView.findViewById(R.id.realname)
    val team:TextView=itemView.findViewById(R.id.team)


    fun bind(character: MarvelCharacters)
    {
        name.text =character.name
        publisher.text="Publisher: "+character.publisher
        createdby.text="Created By: "+character.createdby
        realname.text="Real Name: "+character.realname
        team.text="Team: "+character.team

        Glide.with(itemView.context).applyDefaultRequestOptions(RequestOptions().apply {
            placeholder(CircularProgressDrawable(itemView.context).apply {
                strokeWidth = 2f
                centerRadius = 50f
                start()
            })
        }).load(character.imageurl).into(i_mage)
    }
}


