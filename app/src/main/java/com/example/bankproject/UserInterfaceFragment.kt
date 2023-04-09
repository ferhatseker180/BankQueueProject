package com.example.bankproject

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_user_interface.*
import kotlinx.android.synthetic.main.fragment_user_interface.view.*


class UserInterfaceFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val tasarim = inflater.inflate(R.layout.fragment_user_interface, container, false)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        val email = auth.currentUser?.email.toString()

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

        val sorgu = refMusteriler.orderByChild("sira_no").limitToLast(1)
        val sorgu2 = refMusteriler.orderByChild("gmail").equalTo("${email}")


        tasarim.buttonOnayla.setOnClickListener {
            sorgu2.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    for (s in snapshot.children) {
                        val musteri = s.getValue(Musteriler::class.java)
                        val tc = tasarim.editTextTcKimlik.text.toString()
                        val giseNumarasi = editTextGiseNumarasi.text.toString()
                        val siraNumarasi = editTextSiraNo.text.toString()
                        if (s != null ) {
                            val keyy = s.key
                            if (musteri?.tc_no.toString() == tc && giseNumarasi.toString()==musteri?.gise_no.toString()) {
                                tasarim.textViewAdSoyad.text = "Adınız Soyadınız : ${musteri?.ad_soyad.toString()}"
                                tasarim.textViewTcKimlik.text = "TC Kimlik Numaranız : ${musteri?.tc_no.toString()}"
                                tasarim.textViewGmail.text = "Gmail Adresiniz : ${musteri?.gmail.toString()}"
                                tasarim.textViewGiseNo.text = "Gişe Numaranız : ${musteri?.gise_no.toString()}"
                                tasarim.textViewSiraNo.text = "Sıra Numaranız : ${musteri?.sira_no.toString()}"
                                refMusteriler.orderByChild("sira_no").limitToLast(1)

                                sorgu.addValueEventListener(object : ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        for (postSnapshot in snapshot.children ) {
                                            val musteri = postSnapshot.getValue(Musteriler::class.java)
                                            if (postSnapshot != null) {
                                                val key = postSnapshot.key
                                                Log.e("key",key.toString())
                                                tasarim.textViewMevcutSiraNo.text = "İşlem Yapan Kişinin Sıra Numarası : ${musteri?.sira_no.toString()} "

                                            }
                                        }

                                    }

                                    override fun onCancelled(error: DatabaseError) {


                                    }

                                })
                            }

                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("HATA",error.toException().toString())
                }

            })
        }



        return tasarim
    }

}