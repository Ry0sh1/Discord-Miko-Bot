package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class SelfRoleAge implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        EmbedBuilder selfRoleSex = new EmbedBuilder();
        selfRoleSex.setTitle("What is your age?");
        selfRoleSex.setDescription(":baby: 0-13 years \n:child: 14-17 years \n:adult: 18+ years");
        selfRoleSex.setColor(0x9e5f7e);

        channel.sendMessageEmbeds(selfRoleSex.build()).queue(messageR -> {messageR.addReaction(Emoji.fromUnicode("\uD83D\uDC76")).queue();
            messageR.addReaction(Emoji.fromUnicode("\uD83E\uDDD2")).queue();
            messageR.addReaction(Emoji.fromUnicode("\uD83E\uDDD1")).queue();});

    }

}