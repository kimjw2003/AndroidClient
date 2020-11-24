package com.example.androidclient.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidclient.R
import com.example.androidclient.adapter.MyRoomAdapter
import com.example.androidclient.adapter.RoomListAdapter
import com.example.androidclient.data.MyRoomData
import com.example.androidclient.data.request.User
import com.example.androidclient.data.response.RoomListInfo
import com.example.androidclient.data.response.RoomResponse
import com.example.androidclient.retrofit.RetrofitClient
import com.example.androidclient.room.DataBase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_myroom.*
import kotlinx.android.synthetic.main.fragment_myroom.view.*
import kotlinx.android.synthetic.main.fragment_myteam.*
import kotlinx.android.synthetic.main.fragment_myteam.myTeamRcView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRoomFragment : Fragment() {

    var myRoomList : ArrayList<RoomListInfo> = arrayListOf()
    lateinit var adapter : MyRoomAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_myroom, container, false)

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

        getMyRoom()
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
        RetrofitClient.getInstance().getMyRoom(User(DataBase.getInstance(requireActivity())!!.dao().getAll().get(0).user))
            .enqueue(object : Callback<List<RoomListInfo>>{
                override fun onResponse(call: Call<List<RoomListInfo>>, response: Response<List<RoomListInfo>>) {
                    if(response.code() == 200){
                        myRoomList.clear()
                        myRoomList = response.body() as java.util.ArrayList<RoomListInfo>
                        Log.d("TAG", "data $myRoomList")
                        val mAdapter = MyRoomAdapter(requireContext(), myRoomList)
                        myRoomRcView.setHasFixedSize(true)
                        myRoomRcView.adapter = mAdapter
                    }
                }

                override fun onFailure(call: Call<List<RoomListInfo>>, t: Throwable) {
                    Log.d("Logd", t.message.toString())
                }
            })
    }
}