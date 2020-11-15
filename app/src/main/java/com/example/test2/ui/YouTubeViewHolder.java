package com.example.test2.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import com.example.test2.R;

public class YouTubeViewHolder extends RecyclerView.ViewHolder {
    YouTubePlayerView youTubePlayerView;

    public YouTubeViewHolder(@NonNull View itemView) {
        super(itemView);

        youTubePlayerView = itemView.findViewById(R.id.card_content_player_view);

    }
}
