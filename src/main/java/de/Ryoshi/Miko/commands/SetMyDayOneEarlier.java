package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.time.LocalDate;

public class SetMyDayOneEarlier implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        int today = Integer.parseInt("" + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth());
        int earlierDay = today - 1;
        LiteSQL.onUpdate("UPDATE cookies set lastflip = " + earlierDay + " WHERE id = " + m.getId());
        LiteSQL.onUpdate("UPDATE cookies set lastflirt = " + earlierDay + " WHERE id = " + m.getId());

    }

}
