package com.example.rafaelvilla.mobilieapplicationtest.model.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.rafaelvilla.mobilieapplicationtest.R
import com.example.rafaelvilla.mobilieapplicationtest.model.properties.ContentImage
import java.util.*

/**
 * ExampleTest
 * @author Rafael Villa Hernández
 */

class ImageAdapter (val imagesItems: ArrayList<ContentImage>, val context: Context): RecyclerView.Adapter<ViewHolder>() {
    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.urlImage.setImageBitmap(imagesItems.get(p1).urlImage)
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.content_image_layout, p0, false))
    }
    override fun getItemCount(): Int {
        return imagesItems.size
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var urlImage: ImageView = view.findViewById(R.id.imageURLContent)

}