package com.scanner.binpicking.util

import android.widget.Toast
import com.scanner.binpicking.BaseApplication

actual fun showToastMsg(
    msg: String,
    duration: ToastDurationType
) {

    var mContext = BaseApplication.instance

    mContext.let {
        when (duration){
            ToastDurationType.SHORT -> Toast.makeText(it, msg, Toast.LENGTH_SHORT).show()
            ToastDurationType.LONG -> Toast.makeText(it, msg, Toast.LENGTH_LONG).show()
        }
    }
}