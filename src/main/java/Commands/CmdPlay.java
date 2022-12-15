package Commands;

import Libreria.EventData;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.PlayerManager;
import java.net.URI;

import java.net.URISyntaxException;

public class CmdPlay {

    public void execute(EventData event) {
        if (!event.memberVoiceState.inAudioChannel()){
            event.textChannel.sendMessage("necesitas estar en un canal diferente para que este comando ande").queue();
            return;
        }

        if (!event.selfVoiceState.inAudioChannel()){
            final AudioManager audioManager = event.gd.getAudioManager();
            final AudioChannel memberChannel = (AudioChannel) event.memberVoiceState.getChannel();

            audioManager.openAudioConnection(memberChannel);

        }
        String link = String.join(" ", event.getArgs());

        if (!isUrl(link)){
            link = "ytsearch: "+ link + " audio";
        }

        PlayerManager.getINSTANCE().loadAndPlay(event.textChannel, link);
    }

    public boolean isUrl(String url){
        try {
            new URI(url);
            return true;
        }catch (URISyntaxException e){
            return false;
        }
    }
}
