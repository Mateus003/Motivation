package com.example.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.example.motivation.infrastructure.MotivationConstants
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.infrastructure.SecurityPreferences
import com.example.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    private var categoryId = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonNewPhrase.setOnClickListener(this)

        supportActionBar?.hide()

        handleUserName()
        handleNextPhrase()

        handleFilter(R.id.icon_infinity)
        //Events
        binding.iconInfinity.setOnClickListener(this)
        binding.iconHappy.setOnClickListener(this)
        binding.iconSunny.setOnClickListener(this)


    }
    override fun onClick(view: View){
        if(view.id == R.id.button_new_phrase){
            handleNextPhrase()
        }else if(view.id in listOf(R.id.icon_infinity, R.id.icon_happy, R.id.icon_sunny)){
            handleFilter(view.id)
        }
    }

    private fun handleUserName(){
        val name =  SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textName.text = "Olá, $name!"
    }

    private fun handleFilter(id:Int){
        binding.iconInfinity.setColorFilter(ContextCompat.getColor(this, R.color.purple))
        binding.iconHappy.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.iconSunny.setColorFilter(ContextCompat.getColor(this,R.color.yellow))

        when (id) {
            R.id.icon_infinity -> {
                binding.iconInfinity.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.INFINITY
            }
            R.id.icon_happy -> {
                binding.iconHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.icon_sunny -> {
                binding.iconSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY

            }
        }
    }

    private fun handleNextPhrase(){
        binding.textPhrase.text = Mock().getPhrase(categoryId)

    }

}