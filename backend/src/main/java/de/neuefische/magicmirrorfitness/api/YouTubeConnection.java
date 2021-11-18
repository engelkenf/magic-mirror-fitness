package de.neuefische.magicmirrorfitness.api;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;

public class YouTubeConnection {

    String apiKey = "AIzaSyCg5ldOrjwwWPXDpLUZB3hpmdv-jaelqC0";
    Video video = new Video();

    public Video getVideoInfoById(String youTubeId) {
        try {
            YouTube youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("APP_ID").build();
            YouTube.Videos.List listVideosRequest = youtube.videos().list("statistics,snippet,contentDetails");
            System.out.println("youTubeIdm  in der connection class: "+youTubeId);
            listVideosRequest.setId(youTubeId); // add list of video IDs here
            listVideosRequest.setKey(apiKey);
            VideoListResponse listResponse = listVideosRequest.execute();

            video = listResponse.getItems().get(0);



        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return video;

    }

}