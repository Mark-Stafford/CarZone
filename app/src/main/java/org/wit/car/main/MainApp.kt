//Mark Stafford
//Mobile App Assignment - SSD Year 2
//Lecturer - Rob O Connor



package org.wit.car.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.car.models.CarMemStore
import org.wit.car.models.CarModel
import org.wit.car.models.CarJSONStore
import org.wit.car.models.CarStore


class MainApp : Application(), AnkoLogger {


    lateinit var cars: CarStore

    override fun onCreate() {
        super.onCreate()
        cars = CarMemStore()
        info("Car started")
    }
}