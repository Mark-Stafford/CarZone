package org.wit.car.activities
import org.wit.car.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_car.*
import org.wit.car.models.CarModel

import org.wit.car.helpers.readImage
import org.wit.car.helpers.readImageFromPath
import org.wit.car.helpers.showImagePicker
import org.wit.car.main.MainApp
import org.wit.car.models.Location
import android.content.Intent
import android.view.ActionMode
import kotlinx.android.synthetic.main.activity_car.carTitle
import kotlinx.android.synthetic.main.activity_car.description
import kotlinx.android.synthetic.main.activity_car.doors
import kotlinx.android.synthetic.main.activity_car.enginesize
import kotlinx.android.synthetic.main.activity_car.model
import kotlinx.android.synthetic.main.card_car.*
import org.jetbrains.anko.*



import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    private var Name: EditText? = null
    private var Password: EditText? = null
    private var Info: TextView? = null
    private var Login: Button? = null
    private var counter = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Name = findViewById(R.id.etName) as EditText
        Password = findViewById(R.id.etPassword) as EditText
        Info = findViewById(R.id.tvInfo) as TextView
        Login = findViewById(R.id.btnLogin) as Button

        Info!!.text = "No of attempts remaining: 5"

        Login!!.setOnClickListener { validate(Name!!.text.toString(), Password!!.text.toString()) }
    }

    private fun validate(userName: String, userPassword: String) {
        if (userName == "Admin" && userPassword == "1234") {
            val intent = Intent(this@LoginActivity, CarActivity::class.java)
            startActivity(intent)
        } else {
            counter--

            Info!!.text = "No of attempts remaining: $counter"

            if (counter == 0) {
                Login!!.isEnabled = false
            }
        }
    }

}