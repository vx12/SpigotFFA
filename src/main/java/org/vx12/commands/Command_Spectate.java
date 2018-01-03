package org.vx12.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.vx12.ffa.FFA;

import java.util.ArrayList;

public class Command_Spectate implements CommandExecutor {

    private FFA plugin;

    public Command_Spectate(FFA plugin) {
        this.plugin = plugin;
    }


    public static ArrayList<Player> hidden = new ArrayList<Player>();

    public boolean onCommand(CommandSender sender, Command label, String string, String[] args) {

        if(sender instanceof Player){

            //TODO Rework the spectate system. Can be unstable at the moment!
            Player p = (Player)sender;

            String prefix = plugin.getConfig().getString("Config.prefix");
            String enterSpec = plugin.getConfig().getString("Config.enterSpecMode");
            String exitSpec = plugin.getConfig().getString("Config.exitSpecMode");
            String noPerm = plugin.getConfig().getString("Config.noPermMessage");

            if(!(p.hasPermission("ffa.spectate") || p.hasPermission("ffa.admin"))){

            }else {

                if(hidden.contains(p)){
                    hidden.remove(p);
                    for(Player all : Bukkit.getOnlinePlayers()){
                        all.showPlayer(p);
                    }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',prefix + exitSpec));

                } else {
                    if(!hidden.contains(p)){
                        hidden.add(p);
                        for(Player all : Bukkit.getOnlinePlayers()){
                            all.hidePlayer(p);
                        }

                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + enterSpec));

                    }
                }



            }




        } else {
            sender.sendMessage("You are not allowed to execute that command!");
            return false;
        }







        return false;
    }
}