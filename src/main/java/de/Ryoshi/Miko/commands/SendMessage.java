package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class SendMessage implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        String msg = message.getContentDisplay().substring(9);

        String[] args = msg.split(":");

        m.getGuild().getTextChannelById(args[0]).sendMessage(args[1]).queue();

    }

}
