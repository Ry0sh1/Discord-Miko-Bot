package de.Ryoshi.Miko.listener;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Button extends ListenerAdapter {

    public String notVerified = "1014967846591266898";

    public void onButtonInteraction (ButtonInteractionEvent event){

        if (event.getButton().getId().equals("verify")){

            event.deferEdit().queue();

            event.getGuild().removeRoleFromMember(event.getUser(), event.getGuild().getRoleById(notVerified)).queue();

        }

    }

}
