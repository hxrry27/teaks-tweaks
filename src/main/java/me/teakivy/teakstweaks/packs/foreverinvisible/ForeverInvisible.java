package me.teakivy.teakstweaks.packs.foreverinvisible;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.permission.Permission;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import java.util.List;

public class ForeverInvisible extends BasePack {

    public ForeverInvisible() {
        super(TTPack.FOREVER_INVISIBLE, Material.MILK_BUCKET);
    }

    @EventHandler(ignoreCancelled = true)
    public void onClick(PlayerInteractEntityEvent event) {
        if (!Permission.FOREVER_INVISIBLE.check(event.getPlayer())) return;
        if (event.getRightClicked() instanceof Player) return;

        ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
        switch (item.getType()) {
            case POTION:
                handlePotionClick(event);
                break;
            case MILK_BUCKET:
                handleMilkClick(event);
                break;
        }
    }

    private void handlePotionClick(PlayerInteractEntityEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());

        PotionMeta meta = (PotionMeta) item.getItemMeta();
        if (meta == null) return;
        List<PotionEffect> effects = meta.getCustomEffects();
        if (!effects.isEmpty()) return;
        if (!meta.hasBasePotionType()) return;
        if (meta.getBasePotionType() != PotionType.INVISIBILITY && meta.getBasePotionType() != PotionType.LONG_INVISIBILITY) return;

        Entity entity = event.getRightClicked();
        if (entity.isInvisible()) return;
        entity.setInvisible(true);
        event.setCancelled(true);

        event.getPlayer().getInventory().setItem(event.getHand(), new ItemStack(Material.GLASS_BOTTLE));
    }

    private void handleMilkClick(PlayerInteractEntityEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
        Entity entity = event.getRightClicked();
        if (!entity.isInvisible()) return;
        entity.setInvisible(false);
        event.setCancelled(true);

        event.getPlayer().getInventory().setItem(event.getHand(), new ItemStack(Material.BUCKET));
    }
}
