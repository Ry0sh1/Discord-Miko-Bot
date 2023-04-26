package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;

public class FlipCookies implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        //!flip [inset]

        int inset = Integer.parseInt(message.getContentDisplay().substring(6));

        try {

            if (LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id = " + m.getId()).getInt(1) >= inset) {

                if (LiteSQL.onQuery("SELECT lastflip FROM cookies WHERE id =" + m.getId()).getString(1) == null || Integer.parseInt("" + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth()) - Integer.parseInt(LiteSQL.onQuery("SELECT lastflip FROM cookies WHERE id =" + m.getId()).getString(1)) >= 1) {

                    LiteSQL.onUpdate("UPDATE cookies SET lastflip = " + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + LocalDate.now().getDayOfMonth() + " WHERE id = " + m.getId());

                    Random rand = new Random();

                    int n = rand.nextInt(50);

                    //Success

                    if (n > 25) {

                        try {

                            int oldCookies = LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id = " + m.getId()).getInt(1);

                            int newCookies = oldCookies + inset;

                            LiteSQL.onUpdate("UPDATE cookies SET cookie = " + newCookies + " WHERE id = " + m.getId());

                            channel.sendMessage("Congratulation! You just won " + inset + " Cookies!:cookie:").queue();

                        } catch (SQLException e) {

                            throw new RuntimeException(e);

                        }

                    }

                    //Fail

                    if (n < 25) {

                        int oldCookies = LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id = " + m.getId()).getInt(1);

                        int newCookies = oldCookies - inset;

                        LiteSQL.onUpdate("UPDATE cookies SET cookie = " + newCookies + " WHERE id = " + m.getId());

                        channel.sendMessage("Im sorry for you! You just lost " + inset + " Cookies!:cookie:").queue();

                    }

                } else {

                    channel.sendMessage("You need to wait to do the next flip!").queue();

                }

            }else {

                channel.sendMessage("You don't have enough Cookies:cookie: for that!").queue();

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}
