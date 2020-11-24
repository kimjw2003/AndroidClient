package com.example.androidclient.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidclient.R
import com.example.androidclient.adapter.MyRoomAdapter
import com.example.androidclient.data.MyRoomData
import kotlinx.android.synthetic.main.fragment_myroom.*
import kotlinx.android.synthetic.main.fragment_myroom.view.*

class MyRoomFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_myroom, container, false)

        val myRoomList = ArrayList<MyRoomData>()
            myRoomList.add(MyRoomData("room1", "예약가능"))

        val adapter = MyRoomAdapter(requireContext(),myRoomList)
        view.myRoomRcView.adapter = adapter


        return view
    }

}