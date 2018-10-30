package com.example.rafaelvilla.mobilieapplicationtest.view.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.rafaelvilla.mobilieapplicationtest.R
import com.example.rafaelvilla.mobilieapplicationtest.Utilities.KeyDictionary
import com.example.rafaelvilla.mobilieapplicationtest.model.Adapter.ImageAdapter
import com.example.rafaelvilla.mobilieapplicationtest.model.properties.ContentImage
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


/**
 * ExampleTest
 * @author Rafael Villa Hernández
 */

class HomeImageActivity: AppCompatActivity() {

    private lateinit var recyclerViewContent: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var dataContent: ArrayList<ContentImage>
    private lateinit var adapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_recycler_activity)

        recyclerViewContent = findViewById(R.id.recyclerViewImage)

        recyclerViewContent.setHasFixedSize(KeyDictionary.defautlBool)
        layoutManager = GridLayoutManager(applicationContext, 2)
        recyclerViewContent.layoutManager = layoutManager

        dataContent = dataArrayImage()
        adapter = ImageAdapter(dataContent, applicationContext)

        recyclerViewContent.adapter = adapter


    }

    fun urlToBitmap(urlImage: String): Bitmap? {
        try {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)

            val urlContent = URL(urlImage)
            val connectImage = urlContent.openConnection() as HttpURLConnection

            connectImage.readTimeout = 5000
            connectImage.connectTimeout = 7000
            connectImage.requestMethod = "GET"
            connectImage.doInput = true
            connectImage.connect()
            var bitmap = connectImage.inputStream
            Log.e("Imagen", "Imagen" + bitmap)
            return BitmapFactory.decodeStream(connectImage.inputStream)
        } catch (e: IOException) {
            Log.e("Error", "Erro es: " + e)
            return null
        }

    }

    fun dataArrayImage(): ArrayList<ContentImage> {
        var testImage = ArrayList<ContentImage>()

        for (numberImage in 0..(KeyDictionary.imageURLs.size - 1)) {
            var contentImage = ContentImage("Image$numberImage", urlToBitmap(KeyDictionary.imageURLs[numberImage]))
            testImage.add(contentImage)
        }
        return testImage
    }

}