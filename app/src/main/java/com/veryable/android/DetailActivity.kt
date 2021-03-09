package com.veryable.android

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.veryable.android.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var info: AccountInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        supportActionBar?.apply {
            title = getString(R.string.details_title)
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }

        info = (intent?.getSerializableExtra(INFO_KEY)
            ?: savedInstanceState?.getSerializable(INFO_KEY)) as AccountInfo

        binding.accountName.setAccountName(info)
        binding.accountDesc.setAccountDesc(info)
        binding.accountImage.setAccountImage(info)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.accountImage.imageTintList =
                ColorStateList.valueOf(resources.getColor(R.color.blue, theme))
            binding.buttonDone.setBackgroundColor(resources.getColor(R.color.blue, theme))
        }

        binding.buttonDone.setOnClickListener {
            finish()
        }

        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            else -> {
                Log.d(LOG_TAG, "Unknown menu item clicked")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val LOG_TAG = "DetailsActivity"
        const val INFO_KEY = "info"
    }
}