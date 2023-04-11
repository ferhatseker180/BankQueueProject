package com.example.bankproject

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_user_create.*
import kotlinx.android.synthetic.main.fragment_user_create.view.*

class UserCreateFragment : Fragment() {

    private lateinit var auth : FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val tasarim = inflater.inflate(R.layout.fragment_user_create, container, false)

        auth = FirebaseAuth.getInstance()
        val guncelKullanici = auth.currentUser

        tasarim.buttonOlustur.setOnClickListener {

                val adSoyad = tasarim.editTextAdSoyadOlustur.text.toString().trim()
                val kullaniciGmail = tasarim.editTextEmailOlusturma.text.toString().trim()
                val kullaniciPassword = tasarim.editTextSifreOlusturma.text.toString().trim()
                val kullaniciPasswordCheck = tasarim.editTextSifreCheck.text.toString().trim()
            if (kullaniciPassword == kullaniciPasswordCheck) {
                auth.createUserWithEmailAndPassword(kullaniciGmail,kullaniciPassword).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Navigation.findNavController(it).navigate(R.id.action_userCreateFragment_to_userLoginFragment)
                    }

                }.addOnFailureListener { exception ->
                    Toast.makeText(activity,exception.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
            else if(kullaniciPassword != kullaniciPasswordCheck) {
                Toast.makeText(activity,"Şifreleriniz Birbiriyle Uyuşmuyor",Toast.LENGTH_LONG).show()
            }


        }


        return tasarim
    }


}