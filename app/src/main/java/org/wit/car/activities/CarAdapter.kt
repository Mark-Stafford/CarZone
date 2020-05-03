//Mark Stafford
//Mobile App Assignment - SSD Year 2
//Lecturer - Rob O Connor
package org.wit.car.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_car.view.*
import kotlinx.android.synthetic.main.card_car.view.*
import kotlinx.android.synthetic.main.card_car.view.carTitle
import kotlinx.android.synthetic.main.card_car.view.description
import kotlinx.android.synthetic.main.card_car.view.model
import kotlinx.android.synthetic.main.card_car.view.doors
import kotlinx.android.synthetic.main.card_car.view.enginesize

import org.wit.car.R
import org.wit.car.helpers.readImageFromPath
import org.wit.car.models.CarModel
import org.wit.car.activities.CarAdapter.MainHolder as MainHolder

interface CarListener {
    fun onCarClick(car: CarModel)
}

class CarAdapter constructor(private var cars: List<CarModel>,
                                   private val listener: CarListener) : RecyclerView.Adapter<CarAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_car, parent, false))
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val car = cars[holder.adapterPosition]
        holder.bind(car, listener)
    }

    override fun getItemCount(): Int = cars.size

    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(car: CarModel,  listener : CarListener) {
            itemView.carTitle.text = car.title
            itemView.description.text = car.description
            itemView.enginesize.text = car.enginesize
            itemView.doors.text = car.doors
            itemView.model.text = car.model
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, car.image))
            itemView.setOnClickListener { listener.onCarClick(car) }
        }
    }
}