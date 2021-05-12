package com.example.telegram.ui.fragments

import com.example.telegram.R
import com.example.telegram.database.USER
import com.example.telegram.database.setBioToDatabase
import com.mikepenz.materialize.util.KeyboardUtil.hideKeyboard
import kotlinx.android.synthetic.main.fragment_change_bio.*

class ChangeBioFragment : BaseChangeFragment(R.layout.fragment_change_bio) {

    override fun onResume() {
        super.onResume()
        settings_input_bio.setText(USER.bio)
    }

    override fun change() {
        super.change()
        val newBio = settings_input_bio.text.toString()
        setBioToDatabase(newBio)
        hideKeyboard(this.activity)
    }
}