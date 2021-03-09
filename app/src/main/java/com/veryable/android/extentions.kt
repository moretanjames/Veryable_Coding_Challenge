package com.veryable.android

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("accountImage")
fun ImageView.setAccountImage(accountInfo: AccountInfo) {
    setImageResource(
        when (accountInfo.accountType) {
            "card" -> R.drawable.baseline_credit_card_black_48pt
            "bank" -> R.drawable.baseline_account_balance_black_48pt
            else -> R.drawable.baseline_account_balance_black_48pt
        }
    )
}

@BindingAdapter("accountName")
fun TextView.setAccountName(accountInfo: AccountInfo) {
    text = accountInfo.accountName
}

@BindingAdapter("accountDesc")
fun TextView.setAccountDesc(accountInfo: AccountInfo) {
    text = accountInfo.description
}

@BindingAdapter("accountTrans")
fun TextView.setAccountTrans(accountInfo: AccountInfo) {
    text = when (accountInfo.accountType) {
        "card" -> context.getString(R.string.account_type_card)
        "bank" -> context.getString(R.string.account_type_bank)
        else -> context.getString(R.string.account_type_error)
    }
}
