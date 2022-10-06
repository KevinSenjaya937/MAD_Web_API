package au.edu.curtin.assignment2a;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import au.edu.curtin.assignment2a.controllers.UserController;
import au.edu.curtin.assignment2a.fragments.UserFragment;
import au.edu.curtin.userinfo.User;

public class BackGroundTaskHandler implements Runnable{

    Activity uiActivity;
    String searchKey;
    ProgressBar progressBar;
    ImageView imageView;
    FragmentManager fm;
    UserFragment userFragment;
    UserController userController;

    public BackGroundTaskHandler(Activity uiActivity, UserFragment userFragment, FragmentManager fm, UserController userController) {
        this.uiActivity = uiActivity;
//        this.searchKey = searchValue;
        this.progressBar = progressBar;
        this.imageView = imageView;
        this.fm = fm;
        this.userFragment = userFragment;
        this.userController = userController;
    }

    @Override
    public void run() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        SearchTask searchTask = new SearchTask(uiActivity);
        Future<String> searchResponsePlaceholder = executorService.submit(searchTask);
        String searchResult = waitingForSearch(searchResponsePlaceholder);

        if(searchResult!=null){
            GetPostsTask postsRetrievalTask = new GetPostsTask(uiActivity);
            Future<String> postsResponsePlaceHolder = executorService.submit(postsRetrievalTask);
            String postsResult = waitingForPosts(postsResponsePlaceHolder);

            Gson gson = new Gson();
            Type type = new TypeToken<List<User>>() {}.getType();
            List<User> userList = gson.fromJson(searchResult, type);

            for (User user : userList) {
                userController.addUser(user);
                Log.d("KEVIN", user.getUsername());
            }

            Type typePost = new TypeToken<List<Post>>() {}.getType();
            List<Post> postList = gson.fromJson(postsResult, typePost);

            for (Post post : postList) {
                userController.addPost(post);
                Log.d("KEVIN", "Post Added");
            }

            uiActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fm.beginTransaction().add(R.id.userListFragment, userFragment).commit();
                }
            });
        }
        else{
            showError(4,"Search");
        }
        executorService.shutdown();
    }

    public String waitingForSearch(Future<String> searchResponsePlaceholder){
        uiActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                progressBar.setVisibility(View.VISIBLE);
            }
        });
        showToast("Search Starts");
        String searchResponseData =null;
        try {
            searchResponseData = searchResponsePlaceholder.get(6000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
            showError(1, "Search");
        } catch (InterruptedException e) {
            e.printStackTrace();
            showError(2, "Search");
        } catch (TimeoutException e) {
            e.printStackTrace();
            showError(3, "Search");
        }
        showToast("Search Ends");
        uiActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        return  searchResponseData;
    }

    public String waitingForPosts(Future<String> postsResponsePlaceholder){
        uiActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                progressBar.setVisibility(View.VISIBLE);
            }
        });
        showToast("Posts Retrieval Starts");
        String postsResponseData =null;
        try {
            postsResponseData = postsResponsePlaceholder.get(6000, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            e.printStackTrace();
            showError(1, "Posts Retrieval");
        } catch (InterruptedException e) {
            e.printStackTrace();
            showError(2, "Posts Retrieval");
        } catch (TimeoutException e) {
            e.printStackTrace();
            showError(3, "Posts Retrieval");
        }
        showToast("Posts Retrieval Ends");
        uiActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        return  postsResponseData;
    }

    public void showError(int code, String taskName){
        if(code ==1){
            showToast(taskName+ " Task Execution Exception");
        }
        else if(code ==2){
            showToast(taskName+" Task Interrupted Exception");
        }
        else if(code ==3){
            showToast(taskName+" Task Timeout Exception");
        }
        else{
            showToast(taskName+" Task could not be performed. Restart!!");
        }
    }

    public void showToast(String text){
        uiActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(uiActivity,text,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
