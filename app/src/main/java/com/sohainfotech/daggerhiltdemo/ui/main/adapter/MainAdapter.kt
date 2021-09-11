package com.sohainfotech.daggerhiltdemo.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sohainfotech.daggerhiltdemo.R
import com.sohainfotech.daggerhiltdemo.data.model.User
import com.sohainfotech.daggerhiltdemo.databinding.ItemLayoutBinding

class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {


    inner class DataViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return DataViewHolder(binding)
    }

    // bind the items with each item of the list languageList which than will be
    // shown in recycler view
    // to keep it simple we are not setting any image data to view
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        with(holder){
            with(users[position]){
                binding.textViewUserName.text = this.name
                binding.textViewUserEmail.text = this.email

                Glide.with(binding.imageViewAvatar.context)
                    .load(this.avatar)
                    .into(binding.imageViewAvatar)

            }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return users.size
    }

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}

/*class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            itemView.textViewUserName.text = user.name
            itemView.textViewUserEmail.text = user.email
            Glide.with(itemView.imageViewAvatar.context)
                .load(user.avatar)
                .into(itemView.imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])*/


//}