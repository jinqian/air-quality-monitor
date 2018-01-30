package com.potatobon.airqualitymonitor

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.knobtviker.android.things.contrib.driver.bme680.Bme680
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bme680: Bme680

        try {
            bme680 = Bme680(BoardDefaults.getI2cBus())
            bme680.temperatureOversample = Bme680.OVERSAMPLING_1X
            val temperature = bme680.readTemperature()
            temperatureTextView.text = "Current Temperature: $temperature"
        } catch (e: IOException) {
            Log.e("AirQualityMonitor", e.message)
        }
    }
}
