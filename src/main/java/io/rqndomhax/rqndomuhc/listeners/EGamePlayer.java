package io.rqndomhax.rqndomuhc.listeners;

import io.rqndomhax.uhcapi.events.GamePlayerAddedEvent;
import io.rqndomhax.uhcapi.events.GamePlayerRemovedEvent;
import io.rqndomhax.rqndomuhc.game.GamePlayer;
import io.rqndomhax.uhcapi.UHCAPI;
import io.rqndomhax.uhcapi.game.IGamePlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EGamePlayer implements Listener {

    private final UHCAPI api;

    public EGamePlayer(UHCAPI api) {
        this.api = api;
    }

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
        if (!api.getGameTaskManager().getGameState().equals("LOBBY"))
            return;
        GamePlayerAddedEvent playerAdd = new GamePlayerAddedEvent(new GamePlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName()));
        Bukkit.getPluginManager().callEvent(playerAdd);
        if (!playerAdd.isCancelled())
            api.getGamePlayers().add(playerAdd.getGamePlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (!api.getGameTaskManager().getGameState().equals("LOBBY"))
            return;
        IGamePlayer gamePlayer = api.getGamePlayer(event.getPlayer().getUniqueId());
        if (gamePlayer == null)
            return;
        GamePlayerRemovedEvent playerRemoved = new GamePlayerRemovedEvent(gamePlayer);
        Bukkit.getPluginManager().callEvent(playerRemoved);
        if (!playerRemoved.isCancelled())
            api.getGamePlayers().remove(playerRemoved.getGamePlayer());
    }
}
