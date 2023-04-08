package com.example.bankproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_user_interface.view.*


class UserInterfaceFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tasarim = inflater.inflate(R.layout.fragment_user_interface, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        val tcKimlik = tasarim.editTextTcKimlik.text.toString().trim()
        val giseNo = tasarim.editTextGiseNumarasi.text.toString().trim()
        val siraNo = tasarim.editTextSiraNo.text.toString().trim()

        tasarim.exitButton.setOnClickListener {
            auth.signOut()
            Navigation.findNavController(it).navigate(R.id.action_userInterfaceFragment_to_userLoginFragment)
        }

        val refMusteriler = database.getReference("musteriler")

        //  val musteri1 = Musteriler("Mehmet Karaca","kullanici@gmail.com",12345678998,1,1)
        //  refMusteriler.push().setValue(musteri1)

        //  val musteri2 = Musteriler("Ahmet Kibar","kullanici2@gmail.com",14725836998,1,2)
        //  refMusteriler.push().setValue(musteri2)

        return tasarim
    }

}