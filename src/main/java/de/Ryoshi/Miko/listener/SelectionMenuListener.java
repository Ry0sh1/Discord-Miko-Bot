package de.Ryoshi.Miko.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class SelectionMenuListener extends ListenerAdapter {

    public void onSelectMenuInteraction (SelectMenuInteractionEvent event){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMddHHmm").withLocale(Locale.getDefault()).withZone(ZoneId.of("UTC"));

        String sMessage = formatter.format(event.getMessage().getTimeCreated().toInstant());
        String sNow = formatter.format(Instant.now());

        long message = Long.parseLong(sMessage);
        long now = Long.parseLong(sNow);


        switch (event.getValues().get(0)){

                case "cookies":

                    if(now - message > 10) {

                        EmbedBuilder tooLate = new EmbedBuilder()
                                .setColor(0x9e5f7e)
                                .setTitle("An unexpected error has occurred")
                                .setDescription("The message is too old to interact with it");

                        event.getMessage().editMessageEmbeds(tooLate.build()).queue();

                        event.reply("** **").complete().deleteOriginal().queue();

                    } else {

                        EmbedBuilder cookies = new EmbedBuilder()
                                .setColor(0x9e5f7e)
                                .setTitle("Help")
                                .addField("What is the Cookie Grind?", "In the Cookie grind members collect cookies to trade, play or buy cool stuff", false)
                                .addField("How do i get Cookies?", "You get 1 Cookies from every message you write. You can also get Cookies by playing games like Cookieflip but just like that you can also lose Cookies", false)
                                .addField("Why should i collect Cookies?", "You can buy roles with cookies that are displayed separately from other member. You can even buy extra permissions and choose the color of your name", false)
                                .addField("Useful commands to successfully start the cookie Grind", "```/cookies\n``` View your or another Member's collected Cookies\n```/flip\n``` to flip a Coin. 50% success rate. Choose your stake wisely you will lose everything on a loss", false);

                        event.getMessage().editMessageEmbeds(cookies.build()).queue();

                        event.reply("** **").complete().deleteOriginal().queue();

                    }

                    break;

                case "relationship":

                    if (now - message > 10) {

                        event.reply("Too late").queue();

                    } else {

                        EmbedBuilder relationship = new EmbedBuilder()
                                .setColor(0x9e5f7e)
                                .setTitle("Help")
                                .addField("How can i start a relationship with Miko?", "Like in real life the first thing your should do to start a relationship with Miko is to introduce yourself to her otherwise it would be weird", false)
                                .addField("How can i boost my relationship with Miko?", "You can boost your relationship with Miko by talking to her everyday or by gifting her Cookies or by complimenting her. There are also other ways to boost your relationship which you unlock by buying them with Cookies or even sum you unlock with a higher relationship Level", false)
                                .addField("Why should i boost my relationship with Miko?", "You get a better Cookie multiplier with higher level. Special and cool interactions with Miko and Miko may also gift you some Cookies", false)
                                .addField("Useful commands to successfully start a relationship with Miko", "```/introduce\n```Introduce yourself to Miko to start a relationship\n```/opinion (private)\n```Ask Miko how she feels towards you\n```/talk [private/public]\n```Talk to Miko. You can choose to talk with her private or in public\n```/flirt (private)\n```Flirt with Miko (relationship level 3 required)\n```/compliment\n```Compliment Miko\n```/fuck (private)\n```Fuck with Miko (relationship level 10 required)\n```/nudes (private)\n```Ask Miko for Nudes (relationship level 9 required)", false);

                        event.getMessage().editMessageEmbeds(relationship.build()).queue();

                        event.reply("** **").complete().deleteOriginal().queue();

                    }

                    break;

                case "random":

                    if (now - message > 10) {

                        event.reply("Too late").setEphemeral(true).queue();

                    } else {

                        EmbedBuilder random = new EmbedBuilder()
                                .setColor(0x9e5f7e)
                                .setTitle("Help")
                                .setDescription("Still in progress");

                        event.getMessage().editMessageEmbeds(random.build()).queue();

                        event.reply("** **").complete().deleteOriginal().queue();

                    }

                    break;

            }

        }

    }
