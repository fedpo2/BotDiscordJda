package Commands;

import Libreria.EventData;
import Libreria.IComando;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class CmdHelp implements IComando {


    @Override
    public void execute(EventData event) {
        EmbedBuilder eb = new EmbedBuilder();

        /*
        arg 1 = title
        arg 2 = isUrl
         */

        eb.setTitle("!help");

        /*
        setColor
         */
        eb.setColor(new Color(105, 201, 225, 255));

        eb.setDescription("Command all public commands of my bot");

        eb.addField("!play | !p 'song' ", "Plays a youtube song  //still wip cant manually skip a song", false);
        eb.addField("!skip | !s", "Skips np song", false);
        eb.addField("!leave | !l","leaves voiceChannel and removes queue" ,false);
        eb.addField("!join | !j", "Joins a voice channel", false);
        eb.addField("!ping | !pi", "Returns a pong to test the conection of the bot", false);
        eb.addField("!copiame | !co 'mensaje'", "Escribe lo que le pidas", false);
        eb.addField("!help ! !h", "Shows this helpBox", false);


        event.textChannel.sendMessageEmbeds(eb.build())
                .queue();
    }

    @Override
    public void execute(EventData event, boolean skip) {

    }
}
