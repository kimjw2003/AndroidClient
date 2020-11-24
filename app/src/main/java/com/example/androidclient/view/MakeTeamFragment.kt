package com.example.androidclient.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.androidclient.R
import com.example.androidclient.adapter.MakeTeamAdapter
import com.example.androidclient.data.MakeTeamData
import com.example.androidclient.data.TeamInfo
import com.example.androidclient.data.response.Status
import com.example.androidclient.module.MakeTeamDialog
import com.example.androidclient.retrofit.RetrofitClient
import com.example.androidclient.room.DataBase
import kotlinx.android.synthetic.main.fragment_maketeam.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MakeTeamFragment : Fragment() {

    var teamList: ArrayList<MakeTeamData> = arrayListOf()
    var memberList: ArrayList<String> = arrayListOf()

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

        make_team_class.text = DataBase.getInstance(requireContext())!!.dao().getAll().get(0).user.substring(0, 4)
        make_team_name.text = DataBase.getInstance(requireContext())!!.dao().getAll().get(0).name


        team_make_add_team.setOnClickListener {
            if (team_make_people_name.text.isEmpty()) {
                Toast.makeText(
                    requireActivity().applicationContext,
                    "팀원 이름을 넣어주세요",
                    Toast.LENGTH_SHORT
                ).show()
                Log.d("TAG", "하이")
            } else {
                teamList.add(MakeTeamData(team_make_people_name.text.toString()))
                memberList.add(team_make_people_name.text.toString())
                team_make_people_name.setText("")
                val mAdapter = MakeTeamAdapter(teamList)
                make_team_recyclerview.setHasFixedSize(true)
                make_team_recyclerview.adapter = mAdapter
            }
        }

        make_team_team_make.setOnClickListener {
            postTeam()
        }

    }

    fun postTeam() {
        val sweetAlertDialog =
            SweetAlertDialog(context, SweetAlertDialog.PROGRESS_TYPE)
        sweetAlertDialog.progressHelper.barColor = Color.parseColor("#0DE930")
        sweetAlertDialog
            .setTitleText("로딩중")
            .setCancelable(false)
        sweetAlertDialog.show()

        val makeTeamDialog = MakeTeamDialog()

        RetrofitClient.getInstance()
            .addTeam(
                TeamInfo(
                    make_team_team_name.text.toString(),
                    DataBase.getInstance(requireContext())!!.dao().getAll().get(0).user,
                    memberList
                )
            )
            .enqueue(object : Callback<Status> {
                override fun onResponse(call: Call<Status>, response: Response<Status>) {
                    makeTeamDialog.connectionSuccess(
                        response,
                        requireContext(),
                        sweetAlertDialog
                    )
                }

                override fun onFailure(call: Call<Status>, t: Throwable) {
                    Toast.makeText(
                        requireActivity().applicationContext,
                        "팀 생성이 실패하였습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }
}