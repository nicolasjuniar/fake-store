package juniar.nicolas.fakestore.util

import android.content.SharedPreferences

class FakeStoreSharedPreference(sharedPreferences: SharedPreferences) {
    var loggedUsername: String by SharedPreferenceEditor(sharedPreferences, "USERNAME", "")
}