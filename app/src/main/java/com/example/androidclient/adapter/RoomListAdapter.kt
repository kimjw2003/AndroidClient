package com.example.androidclient.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclient.R
import com.example.androidclient.data.RoomListData
import org.w3c.dom.Text

class RoomListAdapter(private val roomArrayList : ArrayList<RoomListData>) : RecyclerView.Adapter<RoomListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(roomArrayList[position])
    }

    override fun getItemCount(): Int {
        return roomArrayList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val roomName = itemView.findViewById<TextView>(R.id.room_list_room_name)
        val roomTeacher = itemView.findViewById<TextView>(R.id.room_list_teacher_name)
        val roomStatus = itemView.findViewById<TextView>(R.id.room_list_room_status)

        fun bind(item: RoomListData) {

            roomName.text = item.roomName
            roomTeacher.text = item.roomTeacher

            when(item.roomStatus){
                "예약가능" -> {
                    roomStatus.text = "예약가능"
                    roomStatus.setTextColor(Color.parseColor("#0049FF"))
                }
                "사용중" -> {
                    roomStatus.text = "사용중"
                    roomStatus.setTextColor(Color.parseColor("#FF0000"))
                }

            }

            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, //상대액티비티))
            }
        }

    }
}