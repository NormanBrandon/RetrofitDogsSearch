package com.nprmanbrandons11.petsretrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RVAdapter(val images:List<String>) :RecyclerView.Adapter<DogVH>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogVH {
        val layoutInflayer=LayoutInflater.from(parent.context)
        return  DogVH(layoutInflayer.inflate(R.layout.item_dog,parent,false))
    }

    override fun onBindViewHolder(holder: DogVH, position: Int) {
        val item = images[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return images.size
    }

}