package com.example.androidclient.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidclient.R
import com.example.androidclient.adapter.RoomListAdapter
import com.example.androidclient.data.request.School
import com.example.androidclient.data.response.RoomResponse
import com.example.androidclient.retrofit.RetrofitClient
import com.example.androidclient.sharedpreferences.App
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment() {

    var roomList : ArrayList<RoomResponse> = arrayListOf()
    val cal = Calendar.getInstance()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onResume() {
        super.onResume()
        getRoom()
        setDate()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arrow_left.setOnClickListener {
            cal.add(Calendar.DATE, -1)
            setDate()
        }

        arrow_right.setOnClickListener {
            cal.add(Calendar.DATE, +1)
            setDate()
        }
    }

    fun setDate()
    {

        val year = cal.get(Calendar.YEAR).toShort()
        val month = (cal.get(Calendar.MONTH) + 1).toShort()
        val day = (cal.get(Calendar.DATE)).toShort()
        room_list_date.text = ("${year}년 ${month}월 ${day}일")
        App.prefs.setDate("${year}년 ${month}월 ${day}일")
    }


    fun getRoom()
    {
        RetrofitClient.getInstance().getRoomList(School("all", 20201124)).enqueue(object : Callback<List<RoomResponse>> {
            override fun onResponse(
                call: Call<List<RoomResponse>>,
                response: Response<List<RoomResponse>>
            ) {
                roomList.clear()
                roomList = response.body() as ArrayList<RoomResponse>
                Log.d("TAG", "data $roomList")
                val mAdapter = RoomListAdapter(roomList, requireContext())
                room_list_recyclerview.setHasFixedSize(true)
                room_list_recyclerview.adapter = mAdapter
            }

            override fun onFailure(call: Call<List<RoomResponse>>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }

        })
    }
}