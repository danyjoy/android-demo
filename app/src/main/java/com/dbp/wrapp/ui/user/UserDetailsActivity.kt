package com.dbp.wrapp.ui.user

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dbp.wrapp.R
import com.dbp.wrapp.network.responseModels.EmpResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_details.*


class UserDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val intent = intent
        val args = intent.getBundleExtra("bundle")
        val data = args!!.getSerializable("data") as EmpResponse

        Picasso.get().load(data.profileImage).into(profile_image)
        data.name.also { tvName.text = it }
        "Username: ${data.username}".also { tvUsername.text = it }
        "Email: ${data.email}".also { tvEmail.text = it }
        "Phone: ${data.phone}".also { tvPhone.text = it }
        "Website: ${data.website}".also { tvWebsite.text = it }
        "Street: ${data.address?.city}".also { tvStreet.text = it }
        "Suite: ${data.address?.suite}".also { tvSuite.text = it }
        "Zip Code: ${data.address?.zipcode}".also { tvZipcode.text = it }
        "City : ${data.address?.city}".also { tvCity.text = it }
        "Name: ${data.company?.companyName}".also { tvCompanyName.text = it }
        tvBs.text = data.company?.bs
        tvCatchPhrase.text = data.company?.catchPhrase

        btGeo.setOnClickListener {
            val strUri =
                "http://maps.google.com/maps?q=loc:" + data.address?.geo?.lat.toString() + "," + data.address?.geo?.lng.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
            intent.setClassName(
                "com.google.android.apps.maps",
                "com.google.android.maps.MapsActivity"
            )
            startActivity(intent)
        }


    }
}