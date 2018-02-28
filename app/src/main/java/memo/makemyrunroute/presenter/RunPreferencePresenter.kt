package memo.makemyrunroute.presenter

class RunPreferencePresenter(private var view: RunPreferencePresenter.View) : BasePresenter() {
    interface View {
        fun handleDismissPress()
    }

    fun handleDissmissal() {
        view.handleDismissPress()
    }
}