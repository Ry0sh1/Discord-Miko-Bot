package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;

public class SendEmbed implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        String msg = message.getContentDisplay().substring(7);

        String[] args = msg.split("%");

        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(Color.decode(args[1]));
        embed.setTitle(args[2]);
        embed.setDescription(args[3]);
        embed.setImage(args[4]);

        m.getGuild().getTextChannelById(Long.parseLong(args[0])).sendMessageEmbeds(embed.build()).queue();

    }

}
