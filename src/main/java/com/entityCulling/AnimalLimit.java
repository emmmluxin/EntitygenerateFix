package com.entityCulling;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

public class AnimalLimit {

        new BukkitRunnable() {
            @Override
            public void run() {
                for (int x = -16; x <= 16; x++) {
                    for (int z = -16; z <= 16; z++) {
                        Location location = new Location(Bukkit.getWorld("world"), x, 0, z);
                        int animalCount = 0;

                        // Calculate the number of animals in the block.
                        for (Entity entity : location.getChunk().getEntities()) {
                            if (entity instanceof LivingEntity && entity.getType().isAlive()) {
                                animalCount++;
                            }
                        }

                        // If there are more than 50, kill the excess animals.
                        if (animalCount > 50) {
                            int excess = animalCount - 50;
                            for (Entity entity : location.getChunk().getEntities()) {
                                if (excess <= 0) break;

                                if (entity instanceof LivingEntity && entity.getType().isAlive()) {
                                    entity.remove();
                                    excess--;
                                }
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0, 6000); // Check every 5 minutes.

    }


}

