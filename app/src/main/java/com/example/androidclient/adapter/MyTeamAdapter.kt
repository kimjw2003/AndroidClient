package com.example.androidclient.adapter

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclient.R
import com.example.androidclient.data.TeamInfo
import com.example.androidclient.data.request.Name
import com.example.androidclient.retrofit.RetrofitClient
import com.example.androidclient.room.DataBase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTeamAdapter( val myTeamData: ArrayList<String>, val context: Context) : RecyclerView.Adapter<MyTeamAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val teamName = itemView.findViewById<TextView>(R.id.item_TeamName)

        fun bind(data: String){
            teamName.text = data

            itemView.setOnClickListener {
                getMyTeamMate(data)
            }
        }

        private fun getMyTeamMate(teamName : String) {
            RetrofitClient.getInstance().getTeamInfo(Name(teamName))
                .enqueue(object : Callback<TeamInfo>{
                    override fun onResponse(call: Call<TeamInfo>, response: Response<TeamInfo>) {
                        Log.d("TAG", "들어옴2")
                        if(response.code() == 200){
                            val builder = AlertDialog.Builder(context)
                            val dialogView = LayoutInflater.from(context).inflate(R.layout.team_dialog, null)
                            builder.setView(dialogView)
                                .setTitle("내 팀원들")
                                .setMessage(""+response.body()?.members)
                                .setNegativeButton("확인"){
                                        dialogView, which->
                                }.show()
                        }
                    }
                    override fun onFailure(call: Call<TeamInfo>, t: Throwable) {
                        Log.d("TAG", "들어옴3")
                    }

                })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_team_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(myTeamData[position])
    }

    override fun getItemCount(): Int {
        return myTeamData.size
    }
}