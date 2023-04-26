package de.Ryoshi.Miko.listener;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionAdd extends ListenerAdapter {

    String selfRoleSexId = "1017738376062976012";
    String selfRoleAgeId = "1017738396543766530";
    String selfRoleOriginId = "1017738415959195728";

    public String man = "1014966851966599218";
    public String woman = "1014966943884783626";
    public String nonBinary = "1014967007298469968";

    public String baby = "1014967051451904071";
    public String child = "1014967114592944188";
    public String adult = "1014967151288918076";

    public String europe = "1014967191843643392";
    public String asia = "1014967225972707459";
    public String america = "1014967257480318996";

    public void onMessageReactionAdd (MessageReactionAddEvent event){

        //sex

        if (event.getMessageId().equals(selfRoleSexId)){

            if (event.getReaction().getEmoji().getAsReactionCode().equals("\uD83D\uDEB9")){

                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(nonBinary)).queue();

                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(woman)).queue();

                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(man)).queue();

            }

            if (event.getReaction().getEmoji().getAsReactionCode().equals("\uD83D\uDEBA")){

                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(nonBinary)).queue();

                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(man)).queue();

                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(woman)).queue();

            }

            if (event.getReaction().getEmoji().getAsReactionCode().equals("❔")){

                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(woman)).queue();

                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(man)).queue();

                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(nonBinary)).queue();

            }

        }

        //age

        if (event.getMessageId().equals(selfRoleAgeId)) {

            if (event.getReaction().getEmoji().getAsReactionCode().equals("\uD83D\uDC76")) {

                //remove child
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(child)).queue();

                //remove adult
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(adult)).queue();

                //add baby
                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(baby)).queue();

            }

            if (event.getReaction().getEmoji().getAsReactionCode().equals("\uD83E\uDDD2")) {

                //remove baby
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(baby)).queue();

                //remove adult
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(adult)).queue();

                //add child
                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(child)).queue();

            }

            if (event.getReaction().getEmoji().getAsReactionCode().equals("\uD83E\uDDD1")) {

                //remove baby
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(baby)).queue();

                //remove child
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(child)).queue();

                //add adult
                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(adult)).queue();

            }

        }

        //origin

        if (event.getMessageId().equals(selfRoleOriginId)) {

            if (event.getReaction().getEmoji().getAsReactionCode().equals("🌍")) {

                //remove asia
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(asia)).queue();

                //remove america
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(america)).queue();

                //add europe
                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(europe)).queue();

            }

            if (event.getReaction().getEmoji().getAsReactionCode().equals("\uD83C\uDF0F")) {

                //remove europe
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(europe)).queue();

                //remove america
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(america)).queue();

                //add asia
                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(asia)).queue();

            }

            if (event.getReaction().getEmoji().getAsReactionCode().equals("\uD83C\uDF0E")) {

                //remove europe
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(europe)).queue();

                //remove asia
                event.getGuild().removeRoleFromMember(event.getMember(), event.getGuild().getRoleById(asia)).queue();

                //add america
                event.getGuild().addRoleToMember(event.getMember(), event.getGuild().getRoleById(america)).queue();

            }

        }

    }

}
