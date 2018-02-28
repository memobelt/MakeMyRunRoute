package memo.makemyrunroute.model

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import memo.makemyrunroute.R
import memo.makemyrunroute.network.Point
import memo.makemyrunroute.network.RestAPI
import memo.makemyrunroute.presenter.MainPresenter
import java.util.*

class MainActivity : BaseActivity(), MainPresenter.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val presenter = MainPresenter(this)
        runPreferenceButton.setOnClickListener({ presenter.handleButtonClick() })
    }

    override fun handleButtonClicked() {
        val dialogFragment = RunPreferencesFragment()
//        startActivity(Intent(this, MapsActivity::class.java))
//        dialogFragment.show(this@MainActivity.supportFragmentManager, "")
        val restAPI = RestAPI()
        restAPI.getPlaceId(Point(42.341956f, -71.122378f)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
//                    Toast.makeText(this@MainActivity, it.snappedPoints[0].placeId, Toast.LENGTH_SHORT).show()
                    it.snappedPoints[0].placeId?.let {
                        it1 -> restAPI.getStreets(it1)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                Toast.makeText(this@MainActivity, it.result.address_components[1].short_name, Toast.LENGTH_SHORT).show()
                            }
                    }
                }
    }

}
