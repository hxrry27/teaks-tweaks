package me.teakivy.teakstweaks.utils.update;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class VersionManager {
    private static final List<Version> versions = new ArrayList<>();
    private static final HttpClient HTTP = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public static void init() {
        versions.clear();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.modrinth.com/v2/project/Xdn5t532/version"))
                    .GET()
                    .build();
            HttpResponse<String> response = HTTP.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray array = JsonParser.parseString(response.body()).getAsJsonArray();
            for (JsonElement element : array) {
                versions.add(parseVersion(element.getAsJsonObject()));
            }
        } catch (Exception ignored) {}
    }

    private static Version parseVersion(JsonObject obj) {
        String version = obj.get("version_number").getAsString();
        String id = obj.get("id").getAsString();
        List<String> supportedMCVersions = new ArrayList<>();
        JsonArray supportedVersions = obj.getAsJsonArray("game_versions");
        for (JsonElement supportedVersion : supportedVersions) {
            supportedMCVersions.add(supportedVersion.getAsString());
        }
        return new Version(version, supportedMCVersions, id);
    }

    public static List<Version> getVersions() {
        return versions;
    }

    public static Version getBestVersion() {
        List<Version> supportedVersions = new ArrayList<>();
        for (Version version : versions) {
            if (version.isSupported()) supportedVersions.add(version);
        }

        if (supportedVersions.isEmpty()) return null;

        Version bestVersion = supportedVersions.getFirst();
        for (Version version : supportedVersions) {
            if (!bestVersion.isNewerThan(version)) bestVersion = version;
        }

        return bestVersion;
    }
}
