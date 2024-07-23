package net.aerotux.aerofly.util;

import net.aerotux.aerofly.AeroFly;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YamlManager {

    private final AeroFly plugin;
    private final File file;
    private final FileConfiguration config;

    public YamlManager(AeroFly plugin, String fileName) {
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        this.config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void saveConfig() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setVanishedPlayers(List<String> players) {
        config.set("flyingPlayers", players);
        saveConfig();
    }

    public List<String> getVanishedPlayers() {
        return config.getStringList("flyingPlayers");
    }

}
