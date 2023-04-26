package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.sql.SQLException;

public class Top implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        try {

            int mostCookies = LiteSQL.onQuery("SELECT MAX (cookie) FROM cookies").getInt(1);
            int secondCookies = LiteSQL.onQuery("SELECT MAX (cookie) FROM cookies WHERE cookie < (SELECT MAX (cookie) FROM cookies)").getInt(1);
            int thirdCookies = LiteSQL.onQuery("SELECT MAX (cookie) FROM cookies WHERE cookie < (SELECT MAX (cookie) FROM cookies WHERE cookie < (SELECT MAX (cookie) FROM cookies))").getInt(1);

            String mostCookiesId = LiteSQL.onQuery("SELECT id FROM cookies WHERE cookie = " + mostCookies).getString(1);
            String secondCookiesId = LiteSQL.onQuery("SELECT id FROM cookies WHERE cookie = " + secondCookies).getString(1);
            String thirdCookiesId = LiteSQL.onQuery("SELECT id FROM cookies WHERE cookie = " + thirdCookies).getString(1);

            EmbedBuilder top = new EmbedBuilder();
            top.setColor(0x9e5f7e);
            top.setTitle(":trophy: Cookie:cookie: Ranking");
            top.setDescription("\n ** ** \n:first_place:" + m.getGuild().retrieveMemberById(mostCookiesId).complete().getAsMention() + "\n" +
                    "with " + mostCookies + " :cookie: " +
                    "\n ** ** \n:second_place:" + m.getGuild().retrieveMemberById(secondCookiesId).complete().getAsMention() + "\n" +
                    "with " + secondCookies + " :cookie: " +
                    "\n ** ** \n:third_place:" + m.getGuild().retrieveMemberById(thirdCookiesId).complete().getAsMention() + "\n" +
                    "with " + thirdCookies + " :cookie: ");
            channel.sendMessageEmbeds(top.build()).queue();

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}
