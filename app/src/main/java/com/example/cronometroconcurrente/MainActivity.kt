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
    // TextView para mostrar el tiempo en pantalla
    private lateinit var textView: TextView

    // Executor con un pool de 5 hilos para manejar la ejecución concurrente
    private val executor = Executors.newFixedThreadPool(5)

    // Handler para actualizar la interfaz desde un hilo secundario
    private val handler = Handler(Looper.getMainLooper())

    // Variables de control del cronómetro
    private var running = false
    private var time = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asignación de vistas a variables
        textView = findViewById(R.id.textView)
        val btnStartSequential = findViewById<Button>(R.id.btnStartSequential)
        val btnStartConcurrent = findViewById<Button>(R.id.btnStartConcurrent)
        val btnStartSync = findViewById<Button>(R.id.btnStartSync)
        val btnStop = findViewById<Button>(R.id.btnStop)

        // Asigna los eventos de clic a los botones
        btnStartSequential.setOnClickListener { startSequential() }
        btnStartConcurrent.setOnClickListener { startConcurrent() }
        btnStartSync.setOnClickListener { startWithSync() }
        btnStop.setOnClickListener { stopTimer() }
    }

    /**
     * Método para iniciar el cronómetro de forma secuencial.
     * Bloquea el hilo principal mientras se ejecuta.
     */
    private fun startSequential() {
        running = true
        time = 0
        val startTime = System.currentTimeMillis()

        // Bucle que bloquea el hilo principal
        while (running) {
            Thread.sleep(1000) // Espera 1 segundo
            time++
            handler.post { textView.text = "Tiempo: ${time}s" } // Actualiza la UI
        }

        // Calcula el tiempo total transcurrido
        val endTime = System.currentTimeMillis()
        handler.post { textView.text = "Finalizado en: ${endTime - startTime} ms" }
    }

    /**
     * Método para iniciar el cronómetro de forma concurrente usando un hilo del pool de `executor`.
     */
    private fun startConcurrent() {
        running = true
        time = 0
        val startTime = System.currentTimeMillis()

        // Ejecuta el cronómetro en un hilo separado sin bloquear la UI
        executor.execute {
            while (running) {
                Thread.sleep(1000)
                time++
                handler.post { textView.text = "Tiempo: ${time}s" }
            }

            // Calcula el tiempo total transcurrido y lo muestra en la UI
            val endTime = System.currentTimeMillis()
            handler.post { textView.text = "Finalizado en: ${endTime - startTime} ms" }
        }
    }

    /**
     * Método para iniciar el cronómetro con sincronización al escribir en un archivo.
     */
    private fun startWithSync() {
        running = true
        time = 0
        val file = File(filesDir, "cronometro.txt") // Archivo donde se guardará el tiempo

        // Ejecuta el cronómetro en un hilo separado
        executor.execute {
            while (running) {
                Thread.sleep(1000)
                time++

                // Bloqueo sincronizado para evitar escritura simultánea en el archivo
                synchronized(this) {
                    try {
                        FileWriter(file, true).use { it.write("Tiempo: ${time}s\n") }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

                // Actualiza la UI con el tiempo actual
                handler.post { textView.text = "Tiempo: ${time}s" }
            }
        }
    }

    /**
     * Método para detener el cronómetro.
     */
    private fun stopTimer() {
        running = false
    }
}

