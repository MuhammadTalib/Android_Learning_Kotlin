package com.example.talib.gson_task

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MarvelAdapter(val data:ArrayList<MarvelCharacters>): RecyclerView.Adapter<MarvelCharactersViewHolder>()
{
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MarvelCharactersViewHolder = MarvelCharactersViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.marvelcharacter_item,p0,false))
    override fun getItemCount(): Int = data.size
    override fun onBindViewHolder(p0: MarvelCharactersViewHolder, p1: Int) = p0.bind(data[p1])
}
