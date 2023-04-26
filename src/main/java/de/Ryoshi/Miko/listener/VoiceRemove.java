package de.Ryoshi.Miko.listener;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceRemove extends ListenerAdapter {

    public String joinToCreateChannel = "1014874197983506532";

    public void onGuildVoiceLeave (GuildVoiceLeaveEvent event){

        if (event.getChannelLeft().getMembers().isEmpty() && !event.getChannelLeft().getId().equals(joinToCreateChannel)){

            event.getChannelLeft().delete().queue();

        }

    }

}
