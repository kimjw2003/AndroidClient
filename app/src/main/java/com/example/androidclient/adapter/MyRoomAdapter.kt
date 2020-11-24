package com.example.androidclient.adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.graphics.drawable.DrawableCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.androidclient.R
import com.example.androidclient.data.MyRoomData
import com.example.androidclient.data.response.RoomListInfo
import com.example.androidclient.dialog.RoomDialog

class MyRoomAdapter(val context: Context, val myRoomData: ArrayList<RoomListInfo>) : RecyclerView.Adapter<MyRoomAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val roomName = itemView.findViewById<TextView>(R.id.item_RoomName)
        val roomOrder = itemView.findViewById<TextView>(R.id.item_RoomOrder)

        fun bind(data: RoomListInfo){
            roomName.text = data.room
            if(data.room == "ableToBook") {
                roomOrder.text = "예약가능"
                roomOrder.setTextColor(Color.parseColor("#ff000d"))
            }else if(data.room == "accepted"){
                roomOrder.text = "예약 불가능"
                roomOrder.setTextColor(Color.parseColor("#11d154"))
            } else{
                roomOrder.text = "승인 대기"
            }

            itemView.setOnClickListener {
                val dialog = RoomDialog(context, data)
                dialog.callDialog()
                notifyDataSetChanged()

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_room_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(myRoomData[position])
    }

    override fun getItemCount(): Int {
        return myRoomData.size
    }
}