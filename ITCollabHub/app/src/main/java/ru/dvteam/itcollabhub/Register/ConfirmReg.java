package ru.dvteam.itcollabhub;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class ConfirmReg extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null) {
            String name = arguments.getString("name");
            String mail = arguments.getString("mail");
            String pass = arguments.getString("pass");


            TextView enterBut = findViewById(R.id.enterBut);

            enterBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ConfirmReg.this, LogIn.class);
                    startActivity(intent);
                }
            });

            Button conf = findViewById(R.id.confirmBut);
            EditText User_code = findViewById(R.id.code);

            conf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (User_code.getText().toString().equals("")) {
                        User_code.setHint("Введите ваш логин");
                    } else {
                        postData(mail, User_code.getText().toString(), pass, name);
                    }
                }
            });
        }
    }

    public void postData(String mail, String code, String pass, String name){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.confirm("CheckerCode", mail, code);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                if(response.body().getReturn().equals("Проверка почты прошла успешно")) {
                    change(mail, code, pass, name, response.body().getReturn());
                }
                else{
                    Toast.makeText(ConfirmReg.this, response.body().getReturn(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(ConfirmReg.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void postData2(String mail, String code, String pass, String name){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.regEnd("RegNewUser", mail, pass, name);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                if(response.body().getReturn().equals("Успешная регистрация")) {
                    change(mail, code, pass, name, response.body().getReturn());
                }
                else{
                    Toast.makeText(ConfirmReg.this, response.body().getReturn(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                Toast.makeText(ConfirmReg.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void change(String mail, String code, String pass, String name, String res){
        Toast toast = Toast.makeText(this, res, Toast.LENGTH_LONG);
        toast.show();

        EditText User_code = findViewById(R.id.code);
        User_code.setText(res);
        if(res.equals("Проверка почты прошла успешно")){
            postData2(mail, code, pass, name);
        }
        else if(res.equals("Успешная регистрация")){
            SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString("UserReg", "true");
            ed.putString("UserName", name);
            ed.apply();
            Intent intent = new Intent(ConfirmReg.this, MainActivity2.class);
            startActivity(intent);
        }
    }
}