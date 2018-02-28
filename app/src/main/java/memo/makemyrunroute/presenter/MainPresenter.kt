package memo.makemyrunroute.presenter

class MainPresenter(private var view: MainPresenter.View) : BasePresenter() {

    interface View {
        fun handleButtonClicked()
    }

    fun handleButtonClick() {
        view.handleButtonClicked()
    }
}