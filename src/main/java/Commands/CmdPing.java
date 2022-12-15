package Commands;

import Libreria.EventData;

public class CmdPing {
    public void execute(EventData ev){
        ev.textChannel.sendMessage("pong!").queue();
    }
}
