package com.example.androidclient.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclient.R
import com.example.androidclient.data.RoomListData
import com.example.androidclient.data.response.RoomResponse
import com.example.androidclient.sharedpreferences.App
import com.example.androidclient.view.HomeFragment
import com.example.androidclient.view.RoomInfoActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.w3c.dom.Text

class RoomListAdapter(private val roomArrayList : ArrayList<RoomResponse>, val context: Context) : RecyclerView.Adapter<RoomListAdapter.ViewHolder>() {

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

        fun bind(item: RoomResponse) {

            roomName.text = item.name
            roomTeacher.text = item.charge

            when(item.state){
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
                val intent = Intent(itemView.context, RoomInfoActivity::class.java)
                intent.putExtra("date", App.prefs.getData(""))
                Log.d("TAG", App.prefs.getData(""))
                itemView.context.startActivity(intent)
            }
        }

    }
}