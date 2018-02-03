package memo.makemyrunroute.model

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import memo.makemyrunroute.R
import memo.makemyrunroute.preferences.PreferencesManager

class RunPreferencesFragment : DialogFragment() {

    private var distanceAmountEdittext: EditText? = null
    private var distanceTypeTextview: TextView? = null
    private var distanceTypeSwitch: Switch? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreate(savedInstanceState)
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
                    handleDismissal()
                }
                .create()
                .apply {
                    setCanceledOnTouchOutside(true)
                }
    }

    private fun handleDismissal() {
        PreferencesManager.saveLastDistanceAmount(context, distanceAmountEdittext!!.text.toString())
        PreferencesManager.saveDistanceType(context, distanceTypeTextview!!.text.toString())
        dismiss()
    }

}
