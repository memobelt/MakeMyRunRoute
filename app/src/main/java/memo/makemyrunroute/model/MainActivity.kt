package memo.makemyrunroute.model

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import memo.makemyrunroute.R
import memo.makemyrunroute.presenter.MainPresenter

class MainActivity : BaseActivity(), MainPresenter.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var presenter = MainPresenter(this)
        runPreferenceButton.setOnClickListener({view ->  presenter.handleButtonClick()})
    }

    override fun handleButtonClicked() {
        val dialogFragment = RunPreferencesFragment()
        dialogFragment.show(this@MainActivity.supportFragmentManager, "")
    }

}
