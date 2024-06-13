package com.ripdiegozz.discordify.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Objects;

public class MessageListener extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        // This makes sure we only execute our code when someone sends a message with "!play"
        if (!event.getMessage().getContentRaw().startsWith("!play ")) return;
        // Now we want to exclude messages from bots since we want to avoid command loops in chat!
        // this will include own messages as well for bot accounts
        // if this is not a bot make sure to check if this message is sent by yourself!
        if (event.getAuthor().isBot()) return;

        Message message = event.getMessage();

        if (message.getContentDisplay().startsWith("!play ")) {
            String response = "Playing " + message.getContentDisplay().substring(6);

            event.getChannel().sendMessage(response).queue((res) ->
                System.out.printf("[%s][%s] REPRODUCING %s - REQUESTED BY %s\n", event.getGuild().getName(),
                     event.getChannel().getName(), event.getMessage().getContentDisplay(), Objects.requireNonNull(event.getMember()).getEffectiveName())
            );
        }

        System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
            event.getChannel().getName(), Objects.requireNonNull(event.getMember()).getEffectiveName(),
            event.getMessage().getContentDisplay());
    }
}
