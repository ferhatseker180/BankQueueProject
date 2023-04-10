package com.example.bankproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_ana_sira_olusturma.*
import kotlinx.android.synthetic.main.fragment_ana_sira_olusturma.view.*

class AnaSiraOlusturmaFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val tasarim = inflater.inflate(R.layout.fragment_ana_sira_olusturma, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        var gise1 : Int = 0
        var gise2 : Int = 0
        var gise3 : Int = 0

        tasarim.buttonSıraAl.setOnClickListener {

            val ad_soyad = tasarim.siraAlmaAdSoyad.text.toString().trim()
            val tc_no = tasarim.siraAlmaKimlikNumarasi.text.toString().trim()

            if (gise1<20 && gise1 == gise2 && gise1 == gise3 ) {
                gise1++
                tasarim.textViewBilgilendirme.text = "AD SOYAD : ${ad_soyad} \n \n TC KİMLİK NUMARASI : ${tc_no} \n \n GİŞE NUMARANIZ : Gişe1 \n \n SIRA NUMARANIZ : ${gise1}"
                val refMusteriler = database.getReference("ilk_kayit")
                val giseNo = "Gişe 1"
                val siraNo = gise1
                val musteri = MusteriKayit(ad_soyad,tc_no.toLong(),giseNo,siraNo)
                refMusteriler.push().setValue(musteri)

            }
            else if (gise2<20 && gise1>gise2 && gise2 == gise3) {
                gise2++
                tasarim.textViewBilgilendirme.text = "AD SOYAD : ${ad_soyad} \n \n TC KİMLİK NUMARASI : ${tc_no} \n \n GİŞE NUMARANIZ : Gişe2 \n \n SIRA NUMARANIZ : ${gise2}"
                val refMusteriler = database.getReference("ilk_kayit")
                val giseNo = "Gişe 2"
                val siraNo = gise2
                val musteri = MusteriKayit(ad_soyad,tc_no.toLong(),giseNo,siraNo)
                refMusteriler.push().setValue(musteri)
            }
            else if (gise3<20 && gise1>gise3 && gise2>gise3) {
                gise3++
                tasarim.textViewBilgilendirme.text = "AD SOYAD : ${ad_soyad} \n \n TC KİMLİK NUMARASI : ${tc_no} \n \n GİŞE NUMARANIZ : Gişe3 \n \n SIRA NUMARANIZ : ${gise3}"
                val refMusteriler = database.getReference("ilk_kayit")
                val giseNo = "Gişe 3"
                val siraNo = gise3
                val musteri = MusteriKayit(ad_soyad,tc_no.toLong(),giseNo,siraNo)
                refMusteriler.push().setValue(musteri)
            }
            else if (gise1>=20 && gise2>=20 && gise3>=20) {
                Toast.makeText(activity,"Şu anda sıra numarası verme hizmeti veremiyoruz",Toast.LENGTH_LONG)
            }
        }

        tasarim.buttonKullaniciGiriseGit.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_anaSiraOlusturmaFragment_to_userLoginFragment)
        }

        return tasarim
    }


}