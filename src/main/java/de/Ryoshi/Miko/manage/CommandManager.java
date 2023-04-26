package de.Ryoshi.Miko.manage;

import de.Ryoshi.Miko.commands.*;
import de.Ryoshi.Miko.commands.types.ServerCommand;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    public ConcurrentHashMap<String, ServerCommand> commands;

    public CommandManager(){

        this.commands = new ConcurrentHashMap<>();

        this.commands.put("clear", new ClearCommand());
        this.commands.put("message", new SendMessage());
        this.commands.put("embed", new SendEmbed());
        this.commands.put("join", new Join());
        this.commands.put("cookies", new Cookies());
        this.commands.put("gift", new Gift());
        this.commands.put("top", new Top());
        this.commands.put("flip", new FlipCookies());
        this.commands.put("cheatcommandforcookies", new CheatCommandForCookies());
        this.commands.put("cheatcommandforrelationship", new CheatCommandForRelationship());
        this.commands.put("reversecheatcommandforcookies", new ReverseCheatCommandForCookies());
        this.commands.put("setmydayoneearlier", new SetMyDayOneEarlier());
        this.commands.put("verify", new Verify());
        this.commands.put("introduce", new Introduce());
        this.commands.put("flirt", new Flirt());
        this.commands.put("fuck", new Fuck());
        this.commands.put("selfrolesex", new SelfRoleSex());
        this.commands.put("selfroleage", new SelfRoleAge());
        this.commands.put("selfroleorigin", new SelfRoleOrigin());

    }

    public boolean perform(String command, Member m, TextChannel channel, Message message){

        ServerCommand cmd;
        if((cmd = this.commands.get(command.toLowerCase())) != null){

            cmd.performCommand(m, channel, message, null);

            return true;
        }

        return false;
    }

}