//Mark Stafford
//Mobile App Assignment - SSD Year 2
//Lecturer - Rob O Connor
package org.wit.car.models



    import android.content.Context
    import com.google.gson.Gson
    import com.google.gson.GsonBuilder
    import com.google.gson.reflect.TypeToken
    import kotlinx.android.synthetic.main.activity_car.view.*
    import org.jetbrains.anko.AnkoLogger
    import org.wit.car.helpers.*
    import java.util.*

    val JSON_FILE = "cars.json"
    val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
    val listType = object : TypeToken<java.util.ArrayList<CarModel>>() {}.type

    fun generateRandomId(): Long {
        return Random().nextLong()
    }

    class CarJSONStore : CarStore, AnkoLogger {

        val context: Context
        var cars = mutableListOf<CarModel>()

        constructor (context: Context) {
            this.context = context
            if (exists(context, JSON_FILE)) {
                deserialize()
            }
        }

        override fun findAll(): MutableList<CarModel> {
            return cars
        }

        override fun create(car: CarModel) {
            car.id = generateRandomId()
            cars.add(car)
            serialize()
        }



        override fun update(car: CarModel) {
            val carsList = findAll() as ArrayList<CarModel>
            var foundCar: CarModel? = carsList.find { p -> p.id == car.id }
            if (foundCar != null) {
                foundCar.title = car.title
                foundCar.description = car.description
                foundCar.enginesize = car.enginesize
                foundCar.model = car.model
                foundCar.doors = car.doors
                foundCar.image = car.image
                foundCar.lat = car.lat
                foundCar.lng = car.lng
                foundCar.zoom = car.zoom
            }
            serialize()
        }

        private fun serialize() {
            val jsonString = gsonBuilder.toJson(cars, listType)
            write(context, JSON_FILE, jsonString)
        }

        private fun deserialize() {
            val jsonString = read(context, JSON_FILE)
            cars = Gson().fromJson(jsonString, listType)
        }

        override fun delete(car: CarModel) {
            cars.remove(car)
            serialize()
        }
    }
