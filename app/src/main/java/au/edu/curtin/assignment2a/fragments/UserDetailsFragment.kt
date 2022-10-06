package au.edu.curtin.assignment2a.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import au.edu.curtin.assignment2a.R
import au.edu.curtin.assignment2a.controllers.UserController
import au.edu.curtin.userinfo.User


class UserDetailsFragment(private val user: User, private val controller: UserController) : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = view.findViewById<TextView>(R.id.idHelperText)
        val name = view.findViewById<TextView>(R.id.nameHelperText)
        val username = view.findViewById<TextView>(R.id.usernameHelperText)
        val email = view.findViewById<TextView>(R.id.emailHelperText)
        val phone = view.findViewById<TextView>(R.id.phoneHelperText)
        val website = view.findViewById<TextView>(R.id.websiteHelperText)
        val street = view.findViewById<TextView>(R.id.streetHelperText)
        val suite = view.findViewById<TextView>(R.id.suiteHelperText)
        val city = view.findViewById<TextView>(R.id.cityHelperText)
        val zipcode = view.findViewById<TextView>(R.id.zipcodeHelperText)
        val latitude = view.findViewById<TextView>(R.id.latitudeHelperText)
        val longitude = view.findViewById<TextView>(R.id.longitudeHelperText)
        val companyName = view.findViewById<TextView>(R.id.companyNameHelperText)
        val catchPhrase = view.findViewById<TextView>(R.id.catchPhraseHelperText)
        val slogan = view.findViewById<TextView>(R.id.sloganHelperText)
        val backBtn = view.findViewById<Button>(R.id.backBtn)
        val getPostsBtn = view.findViewById<Button>(R.id.getPostsBtn)

        id.text = "ID: ${user.id}"
        name.text = "Name: ${user.name}"
        username.text = "Username: ${user.username}"
        email.text = "Email: ${user.email}"
        phone.text = "Phone: ${user.phone}"
        website.text = "Website: ${user.website}"
        street.text = "Street: ${user.address.street}"
        suite.text = "Suite: ${user.address.suite}"
        city.text = "City: ${user.address.city}"
        zipcode.text = "Zipcode: ${user.address.zipcode}"
        latitude.text = "Latitude: ${user.address.geo.lat}"
        longitude.text = "Longitude: ${user.address.geo.lng}"
        companyName.text = "Company Name: ${user.company.name}"
        catchPhrase.text = "Catch Phrase: ${user.company.catchPhrase}"
        slogan.text = "Business Slogan: ${user.company.bs}"

        backBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.userListFragment, UserFragment(controller))
                commit()
            }
        }

        getPostsBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.userListFragment, PostsFragment(controller, user.id))
                commit()
            }
        }
    }

}