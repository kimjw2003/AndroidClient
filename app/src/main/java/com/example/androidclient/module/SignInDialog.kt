package com.example.androidclient.module

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.androidclient.data.response.UserResponse
import com.example.androidclient.room.DataBase
import com.example.androidclient.room.UserDataBase
import com.example.androidclient.view.MainActivity
import retrofit2.Response

class SignInDialog {
    internal fun connectionSuccess(
        response: Response<UserResponse>,
        context: Context,
        sweetAlertDialog: SweetAlertDialog
    ) {
        when (response.code()) {
            200 -> {

                DataBase.getInstance(context)!!.dao().insert(
                    UserDataBase(
                        id = 0,
                        user = response.body()!!.user,
                        school = response.body()!!.school,
                        grade = response.body()!!.grade,
                        classs = response.body()!!.numClass,
                        name = response.body()!!.name
                    )
                )

                sweetAlertDialog.dismiss()

                val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("로그인이 완료 되었습니다")
                    .setConfirmClickListener {
                        dialog.dismiss()
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(context, intent, null)
                        (context as Activity).finish()
                    }
                    .show()
            }
            500 -> {
                sweetAlertDialog.dismiss()

                val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("관리자에게 문의하시오")
                    .setConfirmClickListener {
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }
}