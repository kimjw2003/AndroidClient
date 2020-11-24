package com.example.androidclient.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.androidclient.R
import com.example.androidclient.adapter.MakeTeamAdapter
import com.example.androidclient.adapter.RoomListAdapter
import com.example.androidclient.data.MakeTeamData
import com.example.androidclient.data.response.RoomResponse
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_maketeam.*
import java.util.ArrayList

class MakeTeamFragment : Fragment() {

    var teamList : ArrayList<MakeTeamData> = arrayListOf()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_maketeam, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        team_make_add_team.setOnClickListener {
            if(team_make_people_name.text.isEmpty())
            {
                Toast.makeText(context, "팀원 이름을 넣어주세요", Toast.LENGTH_SHORT).show()
            }
            else{
                teamList.add(MakeTeamData(team_make_people_name.text.toString()))
                team_make_people_name.setText("")
                val mAdapter = MakeTeamAdapter(teamList)
                make_team_recyclerview.setHasFixedSize(true)
                make_team_recyclerview.adapter = mAdapter
            }
        }
    }
}