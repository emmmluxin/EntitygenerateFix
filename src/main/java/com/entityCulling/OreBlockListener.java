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
        // 检查事件的动作是否是右键点击方块
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            Block clickedBlock = event.getClickedBlock();

            // 检查被点击的方块是否是目标方块
            if (clickedBlock.getType() == Material.LAPIS_ORE) {
                ItemStack originalItem = player.getInventory().getItemInOffHand();
                ItemStack clonedItem = originalItem.clone();
                event.getPlayer().getInventory().addItem(clonedItem);


                // 尝试将物品添加到玩家的库存中
                if (!player.getInventory().addItem(originalItem).isEmpty()) {
                    // 如果添加失败（例如，因为库存已满），则可能需要向玩家发送一条消息
                    player.sendMessage("背包已满，无法添加物品。");
                }
            }
        }
    }
}