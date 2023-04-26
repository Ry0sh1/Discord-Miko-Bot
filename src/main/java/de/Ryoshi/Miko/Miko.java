package de.Ryoshi.Miko;

import de.Ryoshi.Miko.listener.*;
import de.Ryoshi.Miko.manage.CommandManager;
import de.Ryoshi.Miko.manage.LiteSQL;
import de.Ryoshi.Miko.manage.SlashCommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;


import javax.security.auth.login.LoginException;

public class Miko {

    public static Miko INSTANCE;
    private CommandManager cmdMan;

    public static void main (String[] args){

        try {

            new Miko();

        } catch (LoginException e) {

            throw new RuntimeException(e);

        }

    }

    public Miko() throws LoginException{

        INSTANCE = this;

        LiteSQL.connect();
        SQLManager.onCreate();

        JDABuilder builder = JDABuilder.createDefault("");      //Insert your Token

        builder.setActivity(Activity.playing("teasing Onodera"));
        builder.setStatus(OnlineStatus.ONLINE);

        builder.enableIntents(GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS));
        builder.setChunkingFilter(ChunkingFilter.ALL);
        builder.setMemberCachePolicy(MemberCachePolicy.ALL);

        this.cmdMan = new CommandManager();

        //Command Manager

        builder.addEventListeners(new CommandListener());
        builder.addEventListeners(new SlashCommandManager());

        //Listener

        builder.addEventListeners(new CookieListener());
        builder.addEventListeners(new Join());
        builder.addEventListeners(new Remove());
        builder.addEventListeners(new VoiceJoin());
        builder.addEventListeners(new VoiceMove());
        builder.addEventListeners(new VoiceRemove());
        builder.addEventListeners(new Button());
        builder.addEventListeners(new ReactionAdd());
        builder.addEventListeners(new ReactionRemove());
        builder.addEventListeners(new SelectionMenuListener());

        JDA Miko = builder.build();

    }

    public CommandManager getCmdMan() {

        return cmdMan;
    }

}