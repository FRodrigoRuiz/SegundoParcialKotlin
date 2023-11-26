package com.example.segundoparcialkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter(private val fruits: List<Fruit>, val context: Context): RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var onItemClickListener: (Fruit) -> Unit

    inner class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        private val ivFruit: ImageView = view.findViewById(R.id.ivFruit)
        private val tvFruitName: TextView = view.findViewById(R.id.tvFruitName)

        fun bind(fruit: Fruit) {
            tvFruitName.text = fruit.name
            Glide.with(context).load(fruit.image).into(ivFruit)

            view.setOnClickListener{
                onItemClickListener(fruit)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return fruits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruits[position]
        holder.bind(fruit)
    }
}