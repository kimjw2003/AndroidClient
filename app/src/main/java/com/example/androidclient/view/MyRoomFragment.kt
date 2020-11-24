package com.example.androidclient.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidclient.R
import com.example.androidclient.adapter.MyRoomAdapter
import com.example.androidclient.data.MyRoomData
import com.example.androidclient.room.DataBase
import kotlinx.android.synthetic.main.fragment_myroom.*
import kotlinx.android.synthetic.main.fragment_myroom.view.*
import kotlinx.android.synthetic.main.fragment_myteam.*
import kotlinx.android.synthetic.main.fragment_myteam.myTeamRcView

class MyRoomFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_myroom, container, false)

        val myRoomList = ArrayList<MyRoomData>()
            myRoomList.add(MyRoomData("room1", "예약가능"))

        val adapter = MyRoomAdapter(requireContext(), myRoomList)
        view.myRoomRcView.adapter = adapter

        view.team_Btn.setOnClickListener {
             setFrag(0)
             myRoomList.clear()
             adapter.notifyDataSetChanged()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        school_Tv.text = DataBase.getInstance(requireContext())!!.dao().getAll().get(0).school
        name_Tv.text = DataBase.getInstance(requireContext())!!.dao().getAll().get(0).name
        grade_Tv.text = DataBase.getInstance(requireContext())!!.dao().getAll().get(0).grade.toString()
        class_Tv.text = DataBase.getInstance(requireContext())!!.dao().getAll().get(0).classs.toString()
    }

    private fun setFrag(fragNum : Int) {
        val ft = childFragmentManager.beginTransaction() //화면 교체를 위한 트랜잭션
        when(fragNum){
            0 -> {
                ft.replace(R.id.myRoom_Frame, MyTeamFragment()).commit()
            }
        }
    }

    private fun getMyRoom(){

    }
}