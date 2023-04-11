package com.example.bankproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_user_login.view.*


class UserLoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tasarim = inflater.inflate(R.layout.fragment_user_login, container, false)
        auth = FirebaseAuth.getInstance()
        val guncelKullanici = auth.currentUser

        tasarim.buttonKullaniciGiris.setOnClickListener {
            try {
                val kullaniciEmail = tasarim.editTextEmail.text.toString().trim()
                val kullaniciPassword = tasarim.editTextPassword.text.toString().trim()

                auth.signInWithEmailAndPassword(kullaniciEmail,kullaniciPassword).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_userInterfaceFragment)
                        Toast.makeText(activity, "Hoşgeldiniz...", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener { exception ->
                    Toast.makeText(activity,exception.localizedMessage, Toast.LENGTH_LONG).show()
                }



            }catch (e : Exception) {
                e.printStackTrace()
            }
        }

        tasarim.forgotPassword.setOnClickListener {

            try {

                val kullaniciEmail = tasarim.editTextEmail.text.toString()
                FirebaseAuth.getInstance().sendPasswordResetEmail(kullaniciEmail)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Navigation.findNavController(it).navigate(R.id.forgotPassword)
                        }
                    }.addOnFailureListener { exception ->
                        Toast.makeText(activity, "Başarısız Şifre Sıfırlama İşlemi", Toast.LENGTH_LONG)
                            .show()
                    }


            } catch (e: Exception) {
                e.printStackTrace()

            }
        }

        tasarim.createAccountText.setOnClickListener {
            try {
                Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_userCreateFragment)
            }catch (e : Exception) {
                e.printStackTrace()
            }
        }

        return tasarim
    }

}