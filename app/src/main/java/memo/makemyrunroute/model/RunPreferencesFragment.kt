package memo.makemyrunroute.model

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import memo.makemyrunroute.R
import memo.makemyrunroute.preferences.PreferencesManager
import memo.makemyrunroute.presenter.RunPreferencePresenter

class RunPreferencesFragment : DialogFragment(), RunPreferencePresenter.View {

    private var distanceAmountEdittext: EditText? = null
    private var distanceTypeTextview: TextView? = null
    private var distanceTypeSwitch: Switch? = null

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreate(savedInstanceState)
        val presenter = RunPreferencePresenter(this)
        val view = activity.layoutInflater.inflate(R.layout.fragment_run_preferences, null)
        distanceAmountEdittext = view.findViewById(R.id.distanceAmountEdittext)
        distanceTypeTextview = view.findViewById(R.id.distanceTypeTextview)
        distanceTypeSwitch = view.findViewById(R.id.distanceTypeSwitch)
        distanceAmountEdittext!!.setText(PreferencesManager.getLastDistanceAmount(context))
        distanceTypeTextview!!.text = PreferencesManager.getDistanceType(context)
        distanceTypeSwitch!!.isEnabled = distanceTypeTextview!!.text.toString() == PreferencesManager.Direction.MILE
        return AlertDialog.Builder(activity)
                .setTitle("Seek")
                .setView(view)
                .setPositiveButton("OK") { _, _ ->
                    presenter.handleDissmissal()
                }
                .create()
                .apply {
                    setCanceledOnTouchOutside(true)
                }
    }

    override fun handleDismissPress() {
        PreferencesManager.saveLastDistanceAmount(context, this.distanceAmountEdittext!!.text.toString())
        PreferencesManager.saveDistanceType(context, distanceTypeTextview!!.text.toString())
        startActivity(Intent(activity, MapsActivity::class.java))
        dismiss()
    }

}
