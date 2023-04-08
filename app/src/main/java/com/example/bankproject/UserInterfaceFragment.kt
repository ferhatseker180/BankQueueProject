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

        val sorgu = refMusteriler.orderByChild("tc_no").equalTo("12345678998")


        tasarim.buttonOnayla.setOnClickListener {
            sorgu.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    for (postSnapshot in snapshot.children ) {
                        val musteri = postSnapshot.getValue(Musteriler::class.java)
                        if (musteri != null) {
                        //    if (tcKimlik.toString() == musteri.tc_no.toString()) {
                                val key = postSnapshot.key
                                Log.e("key",key.toString())
                                Log.e("Kişi Ad",musteri.ad_soyad.toString())
                        //    }

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