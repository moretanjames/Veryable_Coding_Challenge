package com.veryable.android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.veryable.android.DetailActivity.Companion.INFO_KEY
import com.veryable.android.databinding.ActivityPayoutsListBinding

class PayoutsListActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPayoutsListBinding
    private lateinit var adapter: AccountInfoAdapter

    private val viewModel by viewModels<AccountInfoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.payouts_title)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_payouts_list)
        adapter = AccountInfoAdapter(this)
        binding.accountInfoRecycler.adapter = adapter

        viewModel.accountInfoLiveData.observe(this, Observer<MutableList<AccountInfo>> { accounts ->
            if (accounts.isNotEmpty()) {
                adapter.submitList(accounts)
            } else {
                Log.d(LOG_TAG, "No items in list, show error screen")
                binding.errorText.visibility = View.VISIBLE
            }
        })

        viewModel.getAccountInfo()
    }

    override fun onClick(item: View?) {
        val info = item?.tag as AccountInfo
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(INFO_KEY, info)
        }

        Log.d(LOG_TAG, "item clicked")
        startActivity(intent)
    }

    companion object {
        const val LOG_TAG = "PayoutsListActivity"
    }
}