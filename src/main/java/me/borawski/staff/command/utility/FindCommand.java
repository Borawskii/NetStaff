package me.borawski.staff.command.utility;

import me.borawski.staff.Core;
import me.borawski.staff.command.Command;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class FindCommand implements Command {

    @Override
    public String getPermission() {
        return "hexcore.find";
    }

    @Override
    public String getPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "STAFF | " + ChatColor.GREEN;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"find"};
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if(args.length > 1 || args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Invalid arguments. /find <user>");
            return;
        }

        ProxiedPlayer target = Core.getInstance().getProxy().getPlayer(args[0]);
        String location = target == null ? ChatColor.RED + "n/a" : ChatColor.YELLOW + target.getServer().getInfo().getName();

        sender.sendMessage(getPrefix() + "Queried player: " + ChatColor.YELLOW + args[0]);
        sender.sendMessage(getPrefix() + "Server: " + location);
    }
}
