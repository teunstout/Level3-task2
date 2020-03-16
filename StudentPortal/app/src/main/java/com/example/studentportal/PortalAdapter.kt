package com.example.studentportal

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.portals_layout.view.*

const val PORTAL_EXTRA = "PORTAL_EXTRA"

class PortalAdapter(val portals: List<Portal> ) : RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    inner class ViewHolder(textview : View): RecyclerView.ViewHolder(textview) {
        fun bind(portal: Portal){
            itemView.txtHyperlink.text = portal.link

            itemView.setOnClickListener {
                openChromeTab(portal.link, itemView)
            }
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

    private fun openChromeTab(urlString: String, itemview: View){
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(itemview.context, Uri.parse(urlString))
    }
}