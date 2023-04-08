package com.example.bankproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_splash.view.*

class SplashFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tasarim = inflater.inflate(R.layout.fragment_splash, container, false)

       tasarim.buttonAnaGiris.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_userLoginFragment)
       }


        return tasarim
    }

}