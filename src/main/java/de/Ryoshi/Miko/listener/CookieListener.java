package de.Ryoshi.Miko.listener;

import de.Ryoshi.Miko.manage.LiteSQL;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;

public class CookieListener extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {

        try {

            if (LiteSQL.onQuery("SELECT id FROM cookies WHERE id = " + event.getMember().getId()).getString(1) == null){

                LiteSQL.onUpdate("INSERT INTO cookies(id, cookie, relationship, lastflirt, lastflip) VALUES (" +event.getMember().getId() + ",0, -1, null, null)");

            }else {

                int preCookies = LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id = " + event.getMember().getId()).getInt(1);

                preCookies = preCookies + 1;

                LiteSQL.onUpdate("UPDATE cookies SET cookie = " + preCookies + " WHERE id = " + event.getMember().getId());

            }

        } catch (SQLException e) {

            throw new RuntimeException(e);

        }

    }

}