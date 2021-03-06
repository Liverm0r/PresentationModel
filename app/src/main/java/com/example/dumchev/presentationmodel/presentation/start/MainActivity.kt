package com.example.dumchev.presentationmodel.presentation.start

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.example.dumchev.presentationmodel.R
import com.example.dumchev.presentationmodel.injection.Components
import com.example.dumchev.presentationmodel.presentation.base.RxBindableActivity
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : RxBindableActivity<IStartPresentationModel>() {

    @Inject lateinit override var model: StartPresentationModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Components.mainComponent().inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onBind(model: IStartPresentationModel) = with(model) {
        button.clicks().bindLogic(nextButtonPressed)
        rootLayout.clicks().bindLogic(screenTouch)

        message.bindToView { strId -> showMessage(getString(strId)) }
        goToSecondScreenCommand.bindToView { showMessage("I should go to the next activity") }
        bgColor.bindToView { colorId -> rootLayout.setBackgroundColor(resources.getColor(colorId)) }
        luckyNumbers.bindToView { numbers -> textView.text = numbers.toString() }
    }

    private fun showMessage(message: String) {
        Snackbar.make(rootLayout, message, Snackbar.LENGTH_SHORT).show()
    }
}
