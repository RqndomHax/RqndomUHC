/*
 * Copyright (c) 2021.
 *  Github: https://github.com/RqndomHax
 */

package io.rqndomhax.rqndomuhc.scenarios;

import io.rqndomhax.uhcapi.UHCAPI;
import io.rqndomhax.uhcapi.game.IGamePlayer;
import io.rqndomhax.uhcapi.game.IScenario;
import io.rqndomhax.uhcapi.utils.RValue;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.stream.Collectors;

public class SCatEyes extends RValue implements Listener, IScenario {

    final UHCAPI api;

    public SCatEyes(UHCAPI api) {
        this.api = api;
        addObject("item", Material.GOLDEN_CARROT);
        addObject("author", "unknown");
        addObject("name", "Cat eyes");
    }

    @Override
    public void init() {
        for (IGamePlayer gamePlayer : api.getGamePlayers())
            init(gamePlayer);
    }

    @Override
    public void destroy() {
            for (Player player : Bukkit.getOnlinePlayers().stream().filter(player -> api.getGamePlayer(player) != null).collect(Collectors.toSet()))
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
    }

    @Override
    public void init(IGamePlayer gamePlayer) {
        if (gamePlayer == null)
            return;
        Player player = gamePlayer.getPlayer();
        if (player == null || !player.isOnline())
            return;
        player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 9999999, 0, true));
    }

    @Override
    public RValue getScenarioInfos() {
        return this;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        init(api.getGamePlayer(event.getPlayer()));
    }
}
