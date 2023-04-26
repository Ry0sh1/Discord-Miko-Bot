package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class Fuck implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0x9e5f7e);
        embed.setTitle("You want to fuck?");
        embed.setImage("https://cdn.discordapp.com/attachments/740244977090560031/1096170710919036990/thumbnail.jpg");

        channel.sendMessageEmbeds(embed.build()).queue();

    }

    public EmbedBuilder getResult(){

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0x9e5f7e);
        embed.setTitle("You want to fuck?");
        embed.setImage("https://cdn.discordapp.com/attachments/1017275221385949296/1017275305657905284/thumbnail_a0241fbef4104598576f475789daecfc3bb33400.jpg");

        return embed;
    }

}
