package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.SQLException;

public class Cookies implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString ) {

        try {

            if (message.getMentions().getMembers().isEmpty()){

                channel.sendMessage(m.getAsMention() + " you have " + LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id=" + m.getId()).getInt(1) + " Cookies:cookie:!").queue();

            }else {

                long meId = Long.parseLong(message.getMentions().getMembers().get(0).getId());

                channel.sendMessage(message.getMentions().getMembers().get(0).getAsMention() + " has " + LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id =" + meId).getInt(1) + " Cookies:cookie:!").queue();

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public String getResult(Member m, Mentions ment){

        try {

            if (ment == null){

                  return m.getAsMention() + " you have " + LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id=" + m.getId()).getInt(1) + " Cookies:cookie:!";

            }else {

                long meId = Long.parseLong(ment.getMembers().get(0).getId());

                return ment.getMembers().get(0).getAsMention() + " has " + LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id =" + meId).getInt(1) + " Cookies:cookie:!";

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}
