package dev.aurelium.auraskills.bukkit.commands;

import dev.aurelium.auraskills.bukkit.AuraSkills;
import dev.aurelium.auraskills.common.message.type.CommandMessage;
import org.bukkit.command.CommandSender;

import java.util.Locale;

public class ReloadExecutor {

    private final AuraSkills plugin;

    public ReloadExecutor(AuraSkills plugin) {
        this.plugin = plugin;
    }

    public void reload(CommandSender sender) {
        Locale locale = plugin.getLocale(sender);
        // Load config.yml file
        plugin.config().loadOptions();
        // Load messages
        plugin.getMessageProvider().loadMessages();
        // Load blocked/disabled worlds lists
        plugin.getWorldManager().loadWorlds(plugin.getConfig());
        // Load skills
        plugin.loadSkills();
        // Register default traits
        plugin.getTraitManager().registerTraitImplementations();

        plugin.getUiProvider().getBossBarManager().loadOptions();

        plugin.getRewardManager().loadRewards();
        plugin.getLootTableManager().loadLootTables();

        plugin.getLevelManager().registerLevelers();
        // Load menus
        plugin.getMenuFileManager().generateDefaultFiles();
        plugin.getMenuFileManager().loadMenus();

        plugin.getUiProvider().getActionBarManager().resetActionBars();

        sender.sendMessage(plugin.getPrefix(locale) + plugin.getMsg(CommandMessage.RELOAD, locale));
    }

}
