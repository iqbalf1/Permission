package com.binar.permissionsample

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private lateinit var imageButton: Button
    private lateinit var imageView: ImageView
    private lateinit var request: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadImage();

    }

    private fun loadImage() {
        imageView = findViewById(R.id.image_view)
        request = findViewById(R.id.request_permission)
        imageButton = findViewById(R.id.load_image_button)
        imageButton.setOnClickListener {
            Glide.with(this)
                .load("https://img.icons8.com/plasticine/2x/flower.png")
                .circleCrop()
                .into(imageView)
        }

        request.setOnClickListener {
            requestPermissionLocation()
        }


    }

    private fun requestPermissionLocation() {
        val permissionCheck = checkSelfPermission(ACCESS_FINE_LOCATION)
        if (permissionCheck == PERMISSION_GRANTED) {
            Snackbar.make(
                findViewById(R.id.parent_layout),
                "Permission Diizinkan",
                Snackbar.LENGTH_LONG
            ).show()
            getLongLat()
        } else {
            Snackbar.make(
                findViewById(R.id.parent_layout),
                "Permission Ditolak",
                Snackbar.LENGTH_LONG
            ).show()
            requestLocationPermission()
        }
    }

    @SuppressLint("MissingPermission")
    fun getLongLat() {
        val locationManager =
            applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val location: Location? =
            locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)

        Toast.makeText(
            this,
            "Lat : ${location?.latitude}Long : ${location?.latitude}",
            Toast.LENGTH_SHORT
        ).show()
    }

    fun requestLocationPermission() {
        requestPermissions(arrayOf(ACCESS_FINE_LOCATION), 201)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            201 -> {
                if (grantResults.size > 0 && grantResults[0] == PERMISSION_GRANTED) {
                    Toast.makeText(this, "Allow telah dipilih", Toast.LENGTH_LONG).show()
                    getLongLat()
                } else {
                    Toast.makeText(this, "Deny telah dipilih", Toast.LENGTH_LONG).show()
                }
            }
            else -> {
                Toast.makeText(this, "Bukan Request yang dikirim", Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * 1. buatlah function namanya loadImage
     * 2. pindahin code glide di function loadImage
     * 3. buatlah function yang namanya requestPermissionLocation
     * 4. didalam function requestPermissionLocation tambahkan code untuk request akses location yang ada didalam slide
     */

}