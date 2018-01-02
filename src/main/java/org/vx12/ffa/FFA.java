package org.vx12.ffa;

import org.bukkit.plugin.java.JavaPlugin;

public class FFA extends JavaPlugin{

    @Override
    public void onEnable() {
        getLogger().info("FFA system enabled");
    }


    @Override
    public void onDisable() {

        getLogger().info("FFA system disabled");


    }
}
