package com.example.segundoparcialkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailFruit: AppCompatActivity() {
    private lateinit var ivFruit: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvCalories: TextView
    private lateinit var tvCarb: TextView
    private lateinit var tvFat: TextView
    private lateinit var tvSugar: TextView
    private lateinit var tvProtein: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fruit_detail)

        val fruit = intent.getParcelableExtra<Fruit>("fruit")

        ivFruit = findViewById(R.id.ivFruit)
        tvName = findViewById(R.id.tvFruitName)
        tvCalories = findViewById(R.id.tvCal)
        tvCarb = findViewById(R.id.tvCarb)
        tvFat = findViewById(R.id.tvFat)
        tvSugar = findViewById(R.id.tvSugar)
        tvProtein = findViewById(R.id.tvProtein)

        tvName.text = fruit?.name
        tvCalories.text = fruit?.nutrition?.calories.toString()
        tvCarb.text = fruit?.nutrition?.carbohydrates.toString()
        tvFat.text = fruit?.nutrition?.fat.toString()
        tvSugar.text = fruit?.nutrition?.sugar.toString()
        tvProtein.text = fruit?.nutrition?.protein.toString()
        Glide.with(this).load(fruit?.image).into(ivFruit)
    }
}