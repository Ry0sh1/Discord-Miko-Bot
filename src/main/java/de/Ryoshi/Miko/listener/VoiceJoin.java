package de.Ryoshi.Miko.listener;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceJoin extends ListenerAdapter {

    public String joinToCreateChannel = "1014874197983506532";
    public String category = "1014546315138584657";

    public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {

        if (event.getChannelJoined().getId().equals(joinToCreateChannel)){

            String newChannelName = "Random";

            if (event.getMember().getNickname() != null){

                newChannelName = event.getMember().getNickname();

            }else {

                newChannelName = event.getMember().getUser().getName();

            }

            event.getGuild().moveVoiceMember(event.getMember(), event.getGuild().createVoiceChannel(newChannelName, event.getGuild().getCategoryById(category)).complete()).queue();

        }

    }

}
