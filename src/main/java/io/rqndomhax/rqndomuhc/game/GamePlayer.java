/*
 * Copyright (c) 2021.
 *  Github: https://github.com/RqndomHax
 */

package io.rqndomhax.rqndomuhc.game;

import io.rqndomhax.uhcapi.game.IGamePlayer;
import io.rqndomhax.uhcapi.utils.RValue;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GamePlayer implements IGamePlayer {

    boolean isAlive = true;
    final UUID playerUniqueID;
    final String playerName;

    final Set<UUID> playerKills = new HashSet<>();
    Location lastKnownLocation = null;
    ItemStack[] inventory = new ItemStack[40];
    final RValue playerInfos = new RValue();

    public GamePlayer(UUID playerUniqueID, String playerName) {
        this.playerUniqueID = playerUniqueID;
        this.playerName = playerName;
    }

    @Override
    public UUID getUniqueID() {
        return playerUniqueID;
    }

    @Override
    public String getName() {
        return playerName;
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(playerUniqueID);
    }

    @Override
    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(playerUniqueID);
    }

    @Override
    public boolean isAlive() {
        return isAlive;
    }

    @Override
    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    @Override
    public Set<UUID> getKills() {
        return playerKills;
    }

    @Override
    public Location getPlayerLocation() {
        Player player = getPlayer();
        if (player == null)
            return lastKnownLocation;
        lastKnownLocation = player.getLocation();
        return lastKnownLocation;
    }

    @Override
    public void setPlayerLocation(Location playerLocation) {
        this.lastKnownLocation = playerLocation;
    }

    @Override
    public RValue getPlayerInfos() {
        return playerInfos;
    }

}
