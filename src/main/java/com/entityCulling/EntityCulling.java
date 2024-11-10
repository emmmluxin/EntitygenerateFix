package com.entityCulling;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public final class EntityCulling extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new OreBlockListener(), this);
        getServer().getPluginManager().registerEvents(new ArmorStandLimitListener(), this);
        getLogger().info("EntityCulling enabled!");

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
