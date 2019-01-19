package com.example.muhammadtalib.staggedrecyclerview


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


class StaggedAdapter(
                     val data:ArrayList<post>,
                     val onItemClick:(Int)->Unit): RecyclerView.Adapter<StaggedViewHolder>() {
    override fun getItemCount(): Int =data.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StaggedViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.stagged_item_view,parent,false)
        return StaggedViewHolder(itemView)
    }
    override fun onBindViewHolder(p0: StaggedViewHolder, p1: Int) {
        p0.myImage.setImageResource(data[p1].Image)
        p0.myText.text=data[p1].Text
        p0.itemView.setOnClickListener {
            onItemClick(p1)

        }

}
}