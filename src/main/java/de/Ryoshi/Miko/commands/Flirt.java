package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.SQLException;
import java.time.LocalDate;

public class Flirt implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        try {

            if (LiteSQL.onQuery("SELECT relationship FROM cookies WHERE id =" + m.getId()).getInt(1) > 3){

                int time = Integer.parseInt("" + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth());

                if (LiteSQL.onQuery("SELECT lastflirt FROM cookies WHERE id =" + m.getId()).getString(1) == null){

                    channel.sendMessage("Its getting really hot here :hot_face:").queue();
                    LiteSQL.onUpdate("UPDATE cookies SET lastflirt = " + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + " WHERE id = " + m.getId());

                } else {

                    int lastFlirt = Integer.parseInt(LiteSQL.onQuery("SELECT lastflirt FROM cookies WHERE id =" + m.getId()).getString(1));

                    if (time - lastFlirt >= 1) {

                        channel.sendMessage("Its getting really hot here :hot_face:").queue();
                        LiteSQL.onUpdate("UPDATE cookies SET lastflirt = " + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + " WHERE id = " + m.getId());

                    } else {

                        channel.sendMessage("Haha quite soon for you boya!").queue();

                    }

                }

            } else {

                if (LiteSQL.onQuery("SELECT relationship FROM cookies WHERE id =" + m.getId()).getInt(1) >= 0) {

                    channel.sendMessage("This is too fast for me").queue();

                } else {

                   channel.sendMessage("I don't even know you. Don't bother me!").queue();

                }

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

    public String getResult(Member m, TextChannel channel, Message message) {

        try {

            if (LiteSQL.onQuery("SELECT relationship FROM cookies WHERE id =" + m.getId()).getInt(1) > 3){

                int time = Integer.parseInt("" + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth());

                if (LiteSQL.onQuery("SELECT lastflirt FROM cookies WHERE id =" + m.getId()).getString(1) == null){

                    LiteSQL.onUpdate("UPDATE cookies SET lastflirt = " + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + " WHERE id = " + m.getId());
                    return "Its getting really hot here :hot_face:";

                } else {

                    int lastFlirt = Integer.parseInt(LiteSQL.onQuery("SELECT lastflirt FROM cookies WHERE id =" + m.getId()).getString(1));

                    if (time - lastFlirt >= 1) {

                        LiteSQL.onUpdate("UPDATE cookies SET lastflirt = " + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + " WHERE id = " + m.getId());
                        return "Its getting really hot here :hot_face:";

                    } else {

                        return "Haha quite soon for you boya!";

                    }

                }

            } else {

                if (LiteSQL.onQuery("SELECT relationship FROM cookies WHERE id =" + m.getId()).getInt(1) >= 0) {

                    return "This is too fast for me";

                } else {

                    return "I don't even know you. Don't bother me!";

                }

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}
