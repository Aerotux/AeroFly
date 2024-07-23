package net.aerotux.aerofly.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.bukkit.contexts.OnlinePlayer;
import net.aerotux.aerofly.AeroFly;
import net.aerotux.aerofly.util.CC;
import net.aerotux.aerofly.util.YamlManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.Yaml;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@CommandAlias("fly")
@CommandPermission("aerofly.use")
public class FlyCommand extends BaseCommand {

    private static Set<Player> flyingPlayers = new HashSet<>();
    private static AeroFly instance = AeroFly.getInstance();
    private static YamlManager yamlManager = new YamlManager(instance, "flyingplayers.yml");

    @Default
    @CommandCompletion("@players @empty")
    public void onFly(Player player, @Optional OnlinePlayer onlineTarget) {

        Player target = onlineTarget.player;
        if (target == null) {
            toggleFlight(player, player);
        } else {
            if (player.hasPermission("aerofly.admin")) {
                toggleFlight(target.getPlayer(), target.getPlayer());
                player.sendMessage(CC.translate("&aYou toggled flight for " + target.getName()));
                target.getPlayer().sendMessage(CC.translate("&aYour flight was toggled by " + player.getName()));
            } else {
                player.sendMessage(CC.translate("&cYou don't have permission to run this command!"));
            }
        }
    }

    private void toggleFlight(Player executor, Player target) {
        if (flyingPlayers.contains(target)) {
            flyingPlayers.remove(target);
            target.setAllowFlight(false);
            target.setFlying(false);
            if (executor != target) {
                target.sendMessage(CC.translate("&cFlight Disabled by " + executor.getName()));
                executor.sendMessage(CC.translate("&aYou disabled flight for " + target.getName()));
            } else {
                executor.sendMessage(CC.translate("&cFlight Disabled"));
            }
        } else {
            flyingPlayers.add(target);
            target.setAllowFlight(true);
            target.setFlying(true);
            if (executor != target) {
                target.sendMessage(CC.translate("&aFlight Enabled by " + executor.getName()));
                executor.sendMessage(CC.translate("&aYou enabled flight for " + target.getName()));
            } else {
                executor.sendMessage(CC.translate("&aFlight Enabled"));
            }
        }
    }

}
