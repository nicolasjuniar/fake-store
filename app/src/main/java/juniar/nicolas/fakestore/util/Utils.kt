package juniar.nicolas.fakestore.util

import android.content.Context
import android.view.View
import android.widget.Toast

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