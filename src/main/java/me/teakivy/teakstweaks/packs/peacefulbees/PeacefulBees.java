package me.teakivy.teakstweaks.packs.peacefulbees;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class PeacefulBees extends BasePack {

    public PeacefulBees() {
        super(TTPack.PEACEFUL_BEES, Material.BEEHIVE);
    }

    /**
     * Stops bees from targeting players with the permission. Bees pick a target when they are hit,
     * when a nearby bee is hit, and when their hive or nest is broken, so this covers all three while
     * leaving the hit and the block break themselves untouched (kills, drops, tools and claims behave normally).
     */
    @EventHandler(ignoreCancelled = true)
    public void onTarget(EntityTargetLivingEntityEvent event) {
        if (!(event.getEntity() instanceof Bee bee)) return;
        if (!(event.getTarget() instanceof Player player)) return;
        if (!Permission.PEACEFUL_BEES.check(player)) return;

        event.setCancelled(true);
        bee.setAnger(0);
    }
}
