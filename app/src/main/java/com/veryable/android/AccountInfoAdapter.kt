package com.veryable.android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.veryable.android.databinding.ListItemAccountInfoBinding

// Recycler view adapter For Accounts view. Uses ListAdapter and DiffUtil
class AccountInfoAdapter(val clickListener: View.OnClickListener) : ListAdapter<AccountInfo, AccountInfoAdapter.ViewHolder>(AccountInfoDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        // set a click listener on the root of the view
        holder.binding.root.setOnClickListener(clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ListItemAccountInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AccountInfo) {
            binding.info = item
            binding.executePendingBindings()
            // set the tag to the info so that onClick, the info can be retrieved
            binding.root.tag = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAccountInfoBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class AccountInfoDiffCallback : DiffUtil.ItemCallback<AccountInfo>() {
    override fun areItemsTheSame(oldItem: AccountInfo, newItem: AccountInfo): Boolean {
        return oldItem.accountId == newItem.accountId
    }

    override fun areContentsTheSame(oldItem: AccountInfo, newItem: AccountInfo): Boolean {
        return oldItem == newItem
    }
}