package ru.dvteam.itcollabhub;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class Friend extends AppCompatActivity {

    private int selectedColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.activity_friend);

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String mail = sPref.getString("UserMail", "");

        Bundle arguments = getIntent().getExtras();

        if(arguments!=null) {

            String id = arguments.getString("id");
            String name = arguments.getString("name");
            String urlImage = arguments.getString("image_url");
            String scoreStr = arguments.getString("score");
            String project = arguments.getString("project");
            int score = Integer.parseInt(scoreStr);

            String s = "Очки пользователя: " + score;
            TextView nameu = findViewById(R.id.nameu);
            ImageView loadedImage = findViewById(R.id.loadImg);
            ImageView userCircle = findViewById(R.id.userCircle);
            ImageView bguser = findViewById(R.id.bguser);
            TextView projects = findViewById(R.id.projects);
            TextView friends = findViewById(R.id.friends);
            TextView rating = findViewById(R.id.rating);
            TextView UserScore = findViewById(R.id.score);
            TextView project1 = findViewById(R.id.textView4);
            ImageView restartLine = findViewById(R.id.restart);
            View projects_lin = findViewById(R.id.linear_rating);
            Button delBut = findViewById(R.id.deleteButton);
            UserScore.setText(s);
            project1.setText(project);

            Glide
                    .with(Friend.this)
                    .load(urlImage)
                    .into(loadedImage);
            nameu.setText(name);

            if(score < 100){
                bguser.setBackgroundResource(R.drawable.gradient_blue);
                userCircle.setBackgroundResource(R.drawable.circle_blue);
                UserScore.setTextColor(Color.parseColor("#B20000FF"));
                selectedColor = Color.parseColor("#B20000FF");
                projects_lin.setBackgroundResource(R.drawable.blue_line);
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
            }
            else if(score < 300){
                bguser.setBackgroundResource(R.drawable.gradient_green);
                userCircle.setBackgroundResource(R.drawable.circle_green);
                UserScore.setTextColor(Color.parseColor("#B21AFF00"));
                selectedColor = Color.parseColor("#B21AFF00");
                projects_lin.setBackgroundResource(R.drawable.green_line);
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.green));
            }
            else if(score < 1000){
                bguser.setBackgroundResource(R.drawable.gradient_brown);
                userCircle.setBackgroundResource(R.drawable.circle_brown);
                UserScore.setTextColor(Color.parseColor("#FFCC7722"));
                selectedColor = Color.parseColor("#FFCC7722");
                projects_lin.setBackgroundResource(R.drawable.brown_line);
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.brown));
            }
            else if(score < 2500){
                bguser.setBackgroundResource(R.drawable.gradient_light_gray);
                userCircle.setBackgroundResource(R.drawable.circle_light_gray);
                UserScore.setTextColor(Color.parseColor("#B2B5B5B5"));
                selectedColor = Color.parseColor("#B2B5B5B5");
                projects_lin.setBackgroundResource(R.drawable.light_gray_line);
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.light_gray));
            }
            else if(score < 7000){
                bguser.setBackgroundResource(R.drawable.gradient_ohra);
                userCircle.setBackgroundResource(R.drawable.circle_ohra);
                UserScore.setTextColor(Color.parseColor("#FFE8AA0E"));
                selectedColor = Color.parseColor("#FFE8AA0E");
                projects_lin.setBackgroundResource(R.drawable.ohra_line);
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.ohra));
            }
            else if(score < 17000){
                bguser.setBackgroundResource(R.drawable.gradient_red);
                userCircle.setBackgroundResource(R.drawable.circle_red);
                UserScore.setTextColor(Color.parseColor("#FF0000"));
                selectedColor = Color.parseColor("#FF0000");
                projects_lin.setBackgroundResource(R.drawable.red_line);
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.red));
            }
            else if(score < 30000){
                bguser.setBackgroundResource(R.drawable.gradient_orange);
                userCircle.setBackgroundResource(R.drawable.circle_orange);
                UserScore.setTextColor(Color.parseColor("#FFCC7722"));
                selectedColor = Color.parseColor("#FFCC7722");
                projects_lin.setBackgroundResource(R.drawable.orange_line);
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.orange));
            }
            else if(score < 50000){
                bguser.setBackgroundResource(R.drawable.gradient_violete);
                userCircle.setBackgroundResource(R.drawable.circle_violete);
                UserScore.setTextColor(Color.parseColor("#4F0070"));
                selectedColor = Color.parseColor("#4F0070");
                projects_lin.setBackgroundResource(R.drawable.violete_line);
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.violete));
            }
            else{
                bguser.setBackgroundResource(R.drawable.gradient_blue_green);
                userCircle.setBackgroundResource(R.drawable.circle_blue_green);
                UserScore.setTextColor(Color.parseColor("#FF00C6A2"));
                selectedColor = Color.parseColor("#FF00C6A2");
                projects_lin.setBackgroundResource(R.drawable.blue_green_line);
                getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.main_green));
            }

            restartLine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(getIntent());
                    finish();
                }
            });
            delBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PostDatas post = new PostDatas();
                    post.postDataAddFriend("DeleteFriend", mail, id, new CallBackInt() {
                        @Override
                        public void invoke(String res) {
                            Toast.makeText(Friend.this, res, Toast.LENGTH_SHORT).show();
                            if(res.equals("Друг удалён")){
                                Intent intent = new Intent(Friend.this, Profile.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
            });
        }
    }
}