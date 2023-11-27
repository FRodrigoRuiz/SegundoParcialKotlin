package com.example.segundoparcialkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailFruit : AppCompatActivity() {

    private lateinit var ivFruit: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvCalories: TextView
    private lateinit var tvCarbohydrates: TextView
    private lateinit var tvFat: TextView
    private lateinit var tvSugar: TextView
    private lateinit var tvProtein: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fruit_detail)

        val fruit = intent.getParcelableExtra<Fruit>("fruit")
        val nutrition = intent.getParcelableExtra<Nutrition>("nutrition")

        tvName = findViewById(R.id.tv_fruitname)
        tvCalories = findViewById(R.id.tv_cal)
        tvCarbohydrates = findViewById(R.id.tv_carb)
        tvFat = findViewById(R.id.tv_fat)
        tvSugar = findViewById(R.id.tv_sug)
        tvProtein = findViewById(R.id.tv_prot)
        ivFruit = findViewById(R.id.ivFruit)

        tvName.text = fruit?.name
        tvCalories.text = nutrition?.calories.toString()
        tvCarbohydrates.text = nutrition?.carbohydrates.toString()
        tvFat.text = nutrition?.fat.toString()
        tvSugar.text = nutrition?.sugar.toString()
        tvProtein.text = nutrition?.protein.toString()
        Glide.with(this).load(fruit?.image).into(ivFruit)
    }
}