package net.aerotux.aerofly;

import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.aerotux.aerofly.command.FlyCommand;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("LombokGetterMayBeUsed")
public final class AeroFly extends JavaPlugin {

    @Getter private static AeroFly instance;

    private PaperCommandManager commandManager;

    public static AeroFly getInstance() {
        return instance;
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void onEnable() {

        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getLogger().info("Enabled!");
        getLogger().info("Join the Discord if you encounter issues!");

        registerCommands();

    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);

        commandManager.registerCommand(new FlyCommand());

    }

}
