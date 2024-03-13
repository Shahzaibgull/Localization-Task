package com.example.myapplication

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLocalizationMainBinding

import java.util.Locale

class LocalizationMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalizationMainBinding


    val languages = arrayOf("Select Language", "English", "Spanish")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLocalizationMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.setSelection(0)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long)
            {
                val seletedLang = parent.getItemAtPosition(position).toString()
                if (seletedLang == "English") {
                    setLocal(this@LocalizationMainActivity, "en")
                    finish()
                    startActivity(intent)
                } else if (seletedLang == "Spanish") {
                    setLocal(this@LocalizationMainActivity, "es")
                    finish()
                    startActivity(intent)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun setLocal(activity: Activity, langCode: String?) {
        val locale = Locale(langCode)
        Locale.setDefault(locale)
        val resources = activity.resources
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}