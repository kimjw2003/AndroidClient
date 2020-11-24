package com.example.androidclient.view

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidclient.R
import com.example.androidclient.adapter.RoomListAdapter
import com.example.androidclient.data.request.School
import com.example.androidclient.data.response.RoomResponse
import com.example.androidclient.retrofit.RetrofitClient
import com.example.androidclient.room.DataBase
import com.example.androidclient.sharedpreferences.App
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_myroom.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class HomeFragment : Fragment() {

    var roomList : ArrayList<RoomResponse> = arrayListOf()
    var date = 0
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
        setDate()
        getRoom()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        arrow_left.setOnClickListener {
            cal.add(Calendar.DATE, -1)
            setDate()
            getRoom()
        }

        arrow_right.setOnClickListener {
            cal.add(Calendar.DATE, +1)
            setDate()
            getRoom()
        }
    }

    fun setDate() {
        val year = cal.get(Calendar.YEAR).toShort()
        val month = (cal.get(Calendar.MONTH) + 1).toShort()
        val day = (cal.get(Calendar.DATE)).toShort()
        date = (year.toString() + month.toString() + day.toString()).toInt()
        Log.d("TAG", date.toString())
        room_list_date.text = ("${year}년 ${month}월 ${day}일")
        App.prefs.setDate("${year}년 ${month}월 ${day}일")

        setBackGroundColor()
    }

    fun setBackGroundColor(){
        when(DataBase.getInstance(requireContext())!!.dao().getAll().get(0).school)
        {
            "대덕" -> {
                home_constraintLayout.setBackgroundColor(Color.parseColor("#AEF0E6"))
            }
            "대구" -> {
                home_constraintLayout.setBackgroundColor(Color.parseColor("#AED5F8"))
            }
            "광주" -> {
                home_constraintLayout.setBackgroundColor(Color.parseColor("#AEB6FF"))
            }
        }
    }

    fun getRoom()
    {
        Log.d("TAG", "school ${DataBase.getInstance(requireContext())!!.dao().getAll().get(0).school}")
        Log.d("TAG", "date ${date}")
        RetrofitClient.getInstance().getRoomList(School(DataBase.getInstance(requireContext())!!.dao().getAll().get(0).school, date)).enqueue(object : Callback<List<RoomResponse>> {
            override fun onResponse(
                call: Call<List<RoomResponse>>,
                response: Response<List<RoomResponse>>
            ) {
                if (response.code() == 200) {
                    roomList.clear()
                    roomList = response.body() as ArrayList<RoomResponse>
                    Log.d("TAG", "data $roomList")
                    val mAdapter = RoomListAdapter(roomList, requireContext())
                    room_list_recyclerview.setHasFixedSize(true)
                    room_list_recyclerview.adapter = mAdapter
                }
            }

            override fun onFailure(call: Call<List<RoomResponse>>, t: Throwable) {
                Log.d("TAG", t.message.toString())
            }

        })
    }
}