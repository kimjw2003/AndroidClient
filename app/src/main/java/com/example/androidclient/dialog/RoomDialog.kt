package com.example.androidclient.dialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Window
import android.widget.DatePicker
import android.widget.Toast
import com.example.androidclient.R
import com.example.androidclient.data.request.BookDelete
import com.example.androidclient.data.request.ChangeBook
import com.example.androidclient.data.response.RoomListInfo
import com.example.androidclient.data.response.Status
import com.example.androidclient.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.room_dialog.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomDialog(val context: Context, val data : RoomListInfo) : DatePickerDialog.OnDateSetListener {
    val oldDate = data.date

    fun callDialog(){

        val dialog = Dialog(context);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.room_dialog);
        dialog.show();


        dialog.edit_Time.setOnClickListener {

            val datePickerDialog = DatePickerDialog(context, this, data.date/10000, (data.date%10000)/100-1, data.date%100)
            Log.d("DATE", data.date.toString())
            Log.d("DATE", (data.date/10000).toString())
            Log.d("DATE", ((data.date%10000)/100-1).toString())
            Log.d("DATE", (data.date%100).toString())
            datePickerDialog.show()
            dialog.dismiss()

        }

        dialog.delete_Time.setOnClickListener {
            RetrofitClient.getInstance().bookDelete(BookDelete(data.team,data.room, data.date)).enqueue(object : Callback<Status>{
                override fun onFailure(call: Call<Status>, t: Throwable) {}

                override fun onResponse(call: Call<Status>, response: Response<Status>) {
                    if(response.isSuccessful){
                        Toast.makeText(context, "예약이 취소되었습니다", Toast.LENGTH_LONG).show()
                        dialog.dismiss()
                    }
                }

            })
        }

    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        RetrofitClient.getInstance().bookChange(ChangeBook(data.team, data.room,  year*10000 + (month + 1)*100 + dayOfMonth ,oldDate)).enqueue(object : Callback<Status>{
            override fun onFailure(call: Call<Status>, t: Throwable) {}

            override fun onResponse(call: Call<Status>, response: Response<Status>) {
                if(response.isSuccessful){
                    Toast.makeText(context, "성공적으로 변경되었습니다", Toast.LENGTH_LONG).show()
                }
            }

        })
    }
}