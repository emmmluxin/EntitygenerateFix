package com.entityCulling;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ArmorStandLimitListener implements Listener {
    private Map<String, Integer> armorStandLimitMap = new HashMap<>();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (Material.ARMOR_STAND != event.getBlockPlaced().getType()) {
            return;
        }
        Block block = event.getBlockPlaced();
        String chunkKey = block.getChunk().toString();

        int armorStandCount = armorStandLimitMap.getOrDefault(chunkKey, 0);

        if (armorStandCount >= 200) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            player.sendMessage("under the top!!! too many armor!");
        } else {
            armorStandLimitMap.put(chunkKey, armorStandCount + 1);
        }
    }

    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.ARMOR_STAND) {
            ArmorStand armorStand = (ArmorStand) event.getEntity();
            String chunkKey = armorStand.getLocation().getChunk().toString();

            int armorStandCount = armorStandLimitMap.getOrDefault(chunkKey, 0);

            if (armorStandCount >= 200) {
                event.setCancelled(true);
            } else {
                armorStandLimitMap.put(chunkKey, armorStandCount + 1);
            }
        }
    }
}
