package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class SelfRoleOrigin implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        EmbedBuilder selfRoleOrigin = new EmbedBuilder();
        selfRoleOrigin.setTitle("Where are u from?");
        selfRoleOrigin.setDescription(":earth_africa: Europe \n:earth_asia: Asia \n:earth_americas: America");
        selfRoleOrigin.setColor(0x9e5f7e);

        channel.sendMessageEmbeds(selfRoleOrigin.build()).queue(messageR -> {messageR.addReaction(Emoji.fromUnicode("🌍")).queue();
            messageR.addReaction(Emoji.fromUnicode("\uD83C\uDF0F")).queue();
            messageR.addReaction(Emoji.fromUnicode("\uD83C\uDF0E")).queue();});

    }

}