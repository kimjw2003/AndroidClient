package com.example.androidclient.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclient.R
import com.example.androidclient.data.MakeTeamData
import com.example.androidclient.data.MyRoomData

class MakeTeamAdapter(val makeTeamData: ArrayList<MakeTeamData>) : RecyclerView.Adapter<MakeTeamAdapter.Holder>() {

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

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val name = itemView.findViewById<TextView>(R.id.item_team_people_name)

        fun bind(data : MakeTeamData){
            Log.d("TAG", data.teamMateName)
            name.text = data.teamMateName
        }
    }
}