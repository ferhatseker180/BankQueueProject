package com.example.bankproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_ana_sira_olusturma.view.*
import kotlinx.android.synthetic.main.fragment_splash.view.*

class SplashFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tasarim = inflater.inflate(R.layout.fragment_splash, container, false)
        auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser

        tasarim.buttonSıraAlmayaGit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_anaSiraOlusturmaFragment)
        }

       tasarim.buttonAnaKullaniciGiris.setOnClickListener {
           if (guncelKullanici != null) {
               Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_userInterfaceFragment4)
           } else {
               Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_userLoginFragment)

           }
       }

        tasarim.buttonYoneticiAnaGiris.setOnClickListener {
            if (guncelKullanici != null) {
                Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_siraKayitFragment)
            }
            else {
                Navigation.findNavController(it).navigate(R.id.action_splashFragment_to_yoneticiGirisFragment)
            }
        }



        return tasarim
    }

}