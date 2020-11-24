package com.example.androidclient.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidclient.R;
import com.example.androidclient.RetrofitClient;
import com.example.androidclient.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editID, editPW;
    Button btnLogin;
    TextView failText;
    String userID, userPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editID = findViewById(R.id.editID);
        editPW = findViewById(R.id.editPW);
        btnLogin = findViewById(R.id.btnLogin);
        failText = findViewById(R.id.failText);
        failText.setVisibility(View.INVISIBLE);


        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        userID = editID.getText().toString();
        userPW = editPW.getText().toString();

        if(userID.equals("") || userPW.equals("")){     //비어있으면
            failText.setText("아이디나 비밀번호는 비워둘 수 없습니다.");
            failText.setVisibility(View.VISIBLE);
            Log.d("TAG","아이디나 비밀번호는 비워둘 수 없습니다.");
        }

        Log.d("TAG", "UserID: "+ userID+" UserPW: "+userPW);

        RetrofitClient retrofitClient = new RetrofitClient();

        Call<User> call = retrofitClient.apiService.getretrofitquery(new User(userID, userPW));

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Log.d("TAG", "성공 "+ response.toString());
                    failText.setVisibility(View.INVISIBLE);
                    // main으로 넘기기

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                failText.setText("아이디나 비밀번호가 일치하지 않습니다.");
                failText.setVisibility(View.VISIBLE);
                Log.d("TAG","아이디나 비밀번호가 일치하지 않습니다.");
            }
        });
    }
}