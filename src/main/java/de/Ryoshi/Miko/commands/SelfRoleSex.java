package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class SelfRoleSex implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        EmbedBuilder selfRoleSex = new EmbedBuilder();
        selfRoleSex.setTitle("What is your gender?");
        selfRoleSex.setDescription(":mens: Male \n:womens: Female \n:grey_question: diverse");
        selfRoleSex.setColor(0x9e5f7e);

        channel.sendMessageEmbeds(selfRoleSex.build()).queue(messageR -> {messageR.addReaction(Emoji.fromUnicode("\uD83D\uDEB9")).queue();
            messageR.addReaction(Emoji.fromUnicode("\uD83D\uDEBA")).queue();
            messageR.addReaction(Emoji.fromUnicode("❔")).queue();});

    }

}
