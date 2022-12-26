package Program;

import Commands.CmdCopy;
import Commands.CmdHelp;
import Commands.CmdPing;
import Commands.CmdPlay;
import Libreria.EventData;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main extends ListenerAdapter {
    CmdCopy copy = new CmdCopy();
    CmdPlay play = new CmdPlay();
    CmdPing ping = new CmdPing();
    CmdHelp help = new CmdHelp();

    public static void main(String[] args) {

        JDABuilder.create(args[0], GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_VOICE_STATES)
                .enableCache(CacheFlag.VOICE_STATE)
                //.addEventListeners(jdaCommands)
                .addEventListeners(new Main())
                .build();
    }

    //@Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        if (event.getAuthor().isBot()) {
            return;
        }
        EventData ed = new EventData(event);

        if (!ed.hasValidPrefix) {
            return;
        }

        for (String a : ed.arr) {
            System.out.printf(a + " ");
        }
        System.out.printf("\n");

        System.out.println("cmd = " + ed.cmd);
        switch (ed.cmd) {
            case "j": case "join": ed.am.openAudioConnection(ed.vc); break;
            case "l": case "leave": leaveChannel(ed); break;
            case "s": case "skip": play.execute(ed, true); break;
            case "p": case "play": play.execute(ed, false); break;
            case "pi": case "ping": ping.execute(ed); break;
            case "h": case "help": help.execute(ed); break;
            case "co" : case "copiame": copy.execute(ed);
            //case "np"

        }
    }

    private void leaveChannel(EventData event) {
        try {
            event.am.closeAudioConnection();
        } catch (Exception e) {
            System.err.println(e);
        }
        play = new CmdPlay();
    }
}