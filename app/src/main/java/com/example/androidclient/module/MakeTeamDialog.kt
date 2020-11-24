package com.example.androidclient.module

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.androidclient.data.response.Status
import com.example.androidclient.room.DataBase
import com.example.androidclient.room.UserDataBase
import com.example.androidclient.view.MainActivity
import retrofit2.Response

class MakeTeamDialog {
    internal fun connectionSuccess(
        response: Response<Status>,
        context: Context,
        sweetAlertDialog: SweetAlertDialog
    ) {
        when (response.code()) {
            200 -> {

                sweetAlertDialog.dismiss()

                val dialog = SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)

                dialog.setCancelable(false)

                dialog.setTitleText("팀 생성이 완료 되었습니다")
                    .setConfirmClickListener {
                        dialog.dismiss()
                        context.startActivity(Intent(context, MainActivity::class.java))
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