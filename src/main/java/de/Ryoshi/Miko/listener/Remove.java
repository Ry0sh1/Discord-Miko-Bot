package de.Ryoshi.Miko.listener;

import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;

public class Remove extends ListenerAdapter {

    private String shameChannelId = "1022827074257440799";

    public void onGuildMemberRemove (GuildMemberRemoveEvent event){

        LiteSQL.onUpdate("DELETE FROM cookies WHERE id = " + event.getMember().getId());

        EmbedBuilder shame = new EmbedBuilder();
        shame.setColor(0x9e5f7e);
        shame.setTitle("What a Shame!");
        shame.setDescription(event.getMember().getAsMention() + " has left the Server");
        shame.setImage("https://cdn.discordapp.com/attachments/878699176920248331/1015247395342274591/image0.jpg");
        shame.setFooter("Miko", "https://cdn.discordapp.com/attachments/740244977090560031/1022818181154340934/Character_Yae_Miko_Thumb.png");
        shame.setTimestamp(Instant.now());

        event.getGuild().getTextChannelById(shameChannelId).sendMessageEmbeds(shame.build()).queue();

    }

}
