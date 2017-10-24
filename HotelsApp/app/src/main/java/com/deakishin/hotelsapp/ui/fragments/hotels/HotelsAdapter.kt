package com.deakishin.hotelsapp.ui.fragments.hotels

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import com.deakishin.hotelsapp.R
import com.deakishin.hotelsapp.model.entities.Hotel
import com.deakishin.hotelsapp.utils.extensions.ctx
import com.deakishin.hotelsapp.utils.extensions.inflate
import kotlinx.android.synthetic.main.hotels_list_item.view.*

/** Adapter for the list of hotels.
 * @param hotelClick Invoked when a hotel is clicked. */
class HotelsAdapter(private val hotelClick: (Hotel) -> Unit) :
        RecyclerView.Adapter<HotelsAdapter.HotelViewHolder>() {

    var data: List<Hotel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        return HotelViewHolder(parent.inflate(R.layout.hotels_list_item), hotelClick)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        holder.bindHotel(data?.get(position) ?: return)
    }

    /** ViewHolder that holds a view for a single hotel. */
    class HotelViewHolder(view: View, val itemClick: (Hotel) -> Unit) :
            RecyclerView.ViewHolder(view) {
        val context = view.ctx
        val name: TextView = view.hotels_list_item_name
        val address: TextView = view.hotels_list_item_address
        val details: TextView = view.hotels_list_item_details
        val stars: RatingBar = view.hotels_list_item_stars
        val panel:View = view.hotels_list_item_panel

        /** Binds hotels info to the held view. */
        fun bindHotel(hotel: Hotel) {
            name.text = context.getString(R.string.hotel_name, hotel.name)
            address.text = context.getString(R.string.hotel_address, hotel.address)
            details.text = context.getString(R.string.hotel_details, hotel.distance, hotel.availableSuitsCount)
            stars.rating = hotel.stars
            panel.setOnClickListener { itemClick(hotel) }
        }
    }

}