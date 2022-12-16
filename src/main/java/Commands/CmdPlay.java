package Commands;

import Libreria.EventData;
import Libreria.IComando;
import net.dv8tion.jda.api.entities.channel.middleman.AudioChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.PlayerManager;
import java.net.URI;

import java.net.URISyntaxException;

public class CmdPlay implements IComando {

    @Override
    public void execute(EventData event, boolean skip) {
        if (!event.memberVoiceState.inAudioChannel()){
            event.textChannel.sendMessage("necesitas estar en un canal diferente para que este comando ande")
                    .queue();
            return;
        }

        if (!event.selfVoiceState.inAudioChannel()){
            final AudioManager audioManager = event.gd.getAudioManager();
            final AudioChannel memberChannel = (AudioChannel) event.memberVoiceState.getChannel();

            audioManager.openAudioConnection(memberChannel);

        }

        if (skip){
            PlayerManager.getINSTANCE().skipSong(event.textChannel);
            return;
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


    @Override
    public String getName(String name) {
        return IComando.super.getName(name);
    }

    @Override
    public void execute(EventData event) {

    }

}
