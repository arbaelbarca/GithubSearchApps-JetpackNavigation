package com.arbaelbarca.githubsearchapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView

import com.arbaelbarca.githubsearchapps.R
import com.arbaelbarca.githubsearchapps.model.modeluserlist.ItemsItem
import com.arbaelbarca.githubsearchapps.onclick.OnClickItem
import com.bumptech.glide.Glide

import java.util.ArrayList

class AdapterListUser(private val context: FragmentActivity?) : RecyclerView.Adapter<AdapterListUser.ViewHolder>() {
    var itemsItemArrayList: ArrayList<ItemsItem>? = null

    init {
        itemsItemArrayList = ArrayList()
    }

    var onClickItem: OnClickItem? = null

    fun setOnClick(onClickItem: OnClickItem) {
        this.onClickItem = onClickItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.layout_item_userlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsItem = itemsItemArrayList!![position]

        holder.txtNameUser.text = itemsItem.login

        if (context != null) {
            Glide.with(context)
                    .load(itemsItem.avatarUrl)
                    .into(holder.imgUser)
        }

        holder.itemView.setOnClickListener {
            if (onClickItem != null)
                onClickItem?.clickItem(position)
        }
    }

    override fun getItemCount(): Int {
        return if (itemsItemArrayList == null) 0 else itemsItemArrayList!!.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtNameUser: TextView = itemView.findViewById(R.id.txtUser)
        var imgUser: ImageView = itemView.findViewById(R.id.imgUser)

    }
}
