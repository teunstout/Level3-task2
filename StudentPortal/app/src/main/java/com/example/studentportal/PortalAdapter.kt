package com.example.studentportal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.portals_layout.view.*

class PortalAdapter(val portals: List<Portal> ) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    inner class ViewHolder(textview : View): RecyclerView.ViewHolder(textview) {
        fun bind(portal: Portal){
            itemView.txtHyperlink.text = portal.link
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.portals_layout, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(portals[position])
    }
}