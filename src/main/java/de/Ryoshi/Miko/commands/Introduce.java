package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.SQLException;

public class Introduce implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        try {

            if (LiteSQL.onQuery("SELECT relationship FROM cookies WHERE id =" + m.getId()).getInt(1) == -1){

                channel.sendMessage("Hello " + m.getAsMention() + ", nice to meet you. I am Miko, the server's own bot and i am happy to spend the time on this Server with you together:relaxed:").queue();

                LiteSQL.onUpdate("UPDATE cookies SET relationship = 0 WHERE id = " + m.getId());

            } else {

                channel.sendMessage("I already know you. No need to introduce ourselves again").queue();

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}
