package com.entityCulling;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class OreBlockListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // check right click
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            Block clickedBlock = event.getClickedBlock();

            if (clickedBlock.getType() == Material.LAPIS_ORE) {
                ItemStack originalItem = player.getInventory().getItemInOffHand();
                ItemStack clonedItem = originalItem.clone();
                event.getPlayer().getInventory().addItem(clonedItem);


                if (!player.getInventory().addItem(originalItem).isEmpty()) {
                    player.sendMessage("Invisfull");
                }
            }
        }
    }
}
