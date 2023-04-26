package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class Join implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        LiteSQL.onUpdate("INSERT INTO cookies(id, cookie, relationship, lastflirt, lastflip) VALUES (" +m.getId() + ",0, -1, null, null)");

    }

}
