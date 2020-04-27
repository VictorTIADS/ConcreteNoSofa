package com.concrete.concretenosofa.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.concrete.concretenosofa.R
import com.concrete.concretenosofa.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
