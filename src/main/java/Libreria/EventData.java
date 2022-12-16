package Libreria;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class EventData {
    public  Guild gd;
    public  String txt;
    public String[] arr;
    public String cmd;
    public VoiceChannel vc;
    public TextChannel textChannel;
    public GuildVoiceState selfVoiceState;
    public GuildVoiceState memberVoiceState;
    public Member self;
    public Member member;
    public Channel channel;
    public AudioManager am;
    public boolean hasValidPrefix;
    public EventData(MessageReceivedEvent event){
        this.channel = event.getChannel();
        this.gd = event.getGuild();
        this.txt = event.getMessage().getContentRaw().toLowerCase();
        checkForValidPrefix("!");

        try{
            this.am  = gd.getAudioManager();
            this.vc = event.getMember().getVoiceState().getChannel().asVoiceChannel();
        }catch (Exception e){
            System.err.println(e);
        }

        this.textChannel = event.getGuildChannel().asTextChannel();
        this.self = gd.getSelfMember();
        this.member = event.getMember();
        this.memberVoiceState = member.getVoiceState();
        this.selfVoiceState = self.getVoiceState();
    }

    private void checkForValidPrefix(String prefix){
        if (txt.startsWith(prefix)){
            arr = txt.split(" ");
            this.cmd = arr[0].replace(prefix,"");
            System.out.println("cmdconstruct = " + cmd);
            hasValidPrefix = true;
        }
    }
    public String[] getArgs() {
        return this.arr;
    }
}
