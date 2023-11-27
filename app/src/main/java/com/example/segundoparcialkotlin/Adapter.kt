package com.example.segundoparcialkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Adapter (private val fruits: MutableList<Fruit>, val context: Context) :  RecyclerView.Adapter<Adapter.ViewHolder>() {

    lateinit var onItemClickListener: (Fruit) -> Unit

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val tvFruit: TextView = view.findViewById(R.id.tv_fruit)
        private val ivFruit: ImageView = view.findViewById(R.id.iv_fruit)
        private val defaultImage: String = "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2021/09/27/all-the-fruits-cut-whole.jpg.rend.hgtvcom.1280.720.suffix/1632778035320.jpeg"
        private val persimmonImage: String = "https://static.vecteezy.com/system/resources/previews/022/881/860/original/persimmon-transparent-background-with-generative-ai-png.png"
        private val strawberryImage: String = "https://media.istockphoto.com/id/1157946861/photo/red-berry-strawberry-isolated.jpg?s=612x612&w=0&k=20&c=HyxZMbI_e-vDJbrzZkTz5zWCAo1mBEzWbvVlyigbi-E="
        private val bananaImage: String = "https://png.pngtree.com/png-clipart/20220716/ourmid/pngtree-banana-yellow-fruit-banana-skewers-png-image_5944324.png"

        fun bind(fruit: Fruit) {
            tvFruit.text = fruit.name
            when (fruit.name){
                "Persimmon" -> fruit.image = persimmonImage
                "Strawberry" -> fruit.image = strawberryImage
                "Banana" -> fruit.image = bananaImage
                else -> fruit.image = defaultImage
            }
            Glide.with(context).load(fruit.image).into(ivFruit)
            view.setOnClickListener {
                onItemClickListener(fruit)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return fruits.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruits[position]
        holder.bind(fruit)
    }
}
