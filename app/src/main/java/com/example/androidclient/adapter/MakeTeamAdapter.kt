package com.example.androidclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclient.R
import com.example.androidclient.data.MakeTeamData
import com.example.androidclient.data.MyRoomData

class MakeTeamAdapter(val makeTeamData: ArrayList<MakeTeamData>) : RecyclerView.Adapter<MakeTeamAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(data : MakeTeamData){

            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.make_team_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(makeTeamData[position])
    }

    override fun getItemCount(): Int {
        return makeTeamData.size
    }
}