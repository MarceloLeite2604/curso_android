package com.example.sala01.aula9.util;

import com.example.sala01.aula9.MainActivity;
import com.example.sala01.aula9.RepositoryInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by marcelo on 29/09/16.
 */

public class GitHubUtil {

    private static final String USERNAME_SUBSTITUTION = "&user.";
    private static final String USER_REPOSITORIES_INFO_URL = "https://api.github.com/users/" + USERNAME_SUBSTITUTION + "/repos";

    public static ArrayList<RepositoryInfo> getGitHubRepositoriesInfoFromUser(String username) throws GitHubUtilException {

        ArrayList<RepositoryInfo> repositoryInfoArrayList = null;
        try {
            URL userRepositoriesInfoUrl = new URL(USER_REPOSITORIES_INFO_URL.replace(USERNAME_SUBSTITUTION, username));
            JSONArray repositoriesInfoJsonArray = getUserRepositoresInfo(userRepositoriesInfoUrl);
            repositoryInfoArrayList = extractGitHubRepositoriesInfos(repositoriesInfoJsonArray);
        } catch (Exception exception) {
            throw new GitHubUtilException("Error getting repositories information from user \"" + username + "\".", exception);
        }

        return repositoryInfoArrayList;
    }

    private static JSONArray getUserRepositoresInfo(URL url) throws InterruptedException, ExecutionException, TimeoutException {

        GetJsonArrayFromUrlAsyncTask getJsonArrayFromUrlAsyncTask = new GetJsonArrayFromUrlAsyncTask();
        getJsonArrayFromUrlAsyncTask.execute(url);
        JSONArray userRepositoriesInfoJsonArray = getJsonArrayFromUrlAsyncTask.get(5000, TimeUnit.MILLISECONDS);

        LogUtil.d(MainActivity.LOG_TAG, "{getUserRepositoresInfo, 95} Result: " + userRepositoriesInfoJsonArray);
        return userRepositoriesInfoJsonArray;
    }

    private static ArrayList<RepositoryInfo> extractGitHubRepositoriesInfos(JSONArray jsonArray) throws JSONException, MalformedURLException {

        ArrayList<RepositoryInfo> repositoryInfoArrayList = new ArrayList<>();
        JSONObject jsonObject;

        for (int counter = 0; counter < jsonArray.length(); counter++) {
            jsonObject = jsonArray.getJSONObject(counter);
            String repositoryName = jsonObject.getString("name");
            String avatarUrlAddress = jsonObject.getJSONObject("owner").getString("avatar_url");
            repositoryInfoArrayList.add(new RepositoryInfo(repositoryName, new URL(avatarUrlAddress)));

        }
        return repositoryInfoArrayList;
    }

}
