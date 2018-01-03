package org.vx12.ffa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.vx12.commands.Command_Build;
import org.vx12.commands.Command_FFA;
import org.vx12.commands.Command_Spectate;
import org.vx12.events.Event_PlayerJoinQuit;
import org.vx12.events.Event_PlayerPlaceBreak;


public class FFA extends JavaPlugin{


    //TODO Adding Mapchange

    public static FFA instance;
    public static FFA getInstance(){
    return instance;
    }


    @Override
    public void onEnable() {
        getLogger().info("FFA system enabled");
        loadConfig();
        registerCommands();
        registerEvents();
    }


    @Override
    public void onDisable() {

        getLogger().info("FFA system disabled");
        this.saveConfig();

    }



    public void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new Event_PlayerJoinQuit(this), this);
        pm.registerEvents(new Event_PlayerPlaceBreak(this), this);
    }

    public void registerCommands(){
        getCommand("spectate").setExecutor(new Command_Spectate(this));
        getCommand("build").setExecutor(new Command_Build(this));
        getCommand("ffa").setExecutor(new Command_FFA(this));
    }


    public void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();

    }
}
