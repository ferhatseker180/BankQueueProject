package com.example.bankproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class SiraKayitFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val tasarim = inflater.inflate(R.layout.fragment_sira_kayit, container, false)



        return tasarim
    }

}