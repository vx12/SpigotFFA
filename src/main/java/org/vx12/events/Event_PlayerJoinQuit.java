package org.vx12.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.vx12.commands.Command_Spectate;
import org.vx12.ffa.FFA;

public class Event_PlayerJoinQuit implements Listener{

    private FFA plugin;
    public Event_PlayerJoinQuit(FFA plugin) {
        this.plugin = plugin;
    }

    //TODO Testing. Not sure if working correctly
    @EventHandler
    public void onJoin(PlayerJoinEvent ev){
        Player p = ev.getPlayer();

        String msg = plugin.getConfig().getString("Config.joinMessage");
        msg = msg.replace("[player]", p.getName());
        String prefix = plugin.getConfig().getString("Config.prefix");
        String kickSpec = plugin.getConfig().getString("Config.kickedFromSpec");
        this.plugin.saveConfig();



        ev.setJoinMessage(ChatColor.translateAlternateColorCodes('&', prefix + msg));
        p.setGameMode(GameMode.ADVENTURE);
        if(Command_Spectate.hidden.contains(p)){
            Command_Spectate.hidden.remove(p);
            p.setFlying(false);
            p.setAllowFlight(false);

            if(p.hasPermission("ffa.spectate") || p.hasPermission("ffa.admin")){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + kickSpec));
            }
        }

        for(Player all : Bukkit.getOnlinePlayers()){
            all.showPlayer(p);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent ev){
        Player p = ev.getPlayer();
        String msg = plugin.getConfig().getString("Config.quitMessage");
        String prefix = plugin.getConfig().getString("Config.prefix");
        msg = msg.replace("[player]", p.getName());
        ev.setQuitMessage(ChatColor.translateAlternateColorCodes('&', prefix + msg));
        this.plugin.saveConfig();
        if(Command_Spectate.hidden.contains(p)){
            Command_Spectate.hidden.remove(p);

        }
        p.setFlying(false);
        p.setAllowFlight(false);
    }


}
