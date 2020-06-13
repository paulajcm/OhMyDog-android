package com.github.paulajcm.ohmydog.android.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.paulajcm.ohmydog.android.R
import com.github.paulajcm.ohmydog.android.model.domain.Dog

class BreedListAdapter : RecyclerView.Adapter<BreedListAdapter.ViewHolder?>() {
    private val dogs: MutableList<Dog> = ArrayList()

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val breedTextView: TextView = itemView.findViewById(R.id.breed_name)
        private val breedImageView: ImageView = itemView.findViewById(R.id.dog_photo)
        fun bind(dog: Dog) {
            breedTextView.text = dog.breed.name
            val breedPath: String = dog.url
            Glide.with(breedImageView.context)
                .load(breedPath)
                .apply(RequestOptions.centerInsideTransform().timeout(60000))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(breedImageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.breed_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dogs[position])
    }

    fun addDog(dog: Dog, position: Int) {
        if (position == dogs.size) {
            dogs.add(dog)
            notifyItemInserted(position)
        }
    }

    override fun getItemCount(): Int {
        return dogs.size
    }
}