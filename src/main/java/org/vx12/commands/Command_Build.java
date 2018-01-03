package org.vx12.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.vx12.ffa.FFA;

import java.util.ArrayList;

public class Command_Build implements CommandExecutor {

    private FFA plugin;
    public Command_Build(FFA plugin) {
        this.plugin = plugin;
    }



    public static ArrayList<Player> buildingPlayers = new ArrayList<Player>();


    public boolean onCommand(CommandSender sender, Command command, String string, String[] args) {





        if (sender instanceof Player) {




            String prefix = plugin.getConfig().getString("Config.prefix");
            String noPerm = plugin.getConfig().getString("Config.noPermMessage");
            String enter = plugin.getConfig().getString("Config.enterBuildMessage");
            String exit = plugin.getConfig().getString("Config.exitBuildMessage");

            Player p = (Player) sender;
            if(p.hasPermission("ffa.build") || p.isOp()){


                if (!buildingPlayers.contains(p)) {
                    buildingPlayers.add(p);
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + enter));
                } else {
                    if (buildingPlayers.contains(p)) {
                        buildingPlayers.remove(p);
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + exit));
                    }

                }

            }
             else if (!(p.hasPermission("ffa.build")) || p.hasPermission("ffa.admin")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + noPerm));
            }

            }else{
                sender.sendMessage("You are not allowed to execute that command!");
                return false;
            }


            return false;
        }
    }
