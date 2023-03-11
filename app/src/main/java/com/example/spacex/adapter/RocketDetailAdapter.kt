package com.example.spacex.adapter

import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.databinding.RocketLayoutBinding
import com.example.spacex.model.RocketsResponse
import com.example.spacex.ui.RocketDetailActivity
import com.squareup.picasso.Picasso

class RocketDetailAdapter : RecyclerView.Adapter<RocketDetailAdapter.ViewHolder>() {

    private var rocketList = ArrayList<RocketsResponse>()

    fun setRocketList(rocketList: List<RocketsResponse>) {
        this.rocketList = rocketList as ArrayList<RocketsResponse>
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RocketLayoutBinding) : RecyclerView.ViewHolder(binding.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RocketLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context,
                )
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val rocketName = "<b>Name:</b> " + rocketList[position].name
        holder.binding.rocketName.text = Html.fromHtml(rocketName)
        val rocketCountry = "<b>Country:</b> " + rocketList[position].country
        holder.binding.country.text = Html.fromHtml(rocketCountry)
        holder.binding.wikipedia.text = rocketList[position].wikipedia

        Picasso.get()
            .load(rocketList[position].flickr_images[0])
            .into(holder.binding.image)

        holder.binding.image.setOnClickListener(View.OnClickListener {

            val intent = Intent(holder.binding.image.context, RocketDetailActivity::class.java);
            intent.putExtra("rocketId", rocketList[position].id)
            holder.binding.image.context.startActivity(intent)

        })

    }

    override fun getItemCount(): Int {
        return rocketList.size
    }

}