package org.example;

import Commands.CmdPing;
import Commands.CmdPlay;
import Libreria.EventData;
import ca.tristan.jdacommands.ExecuteArgs;
import ca.tristan.jdacommands.JDACommands;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class Main extends ListenerAdapter {
    token tk = new token();
    CmdPlay play = new CmdPlay();
    CmdPing ping = new CmdPing();

    public static void main(String[] args) {

        JDABuilder.create(token.bot, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT,
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

        if(!ed.hasValidPrefix){
            return;
        }

        for (String a : ed.arr){
            System.out.printf(a + " ");
        }
        System.out.printf("\n");

        System.out.println("cmd = " +ed.cmd);
        switch (ed.cmd){

            case "!join":
                /*try {
                    vc = guild.getVoiceChannelsByName("General", true).get(0);
                }catch (Exception e ){
                    System.out.println(e);
                    vc = null;
                }*/
                //am.setSendingHandler(new Send);
                ed.am.openAudioConnection(ed.vc);
                break;

            case "!leave":
                try{
                    ed.am.closeAudioConnection();
                }catch (Exception e){
                    System.err.println(e);
                }
                break;

            case "!play":
                play.execute(ed);
                break;

            case "!ping":
                ping.execute(ed);
                System.out.println("event = " + event);
                break;

        }
    }
}