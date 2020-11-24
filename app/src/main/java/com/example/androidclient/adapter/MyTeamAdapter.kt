package com.example.androidclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclient.R

class MyTeamAdapter(val myTeamData: ArrayList<String>) : RecyclerView.Adapter<MyTeamAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val teamName = itemView.findViewById<TextView>(R.id.item_TeamName)

        fun bind(data: String){
            teamName.text = data

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