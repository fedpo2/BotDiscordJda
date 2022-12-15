package Libreria;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class EventData {
    public  Guild gd;
    public  Message msg;
    public  String txt;
    public String[] arr;
    public String cmd;
    public VoiceChannel vc;
    public TextChannel textChannel;
    public GuildVoiceState selfVoiceState;
    public GuildVoiceState memberVoiceState;
    public Member self;
    public Member member;

    public AudioManager am;
    public boolean hasValidPrefix;
    public EventData(MessageReceivedEvent event){

        this.gd = event.getGuild();
        this.txt = event.getMessage().getContentRaw();
        checkForValidPrefix("!");
        this.cmd = arr[0];

        try{
            this.am  = gd.getAudioManager();
            this.vc = event.getMember().getVoiceState().getChannel().asVoiceChannel();
        }catch (Exception e){}

        this.textChannel = event.getGuildChannel().asTextChannel();
        this.self = gd.getSelfMember();
        this.member = event.getMember();
        this.memberVoiceState = member.getVoiceState();
        this.selfVoiceState = self.getVoiceState();
    }

    private void checkForValidPrefix(String prefix){
        if (txt.startsWith(prefix)){
            arr = txt.split(" ");
            cmd = arr[0];
            cmd.replaceAll(prefix, "");
            System.out.println("prefix = " + cmd);
            hasValidPrefix = true;
        }
    }
    public String[] getArgs() {
        return this.arr;
    }
}
