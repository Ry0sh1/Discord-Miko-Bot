package de.Ryoshi.Miko.listener;

import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Join extends ListenerAdapter {

    public String welcomeChannel = "1014955832628236348";
    public String ruleChannel = "1014874700796670052";
    public String introductionChannel = "1017261038560874606";
    public String notVerified = "1014967846591266898";

    public void onGuildMemberJoin (GuildMemberJoinEvent event){

        event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(notVerified)).queue();

        LiteSQL.onUpdate("INSERT INTO cookies(id, cookie, relationship, lastflirt, lastflip) VALUES (" + event.getMember().getId() + ",0, -1, null, null)");

        EmbedBuilder welcome = new EmbedBuilder();
        welcome.setDescription("Welcome " + event.getMember().getUser().getAsMention() + "!" +
                "\nYou are now working for me, Miko! " +
                "\nPls check the " + event.getGuild().getGuildChannelById(ruleChannel).getAsMention() + " before working " +
                "\nand introduce yourself in " + event.getGuild().getGuildChannelById(introductionChannel).getAsMention() + "! " +
                "\nEnjoy your Cookie:cookie: grind!");
        welcome.setColor(0x9e5f7e);
        welcome.setImage("https://cdn.discordapp.com/attachments/878699176920248331/1014952329147064481/unknown.png");
        welcome.setThumbnail(event.getUser().getAvatarUrl());
        welcome.setFooter("We are now " + event.getGuild().getMemberCount() + " Mitglieder");

        event.getGuild().getTextChannelById(welcomeChannel).sendMessageEmbeds(welcome.build()).queue();

    }

}
