package com.esaudev.testproj.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import com.esaudev.testproj.R
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var successButton: Button
    private lateinit var failureButton: Button
    private lateinit var imageView: ImageView
    private lateinit var progressIndicator: CircularProgressIndicator
    private lateinit var errorMessage: TextView

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        successButton = findViewById(R.id.loadImageButton)
        failureButton = findViewById(R.id.loadImageWithErrorButton)
        imageView = findViewById(R.id.photoImageView)
        progressIndicator = findViewById(R.id.photoProgressIndicator)
        errorMessage = findViewById(R.id.errorMessageTextView)

        initListeners()
        initObservables()
    }

    private fun initObservables() {
        viewModel.imagePayload.observe(this) { uiState ->
            when(uiState) {
                is MainViewModel.MainUiState.WithImage -> {
                    progressIndicator.visibility = View.INVISIBLE
                    errorMessage.visibility = View.INVISIBLE
                    imageView.setImageBitmap(uiState.bitmap)
                    imageView.visibility = View.VISIBLE
                }
                is MainViewModel.MainUiState.Loading -> {
                    imageView.visibility = View.INVISIBLE
                    errorMessage.visibility = View.INVISIBLE
                    progressIndicator.visibility = View.VISIBLE
                }
                is MainViewModel.MainUiState.Error -> {
                    progressIndicator.visibility = View.INVISIBLE
                    imageView.visibility = View.INVISIBLE
                    errorMessage.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initListeners() {
        successButton.setOnClickListener {
            viewModel.getBitmapFromUrl(
                url = "breeds/terrier-cairn/n02096177_11605.jpg"
            )
        }

        failureButton.setOnClickListener {
            viewModel.getBitmapFromUrl(
                url = "breeds/terrier-cairn/n02096177_11605A.jpg"
            )
        }
    }
}