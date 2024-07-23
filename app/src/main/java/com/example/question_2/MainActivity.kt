package com.example.question_2

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.question_2.R

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerCompanies: Spinner
    private lateinit var spinnerModels: Spinner
    private lateinit var btnFindPrice: Button
    private lateinit var tvPrice: TextView

    private val propertyData = mapOf(
        "RealEstateCo1" to listOf("Apartment" to 120000, "Villa" to 300000, "Penthouse" to 450000),
        "RealEstateCo2" to listOf("Apartment" to 130000, "Villa" to 320000, "Townhouse" to 250000),
        "RealEstateCo3" to listOf("Apartment" to 125000, "Villa" to 310000, "Condo" to 270000)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerCompanies = findViewById(R.id.spinnerCompanies)
        spinnerModels = findViewById(R.id.spinnerModels)
        btnFindPrice = findViewById(R.id.btnFindPrice)
        tvPrice = findViewById(R.id.tvPrice)

        val companies = propertyData.keys.toList()
        val adapterCompanies = ArrayAdapter(this, android.R.layout.simple_spinner_item, companies)
        adapterCompanies.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCompanies.adapter = adapterCompanies

        spinnerCompanies.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val selectedCompany = companies[position]
                val models = propertyData[selectedCompany]?.map { it.first } ?: emptyList()
                val adapterModels = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, models)
                adapterModels.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerModels.adapter = adapterModels
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        btnFindPrice.setOnClickListener {
            val selectedCompany = spinnerCompanies.selectedItem.toString()
            val selectedModel = spinnerModels.selectedItem.toString()
            val price = propertyData[selectedCompany]?.find { it.first == selectedModel }?.second ?: "N/A"
            tvPrice.text = "Price: $${price}"
        }
    }
}
