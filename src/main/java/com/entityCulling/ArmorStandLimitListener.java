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
            player.sendMessage("这个区块内已经达到盔甲架的放置数量上限，请检查粘液机器是否过于密集！");
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
                // 这里可以添加日志或进一步处理
            } else {
                armorStandLimitMap.put(chunkKey, armorStandCount + 1);
            }
        }
    }
}