package juniar.nicolas.fakestore.util

import android.content.SharedPreferences

class FakeStoreSharedPreference(sharedPreferences: SharedPreferences) {
    var token: String by SharedPreferenceEditor(sharedPreferences, "token", "")
}