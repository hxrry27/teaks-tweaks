package me.teakivy.teakstweaks.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class UUIDUtils {
    private static final ConcurrentHashMap<String, UUID> uuidCache = new ConcurrentHashMap<>();
    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static CompletableFuture<UUID> getUUIDAsync(String name) {
        UUID cached = uuidCache.get(name);
        if (cached != null) {
            return CompletableFuture.completedFuture(cached);
        }

        return CompletableFuture.supplyAsync(() -> {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.mojang.com/users/profiles/minecraft/" + name))
                        .GET()
                        .build();
                HttpResponse<String> response = HTTP.send(request, HttpResponse.BodyHandlers.ofString());
                JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
                String raw = json.get("id").getAsString();
                String dashed = raw.replaceAll("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5");
                UUID parsed = UUID.fromString(dashed);
                uuidCache.put(name, parsed);
                return parsed;
            } catch (Exception e) {
                throw new RuntimeException("Failed to resolve UUID for " + name, e);
            }
        });
    }

    public static String getPlayerTexture(UUID uuid) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonArray properties = json.getAsJsonArray("properties");
            return properties.get(0).getAsJsonObject().get("value").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static CompletableFuture<ItemStack> getPlayerHead(String name) {
        return getUUIDAsync(name).thenCompose(uuid -> getPlayerHead(uuid, name));
    }

    public static CompletableFuture<ItemStack> getPlayerHead(UUID uuid, String name) {
        return CompletableFuture.supplyAsync(() -> {
            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            PlayerProfile profile = Bukkit.createPlayerProfile(uuid, name);
            SkullMeta meta = (SkullMeta) head.getItemMeta();
            PlayerTextures textures = profile.getTextures();

            try {
                textures.setSkin(getURLFromTexture(getPlayerTexture(uuid)));
            } catch (MalformedURLException var8) {
                var8.printStackTrace();
            }

            meta.setOwnerProfile(profile);
            meta.setNoteBlockSound(Registry.SOUNDS.getKey(Sound.ENTITY_PLAYER_HURT));
            head.setItemMeta(meta);
            return head;
        });
    }

    private static URL getURLFromTexture(String texture) throws MalformedURLException {
        String decoded = new String(Base64.getDecoder().decode(texture));
        String skinString = decoded.split("\"SKIN\" : ")[1];
        return new URL(skinString.split("\"url\" : \"")[1].split("\"")[0]);
    }

}
