package Commands;

import Libreria.EventData;
import Libreria.IComando;

import java.util.ArrayList;
import java.util.Arrays;

public class CmdCopy implements IComando {

    @Override
    public void execute(EventData event) {

        event.msg.delete().queue();

        event.arr[0] = "";
        var liststr = new ArrayList<>(Arrays.stream(event.arr).toList());
        liststr.remove(0);

        var str = liststr.toString()
                .replace(",", "")
                .replace("[", "")
                .replace("]", "");

        event.textChannel.sendMessage(str).queue();
    }

    @Override
    public void execute(EventData event, boolean skip) {

    }
}
