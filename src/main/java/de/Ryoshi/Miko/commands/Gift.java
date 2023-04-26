package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.SQLException;

public class Gift implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        String msg = message.getContentDisplay().substring(6);

        String args[] = msg.split(" ");

        Long id = Long.parseLong(message.getMentions().getMembers().get(0).getId());

        int amount = Integer.parseInt(args[1]);

        try {

            if (LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id =" + m.getId()).getInt(1) >= amount){

                int preCookiesGifted = LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id = " + id).getInt(1);
                int preCookiesGifting = LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id =" + m.getId()).getInt(1);

                preCookiesGifted = preCookiesGifted + amount;
                preCookiesGifting = preCookiesGifting - amount;

                LiteSQL.onUpdate("UPDATE cookies SET cookie = " + preCookiesGifted + " WHERE id = " + id);
                LiteSQL.onUpdate("UPDATE cookies SET cookie = " + preCookiesGifting + " WHERE id = " + m.getId());

                channel.sendMessage(m.getAsMention() + " you gifted " + m.getGuild().retrieveMemberById(id).complete().getAsMention() + " " + args[1] + " Cookies:cookie:!").queue();

            }
            else {

                channel.sendMessage("You do not have enough Cookies!:cookie:").queue();

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}
