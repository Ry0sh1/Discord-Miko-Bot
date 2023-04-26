package de.Ryoshi.Miko.commands;

import de.Ryoshi.Miko.commands.types.ServerCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class Verify implements ServerCommand {

    public void performCommand(Member m, TextChannel channel, Message message, String extraString) {

        EmbedBuilder verify = new EmbedBuilder();
        verify.setTitle("Verification");
        verify.setDescription("To actually use this server and to make new friends you need to verify first");
        verify.setColor(0x9e5f7e);

        Button verifyBtn = Button.success("verify", "Verification");

        channel.sendMessageEmbeds(verify.build()).addActionRow(verifyBtn).queue();

    }

}
