package com.example.bankproject

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Musteriler(val ad_soyad : String? = "",
                      val gmail : String? = "",
                      val tc_no : Long? = 0,
                      val gise_no : String? = "",
                      val sira_no : Int? = 0) {
}