package com.example.telegram.ui.fragments

import com.example.telegram.R
import com.example.telegram.database.USER
import com.example.telegram.database.setNameToDatabase
import com.example.telegram.utils.showToast
import com.mikepenz.materialize.util.KeyboardUtil.hideKeyboard
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeNameFragment : BaseChangeFragment(R.layout.fragment_change_name) {

    override fun onResume() {
        super.onResume()

        initFullnameList()
    }

    private fun initFullnameList() {
        val fullNameList = USER.fullname.split(" ")
        if (fullNameList.size > 1) {
            settings_input_name.setText(fullNameList[0])
            settings_input_surname.setText(fullNameList[1])
        } else {
            settings_input_name.setText(fullNameList[0])
        }
    }


    override fun change() {
        val name = settings_input_name.text.toString()
        val surname = settings_input_surname.text.toString()

        if (name.isEmpty()) {
            showToast(getString(R.string.settings_toast_name_is_empty))
        } else {
            val fullName = "$name $surname"
            setNameToDatabase(fullName)
            hideKeyboard(this.activity)
        }
    }
}