package com.example.androidclient.module

import android.content.Context
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.androidclient.data.response.Status
import retrofit2.Response

class MakeTeamDialog {
    internal fun connectionSuccess(
        response: Response<Status>,
        context: Context,
        sweetAlertDialog: SweetAlertDialog
    ) {

    }
}