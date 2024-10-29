package com.example.feedm.ui.view.managementClasses.petsAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.example.feedm.data.local.PetModel
import com.example.feedm.R
import com.example.feedm.domain.model.Pet

class MyPetsAdapter(private val context: Context,
                    private var items: List<Pet>, private val onItemClick:(Pet)->Unit,
                    private val onItemLongClick:(View, Pet, Int)->Unit): RecyclerView.Adapter<PetViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.pa_item_recyclerview, parent, false)
        return PetViewHolder(view)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        val item = items[position]
        if (item.animal.equals("dog"))holder.imageView.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.img_dog_illustration))
        else holder.imageView.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.gato))
        holder.name.text = item.nombre
        holder.itemView.setOnClickListener{onItemClick(item)}
        holder.itemView.setOnLongClickListener{
            onItemLongClick(holder.itemView,item,position)
            true}
    }

    override fun getItemCount(): Int {
       return items.size
    }

    fun notifyDataChanged(){
        notifyDataSetChanged()
    }



}