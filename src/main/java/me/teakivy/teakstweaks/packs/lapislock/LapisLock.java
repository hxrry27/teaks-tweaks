package me.teakivy.teakstweaks.packs.lapislock;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.utils.Key;
import me.teakivy.teakstweaks.utils.register.TTPack;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.EnchantingTable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class LapisLock extends BasePack {

    public LapisLock() {
        super(TTPack.LAPIS_LOCK, Material.LAPIS_LAZULI);
    }

    @EventHandler
    public void onGUIClose(InventoryCloseEvent event) {
        if (!(event.getInventory() instanceof EnchantingInventory inventory)) return;

        EnchantingTable enchantingTable = getEnchantingTable(inventory);
        if (enchantingTable == null) return;

        ItemStack lapis = inventory.getSecondary();
        if (lapis == null || lapis.getType() != Material.LAPIS_LAZULI) return;

        PersistentDataContainer data = enchantingTable.getPersistentDataContainer();
        int stored = data.getOrDefault(Key.get("lapis_count"), PersistentDataType.INTEGER, 0);
        int toStore = Math.min(lapis.getAmount(), Material.LAPIS_LAZULI.getMaxStackSize() - stored);
        if (toStore <= 0) return;

        lapis.setAmount(lapis.getAmount() - toStore);
        inventory.setSecondary(lapis.getAmount() > 0 ? lapis : null);

        data.set(Key.get("lapis_count"), PersistentDataType.INTEGER, stored + toStore);
        enchantingTable.update();
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onGUIOpen(InventoryOpenEvent event) {
        if (!(event.getInventory() instanceof EnchantingInventory inventory)) return;
        EnchantingTable enchantingTable = getEnchantingTable(inventory);
        if (enchantingTable == null) return;

        PersistentDataContainer data = enchantingTable.getPersistentDataContainer();
        int lapisCount = data.getOrDefault(Key.get("lapis_count"), PersistentDataType.INTEGER, 0);
        if (lapisCount <= 0) return;

        inventory.setSecondary(new ItemStack(Material.LAPIS_LAZULI, lapisCount));
        data.remove(Key.get("lapis_count"));
        enchantingTable.update();
    }

    @EventHandler(ignoreCancelled = true)
    public void onBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        if (block.getType() != Material.ENCHANTING_TABLE) return;

        EnchantingTable enchantingTable = (EnchantingTable) block.getState();

        PersistentDataContainer data = enchantingTable.getPersistentDataContainer();
        int lapisCount = data.getOrDefault(Key.get("lapis_count"), PersistentDataType.INTEGER, 0);
        if (lapisCount <= 0) return;

        block.getWorld().dropItemNaturally(
                block.getLocation().add(0.5, 0.5, 0.5),
                new ItemStack(Material.LAPIS_LAZULI, lapisCount)
        );

        data.remove(Key.get("lapis_count"));
        enchantingTable.update();
    }

    private EnchantingTable getEnchantingTable(Inventory inventory) {
        Location location = inventory.getLocation();
        if (location == null) return null;
        Block block = location.getBlock();
        if (block.getType() != Material.ENCHANTING_TABLE) return null;
        return (EnchantingTable) block.getState();
    }

}
