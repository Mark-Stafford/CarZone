//Mark Stafford
//Mobile App Assignment - SSD Year 2
//Lecturer - Rob O Connor

package org.wit.car.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_car.*
import org.wit.car.models.CarModel
import org.wit.car.R
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

class CarActivity : AppCompatActivity(), AnkoLogger {

    var car = CarModel()
    lateinit var app : MainApp
    val IMAGE_REQUEST = 1
    val LOCATION_REQUEST = 2
    var edit = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car)
        app = application as MainApp
        var edit = false

        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

// Edit car details such as model and description.
        if (intent.hasExtra("car_edit")) {
            edit = true
            car = intent.extras.getParcelable<CarModel>("car_edit")
            carTitle.setText(car.title)
            description.setText(car.description)
            enginesize.setText(car.enginesize)
            doors.setText(car.doors)
            model.setText(car.model)
            carImage.setImageBitmap(readImageFromPath(this, car.image))
            if (car.image != null){
                chooseImage.setText(R.string.change_car_image)
            }
            btnAdd.setText(R.string.save_car)
        }

        carLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (car.zoom != 0f) {
                location.lat =  car.lat
                location.lng = car.lng
                location.zoom = car.zoom
            }
            startActivityForResult(intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
        }

// Adding car to the recycler view also if all fields are not entered the car will not be added.
        btnAdd.setOnClickListener() {
            car.title = carTitle.text.toString()
            car.description = description.text.toString()
            car.enginesize = enginesize.text.toString()
            car.doors = doors.text.toString()
            car.model = model.text.toString()

            if (car.title.isEmpty()) {
                toast(R.string.enter_car_title)
                app.cars.delete(car)
            } else {
                if (edit) {
                    app.cars.update(car.copy())
                } else {
                    app.cars.create(car.copy())
                }
            }
            if (car.description.isEmpty()) {
                toast(R.string.enter_cardescription_)
                app.cars.delete(car)

            } else {
                if (edit) {
                    app.cars.update(car.copy())
                }
            }

            if (car.enginesize.isEmpty()) {
                toast(R.string.enter_car_enginesize)
                app.cars.delete(car)


            } else {
                if (edit) {
                    app.cars.update(car.copy())
                }
            }
            if (car.model.isEmpty()) {
                toast(R.string.enter_car_model)
                app.cars.delete(car)

            } else {
                if (edit) {
                    app.cars.update(car.copy())
                }
            }
            if (car.doors.isEmpty()) {
                toast(R.string.enter_car_doors)
                app.cars.delete(car)
            } else {
                if (edit) {
                    app.cars.update(car.copy())
                }
            }



            info("add Button Pressed: $carTitle")
            setResult(AppCompatActivity.RESULT_OK)
            
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_car, menu)
        return super.onCreateOptionsMenu(menu)
    }
// delete selected car method.
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.cars.delete(car)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


//Changing car image and location.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    car.image = data.getData().toString()
                    carImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_car_image)
                }
            }
            LOCATION_REQUEST -> {
                if (data != null) {
                    val location = data.extras.getParcelable<Location>("location")
                    car.lat = location.lat
                    car.lng = location.lng
                    car.zoom = location.zoom
                }
            }
        }
    }


}