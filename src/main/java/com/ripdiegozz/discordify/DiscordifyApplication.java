package com.ripdiegozz.discordify;

import ca.tristan.easycommands.EasyCommands;
import ca.tristan.easycommands.commands.defaults.HelpCmd;
import com.ripdiegozz.discordify.listeners.MessageListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.requests.GatewayIntent;


import java.io.IOException;
import java.net.URISyntaxException;

public class DiscordifyApplication implements EventListener {
    public static final GatewayIntent[] INTENTS = new GatewayIntent[]{
            GatewayIntent.GUILD_MESSAGES,
            GatewayIntent.DIRECT_MESSAGES,
            GatewayIntent.MESSAGE_CONTENT,
            GatewayIntent.GUILD_VOICE_STATES,
            GatewayIntent.GUILD_MESSAGE_REACTIONS
    };

    public static void main(String[] args)
            throws InterruptedException, IOException, URISyntaxException {
        // Instanciate EasyCommands to have better command handling
        EasyCommands easyCommands = new EasyCommands();

        System.out.println("Starting DiscordifyApplication");

        // MessageListener messageListener = new MessageListener();

        System.out.println("Creating JDA instance");
        JDA jda = easyCommands.addExecutor(
                        new HelpCmd(easyCommands)
                ).registerListeners(
                        // messageListener
                ).addGatewayIntents(
                        INTENTS
                ).addEnabledCacheFlags()
                .buildJDA();

        jda.addEventListener(new DiscordifyApplication());

        // optionally block until JDA is ready
        jda.awaitReady();

        System.out.println("JDA is ready!");


    }

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent) {
            System.out.println("API is ready!");
        }
    }
}
