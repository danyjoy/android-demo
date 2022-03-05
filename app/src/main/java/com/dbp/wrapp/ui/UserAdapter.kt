package com.dbp.wrapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dbp.wrapp.R
import com.dbp.wrapp.network.responseModels.EmpResponse
import com.squareup.picasso.Picasso

class UserAdapter(private val userList: List<EmpResponse>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    private lateinit var clickListener : onItemClickListener
    private var newList = userList

    interface onItemClickListener{
        fun onClickItem(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        clickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item,
        parent,false)
        return MyViewHolder(itemView, clickListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data  = userList[position]
        holder.name?.text = data.name
        holder.company?.text = data.company?.companyName
        if (!data.profileImage.isNullOrBlank()) {
            Picasso.get().load(data.profileImage).into(holder.imageView)
        }
    }

    override fun getItemCount() = userList.size

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val name: TextView? = itemView.findViewById(R.id.tvName)
        val company: TextView?= itemView.findViewById(R.id.tvCompany)
        val imageView: ImageView? = itemView.findViewById(R.id.profile_image)

        init {
            itemView.setOnClickListener {
                listener.onClickItem(position = adapterPosition)
            }
        }
    }
}