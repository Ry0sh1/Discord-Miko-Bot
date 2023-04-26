package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class CheatCommandForCookies implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        LiteSQL.onUpdate("UPDATE cookies SET cookie = 1000000 WHERE id = " + m.getId());

    }

}
