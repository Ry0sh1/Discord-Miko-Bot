package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClearCommand implements ServerCommand {

    private int amount;

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        if (m.hasPermission(channel, Permission.MESSAGE_MANAGE)) {

            if (message != null) {

                message.delete().queue();

                String[] args = message.getContentDisplay().split(" ");

                if (args.length == 2) {

                    try {

                        int amt = Integer.parseInt(args[1]);
                        channel.purgeMessages(get(channel, amt));

                        channel.sendMessage(amt + " Nachrichten wurden gelöscht!").complete().delete().queueAfter(5, TimeUnit.SECONDS);

                    } catch (NumberFormatException e) {

                        throw new RuntimeException(e);

                    }

                }

            } else{

                amount = Integer.parseInt(extraString);

                try {

                    channel.purgeMessages(get(channel, amount));
                    channel.sendMessage(amount + " Nachrichten wurden gelöscht!").complete().delete().queueAfter(5, TimeUnit.SECONDS);

                } catch (NumberFormatException e) {

                    throw new RuntimeException(e);

                }

            }

        }

    }

    public List<Message> get(MessageChannel channel, int amount){

        List<Message> messages = new ArrayList<>();
        int i = amount + 1;

        for (Message message : channel.getIterableHistory().cache(false)){

            if (!message.isPinned()){

                messages.add(message);

            }

            if(--i <= 0) break;

        }


        return messages;
    }

}