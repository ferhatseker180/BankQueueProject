package com.example.bankproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_yonetici_giris.*
import kotlinx.android.synthetic.main.fragment_yonetici_giris.view.*

class YoneticiGirisFragment : Fragment() {
    private lateinit var auth : FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val tasarim = inflater.inflate(R.layout.fragment_yonetici_giris, container, false)

        auth = FirebaseAuth.getInstance()
        val guncelKullanici = auth.currentUser



        tasarim.buttonYoneticiGiris.setOnClickListener {
            val yoneticimail = tasarim.yoneticiEmailGiris.text.toString().trim()
            val yoneticiPassword = tasarim.yoneticiGirisPassword.text.toString().trim()
            val yoneticiKodText = tasarim.yoneticiKodText.text.toString().trim()
            val yoneticiKod = "1234"

            auth.signInWithEmailAndPassword(yoneticimail,yoneticiPassword).addOnCompleteListener { task ->
                        if (task.isSuccessful && yoneticiKodText==yoneticiKod) {
                         //   val guncelYonetici = auth.currentUser?.email.toString()
                            Navigation.findNavController(it).navigate(R.id.action_yoneticiGirisFragment_to_siraKayitFragment)
                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(activity,exception.localizedMessage,Toast.LENGTH_LONG).show()
                    }
        }

        tasarim.forgotPasswordYonetici.setOnClickListener {
            val yoneticiEmail = tasarim.yoneticiEmailGiris.text.toString().trim()
            FirebaseAuth.getInstance().sendPasswordResetEmail(yoneticiEmail).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Navigation.findNavController(it).navigate(R.id.forgotPasswordYonetici)
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(activity,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }

        return tasarim
    }

}