package me.teakivy.teakstweaks.utils.register;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.anticreepergrief.AntiCreeperGrief;
import me.teakivy.teakstweaks.packs.antiendermangrief.AntiEndermanGrief;
import me.teakivy.teakstweaks.packs.antighastgrief.AntiGhastGrief;
import me.teakivy.teakstweaks.packs.armoredelytra.ArmoredElytra;
import me.teakivy.teakstweaks.packs.cauldronconcrete.CauldronConcrete;
import me.teakivy.teakstweaks.packs.cauldroncopper.CauldronCopper;
import me.teakivy.teakstweaks.packs.cauldronmud.CauldronMud;
import me.teakivy.teakstweaks.packs.cauldronpotions.CauldronPotions;
import me.teakivy.teakstweaks.packs.collectibleblocks.CollectibleBlocks;
import me.teakivy.teakstweaks.packs.confetticreepers.ConfettiCreepers;
import me.teakivy.teakstweaks.packs.countmobdeaths.CountMobDeaths;
import me.teakivy.teakstweaks.packs.customnetherportals.CustomNetherPortals;
import me.teakivy.teakstweaks.packs.disposal.Disposal;
import me.teakivy.teakstweaks.packs.doubleshulkershells.DoubleShulkerShells;
import me.teakivy.teakstweaks.packs.easieraxolotlbreeding.EasierAxolotlBreeding;
import me.teakivy.teakstweaks.packs.easiernametags.EasierNametags;
import me.teakivy.teakstweaks.packs.fastleafdecay.FastLeafDecay;
import me.teakivy.teakstweaks.packs.fixeditemframes.FixedItemFrames;
import me.teakivy.teakstweaks.packs.graves.Graves;
import me.teakivy.teakstweaks.packs.huskdropssand.HuskDropsSand;
import me.teakivy.teakstweaks.packs.invisibleitemframes.InvisibleItemFrames;
import me.teakivy.teakstweaks.packs.killboats.KillBoats;
import me.teakivy.teakstweaks.packs.largerphantoms.LargerPhantoms;
import me.teakivy.teakstweaks.packs.miniblocks.MiniBlocks;
import me.teakivy.teakstweaks.packs.moremobheads.MoreMobHeads;
import me.teakivy.teakstweaks.packs.musicdiscengraver.MusicDiscEngraver;
import me.teakivy.teakstweaks.packs.netherportalcoords.NetherPortalCoords;
import me.teakivy.teakstweaks.packs.paintingpicker.PaintingPicker;
import me.teakivy.teakstweaks.packs.playerheaddrops.PlayerHeadDrops;
import me.teakivy.teakstweaks.packs.rotationwrench.RotationWrench;
import me.teakivy.teakstweaks.packs.silencemobs.SilenceMobs;
import me.teakivy.teakstweaks.packs.spectatorconduitpower.SpectatorConduitPower;
import me.teakivy.teakstweaks.packs.spectatornightvision.SpectatorNightVision;
import me.teakivy.teakstweaks.packs.stairchairs.StairChairs;
import me.teakivy.teakstweaks.packs.stormchanneling.StormChanneling;
import me.teakivy.teakstweaks.packs.trackrawstatistics.TrackRawStatistics;
import me.teakivy.teakstweaks.packs.trackstatistics.TrackStatistics;
import me.teakivy.teakstweaks.packs.transferablepets.TransferablePets;
import me.teakivy.teakstweaks.packs.unlockallrecipes.UnlockAllRecipes;
import me.teakivy.teakstweaks.packs.villagerdeathmessages.VillagerDeathMessages;
import me.teakivy.teakstweaks.packs.workstationhighlights.WorkstationHighlights;
import me.teakivy.teakstweaks.packs.xpmanagement.XPManagement;
import me.teakivy.teakstweaks.utils.config.Config;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;

public enum TTPack {
    ANTI_CREEPER_GRIEF("anti-creeper-grief", AntiCreeperGrief.class),
    ANTI_ENDERMAN_GRIEF("anti-enderman-grief", AntiEndermanGrief.class),
    ANTI_GHAST_GRIEF("anti-ghast-grief", AntiGhastGrief.class),
    ARMORED_ELYTRA("armored-elytra", ArmoredElytra.class),
    CAULDRON_CONCRETE("cauldron-concrete", CauldronConcrete.class),
    CAULDRON_COPPER("cauldron-copper", CauldronCopper.class),
    CAULDRON_MUD("cauldron-mud", CauldronMud.class),
    CAULDRON_POTIONS("cauldron-potions", CauldronPotions.class),
    COLLECTIBLE_BLOCKS("collectible-blocks", CollectibleBlocks.class),
    CONFETTI_CREEPERS("confetti-creepers", ConfettiCreepers.class),
    COUNT_MOB_DEATHS("count-mob-deaths", CountMobDeaths.class),
    CUSTOM_NETHER_PORTALS("custom-nether-portals", CustomNetherPortals.class),
    DISPOSAL("disposal", Disposal.class),
    DOUBLE_SHULKER_SHELLS("double-shulker-shells", DoubleShulkerShells.class),
    EASIER_AXOLOTL_BREEDING("easier-axolotl-breeding", EasierAxolotlBreeding.class),
    EASIER_NAMETAGS("easier-nametags", EasierNametags.class),
    FAST_LEAF_DECAY("fast-leaf-decay", FastLeafDecay.class),
    FIXED_ITEM_FRAMES("fixed-item-frames", FixedItemFrames.class),
    GRAVES("graves", Graves.class),
    HUSK_DROPS_SAND("husk-drops-sand", HuskDropsSand.class),
    INVISIBLE_ITEM_FRAMES("invisible-item-frames", InvisibleItemFrames.class),
    KILL_BOATS("kill-boats", KillBoats.class),
    LARGER_PHANTOMS("larger-phantoms", LargerPhantoms.class),
    MINI_BLOCKS("mini-blocks", MiniBlocks.class),
    MORE_MOB_HEADS("more-mob-heads", MoreMobHeads.class),
    MUSIC_DISC_ENGRAVER("music-disc-engraver", MusicDiscEngraver.class),
    NETHER_PORTAL_COORDS("nether-portal-coords", NetherPortalCoords.class),
    PAINTING_PICKER("painting-picker", PaintingPicker.class),
    PLAYER_HEAD_DROPS("player-head-drops", PlayerHeadDrops.class),
    ROTATION_WRENCH("rotation-wrench", RotationWrench.class),
    SILENCE_MOBS("silence-mobs", SilenceMobs.class),
    SPECTATOR_CONDUIT_POWER("spectator-conduit-power", SpectatorConduitPower.class),
    SPECTATOR_NIGHT_VISION("spectator-night-vision", SpectatorNightVision.class),
    STAIR_CHAIRS("stair-chairs", StairChairs.class),
    STORM_CHANNELING("storm-channeling", StormChanneling.class),
    TRACK_RAW_STATISTICS("track-raw-statistics", TrackRawStatistics.class),
    TRACK_STATISTICS("track-statistics", TrackStatistics.class),
    TRANSFERABLE_PETS("transferable-pets", TransferablePets.class),
    UNLOCK_ALL_RECIPES("unlock-all-recipes", UnlockAllRecipes.class),
    VILLAGER_DEATH_MESSAGES("villager-death-messages", VillagerDeathMessages.class),
    WORKSTATION_HIGHLIGHTS("workstation-highlights", WorkstationHighlights.class),
    XP_MANAGEMENT("xp-management", XPManagement.class);

    private final String key;
    private final Class<? extends BasePack> clazz;
    private BasePack pack;

    TTPack(String key, Class<? extends BasePack> clazz) {
        this.key = key;
        this.clazz = clazz;
    }

    public void instantiate() {
        try {
            Constructor<? extends BasePack> constructor = clazz.getConstructor();
            this.pack = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to instantiate pack: " + name(), e);
        }
    }

    public String getKey() {
        return key;
    }

    public BasePack getPack() {
        return pack;
    }

    public static TTPack fromKey(String key) {
        for (TTPack pack : values()) {
            if (pack.getKey().equalsIgnoreCase(key)) {
                return pack;
            }
        }
        return null;
    }

    public ConfigurationSection getConfig() {
        return getPack().getConfig();
    }

    public void register() {
        if (getPack().isRegistered()) return;
        if (!isEnabled() && !Config.isDevMode()) return;
        getPack().init();
    }

    public boolean isEnabled() {
        return getConfig().getBoolean("enabled", false);
    }

    public ItemStack getItem() {
        return getPack().getItem();
    }

    @Override
    public String toString() {
        return key;
    }
}

