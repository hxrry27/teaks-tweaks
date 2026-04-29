package me.teakivy.teakstweaks.utils.permission;

import org.bukkit.command.CommandSender;

public enum Permission {
    COMMAND_CONDUITPOWER("command.conduitpower"),
    COMMAND_GRAVE("command.grave"),
    COMMAND_GRAVE_LOCATE("command.grave.locate"),
    COMMAND_GRAVE_KEY("command.grave.key", PermissionType.OP),
    COMMAND_GRAVE_UNINSTALL("command.grave.uninstall", PermissionType.OP),
    COMMAND_KILLBOATS("command.killboats", PermissionType.OP),
    COMMAND_MECHANICS("command.mechanics"),
    COMMAND_NIGHTVISION("command.nightvision"),
    COMMAND_PORTAL("command.portal"),
    COMMAND_DISPOSAL("command.disposal"),
    COMMAND_TEAKSTWEAKS("command.teakstweaks"),
    COMMAND_TEAKSTWEAKS_GIVE("command.teakstweaks.give", PermissionType.OP),
    COMMAND_WORKSTATIONHIGHLIGHT("command.workstationhighlight"),

    ARMORED_ELYTRA_CREATE("armored-elytra.create"),
    ARMORED_ELYTRA_SEPARATE("armored-elytra.separate"),
    CAULDRON_CONCRETE("cauldron-concrete"),
    CAULDRON_COPPER("cauldron-copper"),
    CAULDRON_MUD("cauldron-mud"),
    CAULDRON_POTIONS("cauldron-potions"),
    FIXED_ITEM_FRAMES("fixed-item-frames"),
    INVISIBLE_ITEM_FRAMES("invisible-item-frames"),
    ROTATION_WRENCH_REDSTONE("rotation-wrench.redstone"),
    ROTATION_WRENCH_TERRACOTTA("rotation-wrench.terracotta"),
    SILENCE_MOBS("silence-mobs"),
    STAIR_CHAIRS_CREATE("stair-chairs.create"),
    STAIR_CHAIRS_SIT("stair-chairs.sit"),
    UNLOCK_ALL_RECIPES("unlock-all-recipes"),
    XP_MANAGEMENT_BOTTLE("xp-management.bottle"),

    MANAGE("manage", PermissionType.OP),

    TEST("test", PermissionType.NONE);

    private final String permission;
    private final PermissionType type;

    Permission(String permission, PermissionType type) {
        this.permission = "teakstweaks." + permission;
        this.type = type;
    }
    Permission(String permission) {
        this.permission = "teakstweaks." + permission;
        this.type = PermissionType.ALL;
    }

    public String getPermission() {
        return permission;
    }

    public PermissionType getType() {
        return type;
    }

    public boolean check(CommandSender sender) {
        return sender.hasPermission(this.getPermission());
    }

    public static Permission get(String permission) {
        permission = PermissionManager.signPermission(permission);
        for (Permission p : values()) {
            if (p.getPermission().equals(permission)) return p;
        }
        return null;
    }
}

enum PermissionType {
    OP,
    NON_OP,
    ALL,
    NONE
}