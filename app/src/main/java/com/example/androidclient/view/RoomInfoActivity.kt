package com.example.androidclient.view

import android.annotation.SuppressLint
import android.icu.number.Notation.simple
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.androidclient.R
import com.example.androidclient.data.request.BookRoom
import com.example.androidclient.data.request.Name
import com.example.androidclient.data.request.User
import com.example.androidclient.data.response.RoomResponse
import com.example.androidclient.data.response.Status
import com.example.androidclient.retrofit.RetrofitClient
import com.example.androidclient.room.DataBase
import com.example.androidclient.sharedpreferences.SharedPreferences
import kotlinx.android.synthetic.main.activity_room_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomInfoActivity : AppCompatActivity() {
    var teamList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_info)

        var selectTeam = ""

        text_date.text = intent.getStringExtra("date")
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {}

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, l: Long) {
                selectTeam = if(position == 0){
                    ""
                } else {
                    teamList[position - 1]
                }
            }

        }

        val roomName = intent.getStringExtra("roomName")

        val dataBase = DataBase.getInstance(this@RoomInfoActivity)!!.dao().getAll()[0]
        val name = dataBase.name

        RetrofitClient.getInstance().getUserTeam(User(name)).enqueue(object : Callback<List<String>>{
            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d("TAG", t.toString())
            }

            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if(response.isSuccessful){
                    teamList = (response.body() as ArrayList<String>?)!!
                    teamList.add(0, "팀 명")

                    for (t in teamList){
                        println(t)
                    }
                }
                Log.d("TAG", response.code().toString())
                val adapter = ArrayAdapter<String>(this@RoomInfoActivity, android.R.layout.simple_spinner_item, teamList)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

                spinner.adapter = adapter
            }


        })

        text_room_name.text = roomName
        if(roomName != null) {
            RetrofitClient.getInstance().getRoom(Name(roomName)).enqueue(object : Callback<RoomResponse>{
                override fun onFailure(call: Call<RoomResponse>, t: Throwable) {}

                @SuppressLint("SetTextI18n")
                override fun onResponse(
                    call: Call<RoomResponse>,
                    response: Response<RoomResponse>
                ) {
                    if(response.isSuccessful){
                        text_teacher.text = response.body()!!.charge + " 선생님"
                        text_limit_man.text = response.body()!!.acceptable.toString() + " 명"
                        if(response.body()!!.beem == 1){
                            img_beam.setImageResource(R.drawable.beam_no)
                        } else {
                            img_beam.setImageResource(R.drawable.beam_yes)
                        }

                        if(response.body()!!.board == 1){
                            img_board.setImageResource(R.drawable.board_no)
                        } else {
                            img_board.setImageResource(R.drawable.board_yes)
                        }
                    }
                }

            })


            btn_reservation.setOnClickListener {
                val temp = SharedPreferences(this@RoomInfoActivity).getDate("")
                //2 0 2 0 년   1 1 월   2 5 일
                //
                val date = (temp.substring(0,4) + temp.substring(6,8) + temp.substring(10,12)).toInt()
                if(selectTeam != "" && text_reason.text.toString().isNotEmpty()) {
                    RetrofitClient.getInstance().bookRoom(
                        BookRoom(
                            selectTeam,
                            roomName,
                            date,
                            text_reason.text.toString()
                        )
                    ).enqueue(object : Callback<Status> {
                        override fun onFailure(call: Call<Status>, t: Throwable) {
                            Log.d("TAG", t.toString())
                        }

                        override fun onResponse(call: Call<Status>, response: Response<Status>) {
                            if (response.isSuccessful) {
                                if (response.body()!!.status == 200) {
                                    Toast.makeText(this@RoomInfoActivity, "예약이 완료되었습니다", Toast.LENGTH_LONG).show()
                                    finish()
                                }
                            }
                            Log.d("TAG", response.message().toString())
                        }

                    })
                }
            }
        }

    }

}