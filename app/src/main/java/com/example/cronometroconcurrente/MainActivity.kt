package com.example.cronometroconcurrente

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private val executor = Executors.newFixedThreadPool(5)
    private val handler = Handler(Looper.getMainLooper())
    private var running = false
    private var time = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        val btnStartSequential = findViewById<Button>(R.id.btnStartSequential)
        val btnStartConcurrent = findViewById<Button>(R.id.btnStartConcurrent)
        val btnStartSync = findViewById<Button>(R.id.btnStartSync)
        val btnStop = findViewById<Button>(R.id.btnStop)

        btnStartSequential.setOnClickListener { startSequential() }
        btnStartConcurrent.setOnClickListener { startConcurrent() }
        btnStartSync.setOnClickListener { startWithSync() }
        btnStop.setOnClickListener { stopTimer() }
    }

    private fun startSequential() {
        running = true
        time = 0
        val startTime = System.currentTimeMillis()
        while (running) {
            Thread.sleep(1000) // Espera de 1 segundo
            time++
            handler.post { textView.text = "Tiempo: ${time}s" }
        }
        val endTime = System.currentTimeMillis()
        handler.post { textView.text = "Finalizado en: ${endTime - startTime} ms" }
    }

    private fun startConcurrent() {
        running = true
        time = 0
        val startTime = System.currentTimeMillis()
        executor.execute {
            while (running) {
                Thread.sleep(1000)
                time++
                handler.post { textView.text = "Tiempo: ${time}s" }
            }
            val endTime = System.currentTimeMillis()
            handler.post { textView.text = "Finalizado en: ${endTime - startTime} ms" }
        }
    }

    private fun startWithSync() {
        running = true
        time = 0
        val file = File(filesDir, "cronometro.txt")
        executor.execute {
            while (running) {
                Thread.sleep(1000)
                time++
                synchronized(this) {
                    try {
                        FileWriter(file, true).use { it.write("Tiempo: ${time}s\n") }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
                handler.post { textView.text = "Tiempo: ${time}s" }
            }
        }
    }

    private fun stopTimer() {
        running = false
    }
}
