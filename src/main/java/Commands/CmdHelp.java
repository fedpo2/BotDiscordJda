package Commands;

import Libreria.EventData;
import Libreria.IComando;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class CmdHelp implements IComando {


    @Override
    public String getName(String name) {
        return IComando.super.getName(name);
    }

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

        eb.addField("!play 'song'", "Plays a youtube song  //still wip cant manually skip a song", false);
        eb.addField("!ping", "Returns a pong to test the conection of the bot", false);


        event.textChannel.sendMessageEmbeds(eb.build())
                .queue();
    }

    @Override
    public void execute(EventData event, boolean skip) {

    }
}
