package memo.makemyrunroute.presenter

class MainPresenter(view: MainPresenter.View) : BasePresenter() {
    var view = view

    interface View {
        fun handleButtonClicked()
    }

    fun handleButtonClick() {
        view.handleButtonClicked()
    }
}