package com.example.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.motivation.infrastructure.MotivationConstants
import com.example.motivation.R
import com.example.motivation.infrastructure.SecurityPreferences
import com.example.motivation.databinding.ActivityUserNameBinding

class UserNameActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserNameBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserNameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener(this)

        supportActionBar?.hide()

        verifyUserName()

    }

    override fun onClick(view: View) {
        if(view.id == R.id.save_button){
            handleSave()
        }
    }

    private fun verifyUserName(){
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != ""){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


    private fun handleSave(){
        val name = binding.editTextName.text.toString()
        if (name != ""){
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, R.string.fill_name, Toast.LENGTH_SHORT).show()
        }
    }
}