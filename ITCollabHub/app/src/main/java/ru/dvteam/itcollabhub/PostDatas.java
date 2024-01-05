package ru.dvteam.itcollabhub;

import static androidx.core.content.ContextCompat.startActivity;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDatas {

    public void postDataLogIn(String req, String mail, String pass, CallBackInt1 result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.login(req, mail, pass);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn(), response.body().getName());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("Ошибка сервера", "");
            }
        });
    }
    public void postDataConfirm(String req, String mail, String code, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.confirm(req, mail, code);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("Ошибка сервера");
            }
        });
    }
    public void postDataRegUser(String req, String mail, String pass, String name, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.regEnd(req, mail, pass, name);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("Ошибка сервера");
            }
        });
    }
    public void postDataPostCodeMail(String req, String mail, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.postCodeMail(req, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("Ошибка сервера");
            }
        });
    }

    public void postDataCreateAccount(String req, String name, RequestBody requestFile, String mail, CallBackInt result){

        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "lol", requestFile);
        RequestBody requestName = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody requestMail = RequestBody.create(MediaType.parse("text/plain"), mail);
        RequestBody requestReq = RequestBody.create(MediaType.parse("text/plain"), req);

        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.uploadImage(fileToUpload, requestName, requestReq, requestMail);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("All bad");
            }
        });
    }

    public void postDataGetUserData(String mail, CallBackInt2 result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getUserInformation("GetUserInformation", mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getName(), response.body().getUrlImg(),
                        response.body().getTopScore(), response.body().getTopStatus(), response.body().getrFr());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("","", 0, "", "");
            }
        });
    }

    public void postDataCreateLog(String req, String mail, String name,String img, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.uploadLog(req, mail, name, img);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("Ошибка сервера");
            }
        });
    }

    public void postDataGetFriends(String req, String mail, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getUserFriends(req, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });
    }

    public void postDataAddFriend(String req, String mail, String id, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.addFriends(req, mail, id);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("Failure");
            }
        });
    }

    public void postDataGetFindFriend(String req, String name, String mail, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getFindFriends(req, name, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }

        });
    }

    public void postDataEditName(String req, String mail, String name, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.editName(req, name, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }

        });
    }

    public void postDataCreateProject(String req, String name, RequestBody requestFile, String mail, String purposes, String tasks,
                                      String description, String id, CallBackInt result){

        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "lol", requestFile);
        RequestBody requestName = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody requestMail = RequestBody.create(MediaType.parse("text/plain"), mail);
        RequestBody requestReq = RequestBody.create(MediaType.parse("text/plain"), req);
        RequestBody requestDes = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody requestPur = RequestBody.create(MediaType.parse("text/plain"), purposes);
        RequestBody requestTasks = RequestBody.create(MediaType.parse("text/plain"), tasks);
        RequestBody requestId = RequestBody.create(MediaType.parse("text/plain"), id);

        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.createProject(fileToUpload, requestName, requestReq, requestPur, requestMail, requestTasks, requestId,requestDes);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("All bad");
            }
        });
    }

    public void postDataGetUserProjects(String req, String mail, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getUserProjects(req, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }
    public void postDataGetUserProjects1(String req, String mail, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getUserProjects(req, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }
    public void postDataCreateProjectWithoutImage(String req, String name, String mail, String purposes, String tasks,
                                        String description, String id, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.createProjectWithoutImage(name, req, purposes, mail, tasks, id, description);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataGetProjectInformation(String req, String id, String mail, CallBackInt4 result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getProjectInformation(req, id, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getName(), response.body().getUrlImg(), response.body().getDescription(),
                        response.body().getIsend(), response.body().getPurposes(), response.body().getProblems(), response.body().getPeoples(),
                        response.body().getTime(), response.body().getTime1(), response.body().getTg(), response.body().getVk(),
                        response.body().getWebs(),response.body().getPurposesids(), response.body().getProblemsids(), response.body().getIsl());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataSendLink(String req, String mail, String link){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.sendUserLink(req, mail, link);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {

            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataGetLinks(String req, String mail, CallBackInt5 result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getUserLinks(req, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                result.invoke(response.body().getTgLink(), response.body().getVkLink(), response.body().getWebLink());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataGetFriendLinks(String req, String id, CallBackInt5 result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getFriendLinks(req, id);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                result.invoke(response.body().getTgLink(), response.body().getVkLink(), response.body().getWebLink());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataGetProjectReq(String req, String mail, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getProjectRequests(req, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataAnswerProject(String req, String mail, String id,CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.answerOnProjectReq(req, mail, id);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataGetPurpose(String req, String id, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getPurposes(req, id);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataGetProblems(String req, String id, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.getProblems(req, id);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataCreatePurpose(String req, String name, RequestBody requestFile, String description,
                                      String mail, String id, CallBackInt result){

        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "lol", requestFile);
        RequestBody requestName = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody requestReq = RequestBody.create(MediaType.parse("text/plain"), req);
        RequestBody requestDes = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody requestId = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody requestMail = RequestBody.create(MediaType.parse("text/plain"), mail);

        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.createPurpose(fileToUpload, requestName, requestReq, requestId, requestDes, requestMail);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("All bad");
            }
        });
    }

    public void postDataCreatePurposeWithoutImage(String req, String name, String description, String id, String mail, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.createPurposeWithoutImage(name, req, id, description, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void postDataCreateProblem(String req, String name, RequestBody requestFile, String description, String id, String mail, CallBackInt result){

        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", "lol", requestFile);
        RequestBody requestName = RequestBody.create(MediaType.parse("text/plain"), name);
        RequestBody requestReq = RequestBody.create(MediaType.parse("text/plain"), req);
        RequestBody requestDes = RequestBody.create(MediaType.parse("text/plain"), description);
        RequestBody requestId = RequestBody.create(MediaType.parse("text/plain"), id);
        RequestBody requestMail = RequestBody.create(MediaType.parse("text/plain"), mail);

        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.createProblem(fileToUpload, requestName, requestReq, requestId, requestDes, requestMail);
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                result.invoke("All bad");
            }
        });
    }

    public void postDataCreateProblemWithoutImage(String req, String name, String description, String id, String mail, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.createProblemWithoutImage(name, req, id, description, mail);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void setPurposeIsEnd(String req, String id, String pid, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.setPurposesIsEnd(req, id, pid);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }

    public void setProblemIsEnd(String req, String id, String pid, CallBackInt result){
        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Model> call = methods.setProblemIsEnd(req, id, pid);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, retrofit2.Response<Model> response) {
                assert response.body() != null;
                result.invoke(response.body().getReturn());
            }

            @Override
            public void onFailure(Call<Model> call, Throwable t) {
                //result.invoke("lol");
            }
        });
    }


}
