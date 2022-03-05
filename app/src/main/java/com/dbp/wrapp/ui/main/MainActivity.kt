package com.dbp.wrapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dbp.wrapp.R
import com.dbp.wrapp.database.EmpDatabase
import com.dbp.wrapp.databinding.ActivityMainBinding
import com.dbp.wrapp.network.responseModels.EmpResponse
import com.dbp.wrapp.ui.NetworkCallListener
import com.dbp.wrapp.ui.UserAdapter
import com.dbp.wrapp.ui.user.UserDetailsActivity
import com.dbp.wrapp.util.hide
import com.dbp.wrapp.util.show
import com.dbp.wrapp.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable
import java.util.*


class MainActivity : AppCompatActivity(), NetworkCallListener {
    var tempList : ArrayList<EmpResponse> = arrayListOf()
    lateinit var empList : ArrayList<EmpResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = EmpDatabase(this)
        val factory = MainViewModelFactory(db)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        binding.viewmodel = viewModel

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        viewModel.networkCallListener = this
        empList = arrayListOf()

        /**
         * Data from the db
         * if db is empty, get from the API call
         */
        progressBar.show()
        viewModel.getEmployeeDataFromDB().observe(this) {
            if (it.isNotEmpty()) {
                progressBar.hide()
                empList.addAll(it!!)

                tempList = it as ArrayList<EmpResponse>
                val adapter = UserAdapter(tempList)
                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(object : UserAdapter.onItemClickListener {
                    override fun onClickItem(position: Int) {
                        val intent = Intent(this@MainActivity, UserDetailsActivity::class.java)
                        val bundle = Bundle()
                        bundle.putSerializable("data", tempList[position] as Serializable)
                        intent.putExtra("bundle", bundle)
                        startActivity(intent)
                    }
                })

            } else {
                viewModel.getEmployeeData()
            }
        }
    }

    override fun onStarted() {
        progressBar.show()
    }

    override fun onSuccess(message: List<EmpResponse>?) {
        progressBar.hide()
        message.let {
            empList.addAll(it!!)
        }

        if (message != null) {
            tempList= message as ArrayList<EmpResponse>
            val adapter = UserAdapter(tempList)
            recyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : UserAdapter.onItemClickListener{
                override fun onClickItem(position: Int) {
                    val intent = Intent(this@MainActivity,UserDetailsActivity::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable("data",tempList[position] as Serializable)
                    intent.putExtra("bundle",bundle)
                    startActivity(intent)
                }
            })

        }

    }

    override fun onFailure(message: String) {
        progressBar.hide()
        toast(message)
    }

    /**
     * Search functionality for the app
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        val item = menu?.findItem(R.id.search_item)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                tempList.clear()
                val searchString = query!!.lowercase(Locale.getDefault())
                if (searchString.isNotEmpty()){
                    empList.forEach {
                        if (it.name!!.lowercase().contains(searchString) ||
                                it.email!!.contains(searchString)){
                            tempList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    tempList.clear()
                    tempList.addAll(empList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                tempList.clear()
                val searchString = newText!!.lowercase(Locale.getDefault())
                if (searchString.isNotEmpty()){
                    empList.forEach {
                        if (it.name!!.lowercase().contains(searchString)){
                            tempList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    tempList.clear()
                    tempList.addAll(empList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}