package com.potatobon.airqualitymonitor

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.knobtviker.android.things.contrib.driver.bme680.Bme680
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : Activity() {

    private lateinit var bme680: Bme680
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            bme680 = Bme680(BoardDefaults.getI2cBus())
            bme680.temperatureOversample = Bme680.OVERSAMPLING_1X
            val temperature = bme680.readTemperature()

            bme680.humidityOversample = Bme680.OVERSAMPLING_1X
            val humidity = bme680.readHumidity()

            bme680.pressureOversample = Bme680.OVERSAMPLING_1X
            val pressure = bme680.readPressure()

            readings.text = "Temperature: $temperature\nHumidity: $humidity\nPressure: $pressure"
        } catch (e: IOException) {
            Log.e("AirQualityMonitor", e.message)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            bme680.close()
        } catch (e: IOException) {
            Log.e("AirQualityMonitor", e.message)
        }
    }
}
