package com.example.androidclient.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.androidclient.R
import com.example.androidclient.data.request.User
import com.example.androidclient.data.request.UserRequest
import com.example.androidclient.data.response.Status
import com.example.androidclient.data.response.UserResponse
import com.example.androidclient.module.SignInDialog
import com.example.androidclient.retrofit.RetrofitClient
import com.example.androidclient.room.DataBase
import com.example.androidclient.room.UserDataBase
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    var editID: EditText? = null
    var editPW: EditText? = null
    var userID: String? = null
    var userPW: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        editID = findViewById(R.id.editID)
        editPW = findViewById(R.id.editPW)
        failText.visibility = View.INVISIBLE
        btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        userID = editID!!.text.toString()
        userPW = editPW!!.text.toString()
        if (userID == "" || userPW == "") {     //비어있으면
            failText!!.text = "아이디나 비밀번호는 비워둘 수 없습니다."
            failText!!.visibility = View.VISIBLE
            Log.d("TAG", "아이디나 비밀번호는 비워둘 수 없습니다.")
        }

        Log.d("TAG", "UserID: $userID UserPW: $userPW")
        val retrofitClient = RetrofitClient.getInstance()
        val call =
            retrofitClient.login(
                UserRequest(
                    userID!!,
                    userPW!!
                )
            )
        call.enqueue(object : Callback<Status> {
            override fun onResponse(
                call: Call<Status>,
                response: Response<Status>
            ) {
                if (response.isSuccessful) {
                    Log.d("TAG", "성공 $response")
                    failText!!.visibility = View.INVISIBLE
                    DataBase.getInstance(applicationContext)!!.dao().delete()
                    getUserInfo(userID!!)
                } else {
                    failText!!.text = "아이디나 비밀번호가 일치하지 않습니다."
                    failText!!.visibility = View.VISIBLE
                }
            }

            override fun onFailure(
                call: Call<Status>,
                t: Throwable
            ) {
                failText!!.text = "아이디나 비밀번호가 일치하지 않습니다."
                failText!!.visibility = View.VISIBLE
                Log.d("TAG", t.toString())
            }
        })
    }

    fun getUserInfo(id : String){

        val sweetAlertDialog =
            SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        sweetAlertDialog.progressHelper.barColor = Color.parseColor("#0DE930")
        sweetAlertDialog
            .setTitleText("로그인중")
            .setCancelable(false)
        sweetAlertDialog.show()

        val signInDialog = SignInDialog()

        RetrofitClient.getInstance().getUser(User(id)).enqueue(object : Callback<UserResponse>{
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {}

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                signInDialog.connectionSuccess(
                    response,
                    this@LoginActivity,
                    sweetAlertDialog
                )
            }

        })
    }
}