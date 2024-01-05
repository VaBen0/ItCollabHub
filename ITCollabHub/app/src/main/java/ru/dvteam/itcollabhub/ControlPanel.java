package ru.dvteam.itcollabhub;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.service.controls.Control;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ru.dvteam.itcollabhub.databinding.ActivityControlPanelBinding;

public class ControlPanel extends AppCompatActivity {

    ActivityControlPanelBinding binding;
    String title, urlPhoto;
    String mail, islead;
    String purposesidss, problemss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        mail = sPref.getString("UserMail", "");
        int score = sPref.getInt("UserScore", 0);

        binding = ActivityControlPanelBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        if(score < 100){
            binding.bguser.setBackgroundResource(R.drawable.gradient_blue);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.blue));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.blue));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.blue));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.blue));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.blue));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_bg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_bg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_bg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_bg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 300){
            binding.bguser.setBackgroundResource(R.drawable.gradient_green);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.green));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.green));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.green));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.green));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.green));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_gg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_gg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_gg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_gg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 1000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_brown);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.brown));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.brown));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.brown));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.brown));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.brown));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_brg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_brg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_brg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_brg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 2500){
            binding.bguser.setBackgroundResource(R.drawable.gradient_light_gray);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.light_gray));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.light_gray));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.light_gray));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.light_gray));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.light_gray));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_lgg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_lgg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_lgg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_lgg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 7000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_ohra);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.ohra));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.ohra));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.ohra));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.ohra));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.ohra));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_ohg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_ohg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_ohg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_ohg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 17000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_red);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.red));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.red));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.red));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.red));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.red));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_rg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_rg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_rg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_rg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 30000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_orange);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.orange));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.orange));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.orange));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.orange));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.orange));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_og);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_og);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_og);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_og);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
        }
        else if(score < 50000){
            binding.bguser.setBackgroundResource(R.drawable.gradient_violete);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.violete));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.violete));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.violete));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.violete));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.violete));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_vg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_vg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_vg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_vg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
        }
        else{
            binding.bguser.setBackgroundResource(R.drawable.gradient_blue_green);
            getWindow().setStatusBarColor(ContextCompat.getColor(ControlPanel.this,R.color.main_green));
            binding.projectFiles.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.main_green));
            binding.advertisments.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.main_green));
            binding.editProject.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.main_green));
            binding.projectChat.setBackgroundTintList(ContextCompat.getColorStateList(ControlPanel.this, R.color.main_green));
            Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_mgg);
            binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_mgg);
            binding.purpProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_mgg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
            binding.tasksProgress.setBackgroundResource(R.drawable.custom_progress_bar_mgg);
            binding.tasksProgress.setProgressDrawable(progressDrawable);
        }

        Bundle arguments = getIntent().getExtras();

        assert arguments != null;
        String id = arguments.getString("projectId");

        PostDatas postDatas = new PostDatas();
        postDatas.postDataGetProjectInformation("GetProjectMainInformation", id, mail, new CallBackInt4() {
            @Override
            public void invoke(String name, String photoUrl, String descript, int isend, String purposes,
                               String problems, String peoples, String time, String time1, String tg, String vk, String webs, String purposesids,
                               String problemsids, String isl) {
                title = name;
                urlPhoto = photoUrl;
                binding.nameProject.setText(name);
                Glide
                        .with(ControlPanel.this)
                        .load(photoUrl)
                        .into(binding.prLogo);
                purposesidss = purposesids;
                islead = isl;
                problemss = problemsids;

                if(((int)(parse(purposes) * 100) == 100)){
                    Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_greeen);
                    binding.purpProgress.setBackgroundResource(R.drawable.custom_progress_bar_greeen);
                    binding.purpProgress.setProgressDrawable(progressDrawable);
                }
                if(((int)(parse(problems) * 100) == 100)){
                    Drawable progressDrawable = getResources().getDrawable(R.drawable.custom_progress_bar_greeen);
                    binding.problemsProgress.setBackgroundResource(R.drawable.custom_progress_bar_greeen);
                    binding.problemsProgress.setProgressDrawable(progressDrawable);
                }

                if(isl.equals("0")){
                    binding.main.removeView(binding.editProjectConst);
                }

                ObjectAnimator animation = ObjectAnimator.ofInt(binding.purpProgress, "progress", 0, (int)(parse(purposes) * 100));
                animation.setStartDelay(300);
                animation.setDuration(1000);
                animation.setAutoCancel(true);
                animation.setInterpolator(new DecelerateInterpolator());
                animation.start();

                ObjectAnimator anim = ObjectAnimator.ofInt(binding.problemsProgress, "progress", 0, (int)(parse(problems) * 100));
                anim.setStartDelay(300);
                anim.setDuration(1000);
                anim.setAutoCancel(true);
                anim.setInterpolator(new DecelerateInterpolator());
                anim.start();
            }
        });

        Date date = new Date();
        LocalTime current = null;
        long millis = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            current = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
            millis = ChronoUnit.MILLIS.between(current, LocalTime.MAX);
        }
        else {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 1);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            millis = (c.getTimeInMillis() - System.currentTimeMillis());
        }

        //timeInMilliseconds = 86400000 - timeInMilliseconds;
        new CountDownTimer(millis, 1000) {

            public void onTick(long millisUntilFinished) {
                long allSeconds = millisUntilFinished / 1000;
                long seconds = allSeconds % 60;
                long minutes = (allSeconds / 60) % 60;
                long hours = (allSeconds / 3600) % 24;
                binding.timer.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
                // logic to set the EditText could go here
            }

            public void onFinish() {
                binding.timer.setText("А всё)");
            }

        }.start();

        binding.purpProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(islead.equals("0")){
                    Intent intent = new Intent(ControlPanel.this, PurposeParticipiant.class);
                    intent.putExtra("projectTitle", title);
                    intent.putExtra("projectUrlPhoto", urlPhoto);
                    intent.putExtra("projectId", purposesidss);
                    intent.putExtra("projectId1", id);
                    intent.putExtra("leader", islead);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(ControlPanel.this, Purpose.class);
                    intent.putExtra("projectTitle", title);
                    intent.putExtra("projectUrlPhoto", urlPhoto);
                    intent.putExtra("projectId", purposesidss);
                    intent.putExtra("projectId1", id);
                    intent.putExtra("leader", islead);
                    startActivity(intent);
                }
            }
        });

        binding.problemsProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(islead.equals("0")){
                    Intent intent = new Intent(ControlPanel.this, ProblemsParticip.class);
                    intent.putExtra("projectTitle", title);
                    intent.putExtra("projectUrlPhoto", urlPhoto);
                    intent.putExtra("projectId1", id);
                    intent.putExtra("projectId", problemss);
                    intent.putExtra("leader", islead);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(ControlPanel.this, Problems.class);
                    intent.putExtra("projectTitle", title);
                    intent.putExtra("projectUrlPhoto", urlPhoto);
                    intent.putExtra("projectId1", id);
                    intent.putExtra("projectId", problemss);
                    intent.putExtra("leader", islead);
                    startActivity(intent);
                }
            }
        });

        /*for (int i = 0; i < 5; i++) {
            View custom = getLayoutInflater().inflate(R.layout.reminder, null);

            binding.reminderPlace.addView(custom);
        }*/
    }
    double parse(String ratio) {
        if (ratio.contains("/")) {
            String[] rat = ratio.split("/");
            return Double.parseDouble(rat[0]) / Double.parseDouble(rat[1]);
        } else {
            return Double.parseDouble(ratio);
        }
    }
}