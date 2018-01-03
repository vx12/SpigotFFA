package org.vx12.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.vx12.commands.Command_Build;
import org.vx12.ffa.FFA;

public class Event_PlayerPlaceBreak implements Listener{

    //TODO leave this for later0
    private FFA plugin;
    public Event_PlayerPlaceBreak(FFA plugin){
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        Player p = e.getPlayer();
        if(Command_Build.buildingPlayers.contains(p)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        if(Command_Build.buildingPlayers.contains(p)){
            e.setCancelled(false);
        }else{
            e.setCancelled(true);
        }
    }


}
