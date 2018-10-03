package me.borawski.staff.command;

import me.borawski.staff.Core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private Core instance;
    private Map<String, Command> commandMap;

    public CommandHandler(Core instance) {
        this.instance = instance;
        this.commandMap = new HashMap<>();
    }

    public Core getInstance() {
        return instance;
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public void add(String name, Command command) {
        getCommandMap().put(name, command);
    }

    public void remove(String name) {
        getCommandMap().remove(name);
    }

    public void registerCommands() {
        getCommandMap().forEach((k, v) -> {
            getInstance().getProxy().getPluginManager().registerCommand(getInstance(), new net.md_5.bungee.api.plugin.Command(k, v.getPermission(), v.getAliases()) {
                @Override
                public void execute(CommandSender commandSender, String[] args) {
                    if(!commandSender.hasPermission(v.getPermission())) {
                        commandSender.sendMessage(v.getPrefix() + "" + ChatColor.RED + "You don't have the permission to do that!");
                    }

                    v.run(commandSender, args);
                }
            });
        });
    }

}
