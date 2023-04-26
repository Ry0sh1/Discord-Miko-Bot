package de.Ryoshi.Miko.manage;

import de.Ryoshi.Miko.commands.ClearCommand;
import de.Ryoshi.Miko.commands.Cookies;
import de.Ryoshi.Miko.commands.Flirt;
import de.Ryoshi.Miko.commands.Fuck;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SlashCommandManager extends ListenerAdapter {

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){

        String command = event.getName();

        if (command.equals("flirt")){

            new Flirt().performCommand(event.getMember(), event.getChannel().asTextChannel(), null, null);

            event.reply("** **").complete().deleteOriginal().queue();

        }

        if (command.equals("fuck")){

            new Fuck().performCommand(event.getMember(), event.getChannel().asTextChannel(), null, null);

            event.reply("** **").complete().deleteOriginal().queue();

        }

        if (command.equals("cookies")){

            try {

                Mentions m = event.getOption("user").getMentions();
                event.reply(new Cookies().getResult(event.getMember(), m)).queue();

            }catch (NullPointerException a){

                event.reply(new Cookies().getResult(event.getMember(), null)).queue();

            }

            //event.reply("** **").complete().deleteOriginal().queue();

        }

        if (command.equals("clear")){

            OptionMapping option = event.getOption("amount");

            try {

                int a = Integer.parseInt(option.getAsString());

                String amount = option.getAsString();

                new ClearCommand().performCommand(event.getMember(), event.getChannel().asTextChannel(), null, amount);

                event.reply("** **").complete().deleteOriginal().queue();

            } catch (NumberFormatException e) {

                event.reply("Invalid Number").setEphemeral(true);

            }

            event.reply("Invalid Number").setEphemeral(true);

        }

        if (command.equals("help")){

            EmbedBuilder help = new EmbedBuilder();

            help.setColor(0x9e5f7e);
            help.setTitle("Help");
            help.setDescription("Chose the category in the selection menu to get more specific help");

            SelectMenu selectMenu = SelectMenu.create("Help")
                    .setPlaceholder("Categories")
                    .addOption("Cookies", "cookies", "Chose this to see more information about the Cookie grind", Emoji.fromUnicode("🍪"))
                    .addOption("Relationship", "relationship", "Chose this to see more information about the Relationship system", Emoji.fromUnicode("❤"))
                    .addOption("Random", "random", "Chose this to see random commands that doesn't belong to a specific category", Emoji.fromUnicode("❓"))
                    .build();


            event.replyEmbeds(help.build()).setActionRow(selectMenu).queue();

        }

    }

    public void onUserContextInteraction(UserContextInteractionEvent event){

        if (event.getName().equals("Avatar")) {

            event.reply("Here is " + event.getTargetMember().getAsMention() + "'s [avatar](" + event.getTarget().getAvatarUrl() + "?size=4096&ignore=true).").queue();

        }

        if (event.getName().equals("Profile")){

            try {

                int cookies = LiteSQL.onQuery("SELECT cookie FROM cookies WHERE id = " + event.getTarget().getId()).getInt(1);
                int relationshipLevel = LiteSQL.onQuery("SELECT relationship FROM cookies WHERE id = " + event.getTarget().getId()).getInt(1);

                if (event.getTarget().getId().equals("1014963762748461076")){

                    event.reply("Im the boss here. That's the only profile you need to see").queue();

                } else {

                    EmbedBuilder profile = new EmbedBuilder();
                    profile.setColor(0x9e5f7e);
                    profile.setTitle("Profile Info");
                    profile.setDescription(event.getTargetMember().getAsMention() + "'s profile");
                    profile.addField("Cookies", "" + cookies + " :cookie:", false);
                    profile.addField("Relationship Level", "" + relationshipLevel + " :heart:", false);
                    profile.setFooter("Miko", "https://cdn.discordapp.com/attachments/740244977090560031/1022818181154340934/Character_Yae_Miko_Thumb.png");
                    profile.setTimestamp(Instant.now());

                    event.replyEmbeds(profile.build()).queue();

                }

            } catch (SQLException e) {

                throw new RuntimeException(e);

            }

        }

        if (event.getName().equals("User Info")) {

            EmbedBuilder Avatar = new EmbedBuilder();
            Avatar.setColor(0x9e5f7e);
            Avatar.setAuthor("" + event.getTarget().getAsTag(), "https://discord.gg/cSWqkb2t", event.getTarget().getAvatarUrl());
            Avatar.setThumbnail(event.getTarget().getAvatarUrl());

            Avatar.addField("ID", "" + event.getTarget().getId(), true);

            if (event.getTargetMember().getNickname() != null){
                Avatar.addField("Nickname", "" + event.getTargetMember().getNickname(), true);
            }
            Avatar.addField("Nickname", "None", true);

            Date createDate = Date.from(event.getTarget().getTimeCreated().toInstant());
            Avatar.addField("Created Date", "<t:" + createDate.getTime() / 1000 + ":f> (<t:" + createDate.getTime() / 1000 + ":R>)", false);

            Date joinDate = Date.from(event.getTargetMember().getTimeJoined().toInstant());
            Avatar.addField("Join Date", "<t:" + joinDate.getTime() / 1000 + ":f> (<t:" + joinDate.getTime() / 1000 + ":R>)", false);

            if (!event.getTargetMember().getRoles().isEmpty()) {

                String roles = "" + event.getTargetMember().getRoles().get(0).getAsMention();

                for (int i = 1; i < event.getTargetMember().getRoles().size(); i++) {

                    roles = roles + ", " + event.getTargetMember().getRoles().get(i).getAsMention() + " ";

                }

                Avatar.addField("Roles [" + event.getTargetMember().getRoles().size() + "]", roles, false);

            }else {

                Avatar.addField("Roles [1]", "@everyone ", false);

            }

            List<Permission> permissions = new ArrayList<>(event.getTargetMember().getPermissions().size());

            for (Permission s : event.getTargetMember().getPermissions()) {

                permissions.add( s);

            }

            String pms = "" + permissions.get(0).getName();

            for (int i = 1; i < permissions.size(); i++){

                pms = pms + ", " + permissions.get(i).getName() + " ";

            }

            Avatar.addField("Permission(s)", pms, false);

            Avatar.setFooter("Miko", "https://cdn.discordapp.com/attachments/740244977090560031/1022818181154340934/Character_Yae_Miko_Thumb.png");

            Avatar.setTimestamp(Instant.now());

            event.replyEmbeds(Avatar.build()).queue();

        }

    }


    public void onGuildReady(GuildReadyEvent event){

        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("flirt", "Flirt with me"));
        commandData.add(Commands.slash("fuck", "Fuck with me"));
        commandData.add(Commands.slash("cookies", "Watch your Cookies"));
        commandData.add(Commands.slash("clear", "Clear the last messages").addOption(OptionType.STRING, "amount", "The amount of messages you want to delete", true));
        commandData.add(Commands.slash("help", "If you need help"));
        commandData.add(Commands.context(Command.Type.USER, "Avatar"));
        commandData.add(Commands.context(Command.Type.USER, "User Info"));
        commandData.add(Commands.context(Command.Type.USER, "Profile"));
        event.getGuild().updateCommands().addCommands(commandData).queue();

    }

}
