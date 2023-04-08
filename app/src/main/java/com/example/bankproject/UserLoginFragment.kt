package com.example.bankproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_user_login.view.*


class UserLoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tasarim = inflater.inflate(R.layout.fragment_user_login, container, false)

        tasarim.buttonKullaniciGiris.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_userInterfaceFragment)
        }

        tasarim.createAccountText.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_userLoginFragment_to_userCreateFragment)
        }

        return tasarim
    }

}