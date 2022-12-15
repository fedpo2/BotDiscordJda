package org.example;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class GuildMusicManager {
    private AudioPlayer pl;
    final TrackManager manager;
    private AudioPlayerSendHandler sendHandler;

    public GuildMusicManager(AudioPlayerManager manager2) {
        this.pl = manager2.createPlayer();
        this.manager = new TrackManager(this.pl);
        this.pl.addListener(this.manager);
        this.sendHandler = new AudioPlayerSendHandler(this.pl);
    }

    public AudioPlayerSendHandler getSendHandler(){
        return this.sendHandler;
    }
}
