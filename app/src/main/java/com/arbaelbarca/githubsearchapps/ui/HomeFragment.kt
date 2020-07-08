package com.arbaelbarca.githubsearchapps.ui


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arbaelbarca.githubsearchapps.R
import com.arbaelbarca.githubsearchapps.adapter.AdapterListUser
import com.arbaelbarca.githubsearchapps.baseui.BaseFragment
import com.arbaelbarca.githubsearchapps.model.modeluserlist.ItemsItem
import com.arbaelbarca.githubsearchapps.onclick.OnClickItem
import com.arbaelbarca.githubsearchapps.presenter.ListUserPresenter
import com.arbaelbarca.githubsearchapps.presenter.ListUserPresenterImpl
import com.arbaelbarca.githubsearchapps.view.MainContract
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(), MainContract.MainView, OnClickItem {


    private var listUserPresenter: ListUserPresenter? = null
    private var adapterListUser: AdapterListUser? = null

    private var txtSearchGithub: EditText? = null
    private var rvListUser: RecyclerView? = null
    private var progressList: ProgressBar? = null
    private var txtNotFound: TextView? = null

    private var navController: NavController? = null
    private var detailUserFragment: DetailUserFragment? = null
    private var bundle: Bundle? = null

    fun HomeFragment() {

    }

    override fun clickItem(pos: Int) {
        val passData = adapterListUser?.itemsItemArrayList?.get(pos)
        detailUserFragment = DetailUserFragment()
        bundle = Bundle()
        bundle?.putParcelable("data", passData)
        detailUserFragment?.arguments = bundle
        navController!!.navigate(R.id.action_homeFragment_to_detailUserFragment, bundle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        rvListUser = view.findViewById(R.id.rvListUser)
        progressList = view.findViewById(R.id.progressList)
        txtSearchGithub = view.findViewById(R.id.txtSearchGithub)
        txtNotFound = view.findViewById(R.id.txtNotFound)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(activity!!, R.id.my_nav_host_fragment)

        init()
        iniRv()
    }

    private fun iniRv() {
        rvListUser!!.adapter = adapterListUser
        rvListUser!!.layoutManager = LinearLayoutManager(activity)
        rvListUser!!.setHasFixedSize(true)
        adapterListUser?.setOnClick(onClickItem = this)
    }

    private fun init() {
        adapterListUser = AdapterListUser(activity)
        listUserPresenter = ListUserPresenter(this, ListUserPresenterImpl(this))
        listUserPresenter!!.requestFromDataServer()

        txtSearchGithub!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                if (charSequence.isNotEmpty()) {
                    adapterListUser!!.itemsItemArrayList?.clear()
                    listUserPresenter!!.refreshData(charSequence.toString())
                    adapterListUser!!.notifyDataSetChanged()
                    rvListUser!!.visibility = View.VISIBLE
                } else {
                    rvListUser!!.visibility = View.GONE
                }
            }

            override fun afterTextChanged(editable: Editable) {

            }
        })
    }

    override fun showProgress() {
        showView(progressList!!)

    }

    override fun hideProgress() {
        hideView(progressList!!)

    }

    override fun erro403() {
        showView(txtNotFound!!)
        txtNotFound!!.text = activity!!.getString(R.string.text_error_server)
        showView(rvListUser!!)

    }

    override fun error422() {
        showView(txtNotFound!!)
        txtNotFound!!.text = activity!!.getString(R.string.text_notfound)
        showView(rvListUser!!)
    }

    override fun setDataToRecyclerView(modelListUsers: ArrayList<ItemsItem>) {
        if (modelListUsers.size > 0) {
            adapterListUser!!.itemsItemArrayList = modelListUsers
            adapterListUser!!.notifyDataSetChanged()
        } else {
            showToast("Tidak ada data")
        }
    }

    override fun onResponseFailure(throwable: Throwable) {
        listUserPresenter!!.onFailure(throwable)
    }


}
