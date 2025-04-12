package juniar.nicolas.fakestore.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

fun Context.showToast(text: Any, isLong: Boolean = false) {
    Toast.makeText(this, text.toString(), if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT)
        .show()
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
    this.isEnabled = true
}

inline fun <reified T : Activity> Activity.openActivity(
    bundle: Bundle? = null,
    isFinishAffinity: Boolean = false,
    isFinish: Boolean = false,
    isClearTopSingleTop: Boolean = false
) {
    val intent = Intent(this, T::class.java)
    if (isClearTopSingleTop) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
    }
    if (bundle != null) {
        intent.putExtras(bundle)
    }
    startActivity(intent)
    if (isFinish) {
        finish()
    }
    if (isFinishAffinity) {
        finishAffinity()
    }
}

fun ImageView.onLoad(
    context: Context,
    url: String
) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun Dialog.isVisible(visible: Boolean) {
    if (visible) {
        this.show()
    } else {
        this.dismiss()
    }
}

fun Float?.orEmpty() = this ?: 0F

fun Int?.orEmpty() = this ?: 0