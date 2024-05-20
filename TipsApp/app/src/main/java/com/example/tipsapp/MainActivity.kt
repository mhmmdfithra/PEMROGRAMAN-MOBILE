package com.example.tipsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    // Deklarasi variabel binding di tingkat kelas
    private lateinit var costOfServiceEditText: EditText
    private lateinit var tipOptionsRadioGroup: RadioGroup
    private lateinit var roundUpSwitch: Switch
    private lateinit var calculateButton: Button
    private lateinit var tipResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        costOfServiceEditText = findViewById(R.id.cost)
        tipOptionsRadioGroup = findViewById(R.id.RadioGroup)
        roundUpSwitch = findViewById(R.id.Switch)
        calculateButton = findViewById(R.id.ButtonKalku)
        tipResultTextView = findViewById(R.id.tip_result)

        calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val stringInTextField = costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            tipResultTextView.text = ""
            return
        }

        val tipPercentage = when (tipOptionsRadioGroup.checkedRadioButtonId) {
            R.id.OptionAmazing -> 0.20
            R.id.OptionGood -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        if (roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        tipResultTextView.text = getString(R.string.tip_result, formattedTip)
    }
}