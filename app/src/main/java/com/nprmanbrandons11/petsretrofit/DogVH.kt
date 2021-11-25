package com.nprmanbrandons11.petsretrofit

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nprmanbrandons11.petsretrofit.databinding.ItemDogBinding
import com.squareup.picasso.Picasso

class DogVH(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemDogBinding.bind(view)
    fun bind(image:String){
        Picasso.get().load(image).into(binding.ivdog)
    }

}