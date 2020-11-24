package com.example.androidclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclient.R
import com.example.androidclient.data.MyTeamData

class MyTeamAdapter(val myTeamData: ArrayList<MyTeamData>) : RecyclerView.Adapter<MyTeamAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(data : MyTeamData){

            itemView.setOnClickListener {

            }
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