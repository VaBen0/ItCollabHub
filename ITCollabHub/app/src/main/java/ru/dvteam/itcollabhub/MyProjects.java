package ru.dvteam.itcollabhub;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MyProjects extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_projects, container, false);

        LinearLayout main = v.findViewById(R.id.main_layout);
        ActivityProject activityProject = (ActivityProject) getActivity();
        assert activityProject != null;
        String mail = activityProject.getMail();
        //Toast.makeText(v.getContext(), mail, Toast.LENGTH_SHORT).show();
        PostDatas post = new PostDatas();
        post.postDataGetUserProjects("GetUserProject", mail, new CallBackInt() {
            @Override
            public void invoke(String info) {
                String[] inf = info.split(";");

                if(!inf[0].equals("Нет1проектов564")) {
                    main.removeAllViews();
                    String[] names = inf[0].split(",");
                    String[] photo = inf[1].split(",");
                    String[] id = inf[2].split(",");
                    String[] userName = inf[4].split(",");
                    String[] userImg = inf[5].split(",");
                    String[] userScore = inf[6].split(",");
                    String[] percents = inf[7].split(",");;

                    for (int i = 0; i < names.length; i++) {
                        View custom = inflater.inflate(R.layout.project_window, null);
                        TextView nameu = (TextView) custom.findViewById(R.id.textView3);
                        ImageView loadImage = (ImageView) custom.findViewById(R.id.loadImg);
                        ImageView user = (ImageView) custom.findViewById(R.id.prLogo);
                        ImageView userCircle = (ImageView) custom.findViewById(R.id.user_circle);
                        TextView nameOfUser = (TextView) custom.findViewById(R.id.textView13);
                        TextView percent = custom.findViewById(R.id.textView16);
                        ProgressBar lvl = custom.findViewById(R.id.lvl);
                        lvl.setProgress(Integer.parseInt(percents[i]));
                        percent.setText(percents[i] + ".0%");

                        nameOfUser.setText(userName[i]);

                        Glide
                                .with(MyProjects.this)
                                .load(userImg[i])
                                .into(user);


                        if(Integer.parseInt(userScore[i]) < 100){
                            userCircle.setBackgroundResource(R.drawable.circle_blue2);
                        }
                        else if(Integer.parseInt(userScore[i]) < 300){
                            userCircle.setBackgroundResource(R.drawable.circle_green2);
                        }
                        else if(Integer.parseInt(userScore[i]) < 1000){
                            userCircle.setBackgroundResource(R.drawable.circle_brown2);
                        }
                        else if(Integer.parseInt(userScore[i]) < 2500){
                            userCircle.setBackgroundResource(R.drawable.circle_light_gray2);
                        }
                        else if(Integer.parseInt(userScore[i]) < 7000){
                            userCircle.setBackgroundResource(R.drawable.circle_ohra2);
                        }
                        else if(Integer.parseInt(userScore[i]) < 17000){
                            userCircle.setBackgroundResource(R.drawable.circle_red2);
                        }
                        else if(Integer.parseInt(userScore[i]) < 30000){
                            userCircle.setBackgroundResource(R.drawable.circle_orange2);
                        }
                        else if(Integer.parseInt(userScore[i]) < 50000){
                            userCircle.setBackgroundResource(R.drawable.circle_violete2);
                        }
                        else{
                            userCircle.setBackgroundResource(R.drawable.circle_blue_green2);
                        }

                        Glide
                                .with(MyProjects.this)
                                .load(photo[i])
                                .into(loadImage);
                        nameu.setText(names[i]);

                        int finalI = i;
                        loadImage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activityProject.changeActivity(id[finalI]);
                            }
                        });
                        nameu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                activityProject.changeActivity(id[finalI]);
                            }
                        });
                        main.addView(custom);
                    }
                    View empty = inflater.inflate(R.layout.emty_obj, null);
                    main.addView(empty);
                }
                else{
                    Toast.makeText(v.getContext(), "Результаты не найдены", Toast.LENGTH_SHORT).show();;
                }
            }
        });
        /*for (int i = 0; i < 5; i++) {
            View custom = inflater.inflate(R.layout.project_window, null);

            main.addView(custom);
        }
        View empty = inflater.inflate(R.layout.emty_obj, null);
        main.addView(empty);

        Toast.makeText(v.getContext(), "lol", Toast.LENGTH_SHORT).show();*/

        return v;
    }
}