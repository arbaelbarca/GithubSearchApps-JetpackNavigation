package com.arbaelbarca.githubsearchapps.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arbaelbarca.githubsearchapps.R
import com.arbaelbarca.githubsearchapps.model.modeluserlist.ItemsItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_user.*
import kotlinx.android.synthetic.main.layout_item_userlist.*

/**
 * A simple [Fragment] subclass.
 */
class DetailUserFragment : Fragment() {

    private var bundle: Bundle? = null
    private var itemsItem: ItemsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bundle = this.arguments
        itemsItem = bundle?.getParcelable("data")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initial();
    }

    private fun initial() {
        Glide.with(context!!)
                .load(itemsItem?.avatarUrl)
                .into(imgDetail)

        tvUsername.text = itemsItem!!.login
        tvProfileLink.text = itemsItem!!.htmlUrl

    }


}
