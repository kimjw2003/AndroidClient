package com.example.androidclient.view

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.androidclient.R
import com.example.androidclient.data.request.Name
import com.example.androidclient.data.response.RoomResponse
import com.example.androidclient.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_room_info.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_info)

        var teamList = ArrayList<String>()

        var selectTeam = ""

        teamList.add("팀 명")

        text_date.text = intent.getStringExtra("date")

        val roomName = intent.getStringExtra("roomName")

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
        }

        spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, teamList)


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
    }
}