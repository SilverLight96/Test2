package com.example.test2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import kr.co.prnd.YouTubePlayerView;

import static android.provider.MediaStore.Video.Thumbnails.VIDEO_ID;


public class ContentsFragment extends Fragment {
    private YouTubePlayerView youTubePlayerView;
    View rootview;
    String VIDEO_ID="qMtyhDDmJ-U";
    YouTubePlayer.OnInitializedListener listener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_contents, container, false);
        return rootview;
    }

    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        YouTubePlayerView youTubePlayerView = rootview.findViewById(R.id.youtubeView);

    }
}





