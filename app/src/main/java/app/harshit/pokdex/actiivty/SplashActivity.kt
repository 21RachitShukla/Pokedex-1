package app.harshit.pokdex.actiivty

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import app.harshit.pokdex.R
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

fun isUserSignedIn() = FirebaseAuth.getInstance().currentUser != null

fun startAuth(activity: Activity) {
    val alertDialog = AlertDialog.Builder(activity)
            .setTitle(activity.getString(R.string.login_text))
            .setMessage(activity.getString(R.string.logout_message))
            .setPositiveButton(activity.getString(R.string.yes)) { dialog, _ ->
                dialog.cancel()
                activity.startActivity(Intent(activity, IntroActivity::class.java))
                activity.finish()
            }
            .setNegativeButton(activity.getString(R.string.no)) { dialog, _ ->
                //Do nothing
                dialog.cancel()
            }.create()
    alertDialog.show()

}

fun logoutAndStartAuth(activity: Activity) {
    val alertDialog = AlertDialog.Builder(activity)
            .setTitle(activity.getString(R.string.logout_text))
            .setMessage(activity.getString(R.string.logout_message))
            .setPositiveButton(activity.getString(R.string.yes)) { dialog, _ ->
                dialog.cancel()
                AuthUI.getInstance().signOut(activity)
                activity.startActivity(Intent(activity, IntroActivity::class.java))
                activity.finish()
            }
            .setNegativeButton(activity.getString(R.string.no)) { dialog, _ ->
                //Do nothing
                dialog.cancel()
            }.create()
    alertDialog.show()
}

fun getCurrentUser() = FirebaseAuth.getInstance().currentUser

const val RC_SIGN_IN = 4204

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("INTRO", true))
            startActivity(Intent(this, IntroActivity::class.java))
        else
            startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}