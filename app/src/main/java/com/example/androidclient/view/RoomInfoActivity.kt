package com.example.androidclient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.androidclient.R
import kotlinx.android.synthetic.main.activity_room_info.*

class RoomInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_info)

        var teamList = ArrayList<String>()

        var selectTeam = ""

        teamList.add("팀 명")
        // 서버에서 팀 리스트 얻어오기

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