package com.challenge.latihanbinar.SharedPreference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.challenge.latihanbinar.databinding.ActivitySharedPreferenceBinding

class SharedPreferenceActivity : AppCompatActivity() {
    lateinit var binding: ActivitySharedPreferenceBinding

lateinit var sharedpreferences : SharedPreferences
lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpSharedPreference()
        setUpAction()

    }

    private fun setUpSharedPreference() {
        sharedpreferences = this.getSharedPreferences(TABLE_DATA, Context.MODE_PRIVATE)
        editor = sharedpreferences.edit()

    }

    private fun setUpAction() {
        binding.btnSave.setOnClickListener{
            val idData = binding.etInputId.text.toString().toInt()
            val nameData = binding.etInputName.text.toString()
            editor.putInt(KEY_ID,idData)
            editor.putString(KEY_NAME,nameData)
            Toast.makeText(this,"Save Berhasil!",Toast.LENGTH_SHORT).show()
            clearEditText()

        }
        binding.btnView.setOnClickListener {
            val dataIdView = sharedpreferences.getInt(KEY_ID,0)
            val dataNameView = sharedpreferences.getString(KEY_NAME,"-")
            if(dataIdView != 0 && dataNameView != "-"){
                Toast.makeText(this,"Data Ditampilkan!",Toast.LENGTH_SHORT).show()
                binding.apply {
                    tvShowId.text = dataIdView.toString()
                    tvShowName.text = dataNameView
                }
            }else{
                Toast.makeText(this,"Data Kosong!",Toast.LENGTH_SHORT).show()
            }
        }
        binding.btnClear.setOnClickListener {
            editor.clear()
            editor.apply()
            clearEditText()
            clearView()
            Toast.makeText(this,"Data Berhasil Dihapus!",Toast.LENGTH_SHORT).show()

        }
    }

    private fun clearView() {
        binding.apply {
            tvShowId.text = ""
            tvShowName.text = ""
        }

    }

    private fun clearEditText() {
        binding.etInputId.text.clear()
        binding.etInputName.text.clear()

    }


    companion object{
        const val KEY_ID = "KEY_ID"
        const val KEY_NAME = "KEY_NAME"
        const val TABLE_DATA = "kotlinsharedpreference"
    }
}


