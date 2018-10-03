package me.borawski.staff.command;

import net.md_5.bungee.api.CommandSender;

public interface Command {
    String getPermission();

    String getPrefix();

    String[] getAliases();

    void run(CommandSender sender, String[] args);

}
