package x.KenKOfficial.BallGame.Discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;

public class DiscordRP
{
    public void start() {
        DiscordRPC discord = DiscordRPC.INSTANCE;
        String appID = ""; // Tutaj trzeba podac ID swojej aplikacji
        String steamID = "";

        DiscordEventHandlers handlers = new DiscordEventHandlers();
        discord.Discord_Initialize(appID, handlers, true, steamID);

        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = "ballgame-large";
        presence.largeImageText = "v1.0-SNAPSHOT";
        presence.details = "v1.0-SNAPSHOT";
        presence.state = "In Game.";
        discord.Discord_UpdatePresence(presence);

        new Thread( () -> {
            System.out.println("THREAD STARTED");
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    presence.state = "In Game.";
                    discord.Discord_UpdatePresence(presence);
                    Thread.sleep(2000L);
                } catch(InterruptedException ignored) {

                }
            }
        }, "DISCORD-RPC-Thread").start();

    }
}
