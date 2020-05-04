//Mark Stafford
//Mobile App Assignment - SSD Year 2
//Lecturer - Rob O Connor
package org.wit.car.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_car.*
import kotlinx.android.synthetic.main.activity_car_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.car.R
import org.wit.car.main.MainApp
import org.wit.car.models.CarModel

class CarListActivity : AppCompatActivity(), CarListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = CarAdapter(app.cars.findAll(), this)
        loadCars()

        toolbarMain.title = title
        setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }



    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<CarActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCarClick(car: CarModel) {
        startActivityForResult(intentFor<CarActivity>().putExtra("car_edit", car), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_car_list.xml
        recyclerView.adapter?.notifyDataSetChanged()
        loadCars()
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun loadCars() {
        showCars(app.cars.findAll())
    }



    fun showCars (cars: List<CarModel>) {
        recyclerView.adapter = CarAdapter(cars, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }




}