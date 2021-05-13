package com.example.telegram.utils

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

class AppChildEventListener(val onSuccess: (DataSnapshot) -> Unit) : ChildEventListener {
    override fun onCancelled(error: DatabaseError) {
        //TODO()
    }

    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        //TODO("Not yet implemented")
    }

    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
        //TODO("Not yet implemented")
    }

    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        onSuccess(snapshot)
    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
        //TODO("Not yet implemented")
    }

}