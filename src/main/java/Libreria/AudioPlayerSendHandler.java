package Libreria;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;
import net.dv8tion.jda.api.audio.AudioSendHandler;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class AudioPlayerSendHandler implements AudioSendHandler {
    private AudioPlayer pl;
    private ByteBuffer buffer;
    private MutableAudioFrame frame;

    public AudioPlayerSendHandler(AudioPlayer pl) {
        this.pl = pl;
        this.buffer = ByteBuffer.allocate(1024);
        this.frame = new MutableAudioFrame();
        this.frame.setBuffer(buffer);
    }

    @Override
    public boolean isOpus() {
        return true;
    }

    @Override
    public boolean canProvide() {
        return this.pl.provide(this.frame);
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        final Buffer tmp  = ((Buffer)this.buffer).flip();
        return (ByteBuffer) tmp;
    }
}
