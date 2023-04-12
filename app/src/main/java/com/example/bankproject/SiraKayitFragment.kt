package com.example.bankproject

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_sira_kayit.*
import kotlinx.android.synthetic.main.fragment_sira_kayit.view.*


class SiraKayitFragment : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var database: FirebaseDatabase
    var limit = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val tasarim = inflater.inflate(R.layout.fragment_sira_kayit, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()


        tasarim.exitButtonYonetici.setOnClickListener {
            auth.signOut()
            Navigation.findNavController(it).navigate(R.id.action_siraKayitFragment_to_yoneticiGirisFragment)
        }


        tasarim.buttonYoneticiKaydet.setOnClickListener {
          val ad_soyad = tasarim.yoneticiKullaniciAd.text.toString().trim()
            val gmail = tasarim.yoneticiMusteriGmail.text.toString().trim()
            val tc_no = tasarim.yoneticiMusteriKimlikNumarasi.text.toString().trim().toLong()
            val gise_no = tasarim.yoneticiMusteriGiseNo.text.toString().trim()
            val sira_no = tasarim.yoneticiMusteriSiraNo.text.toString().trim().toInt()

            val refMusteriler = database.getReference("musteriler")
            val musteri3 = Musteriler(ad_soyad,gmail,tc_no, gise_no,sira_no)
            refMusteriler.push().setValue(musteri3)
           Toast.makeText(activity,"İşlem Başarılı",Toast.LENGTH_SHORT).show()

        }

        tasarim.buttonVerileriAl.setOnClickListener {
            val refMusteriler = database.getReference("ilk_kayit")

              val sorgu = refMusteriler.orderByChild("gise_no").limitToFirst(limit++)
            sorgu.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (kayit in snapshot.children) {
                        val musteriler = kayit.getValue(MusteriKayit::class.java)
                        if (kayit!= null) {
                            val key = kayit.key
                            alpha()
                            tasarim.textViewGelenMusteriBilgi.text = "Müşterinin Adı Soyadı : ${musteriler?.ad_soyad} \n Gmail Adresi : ${musteriler?.gmail} \n TC Kimlik Numarası : ${musteriler?.tc_no} \n Gişe Numarası : ${musteriler?.gise_no} \n Sıra Numarası : ${musteriler?.sira_no}"


                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {


                }

            })
        }

        return tasarim
    }

    fun alpha() {
        val alphaAnimation = ObjectAnimator.ofFloat(textViewGelenMusteriBilgi,"alpha",0.0f,1.0f).apply {
            duration = 3000
        }
        alphaAnimation.start()
    }


    }
