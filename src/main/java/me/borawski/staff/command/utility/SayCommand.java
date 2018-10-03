package me.borawski.staff.command.utility;

import me.borawski.staff.Core;
import me.borawski.staff.command.Command;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;

public class SayCommand implements Command {

    @Override
    public String getPermission() {
        return "hexcore.say";
    }

    @Override
    public String getPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "BROADCAST | " + ChatColor.RESET;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"say"};
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Invalid arguments. /Broadcast <message>");
            return;
        }

        StringBuilder message = new StringBuilder();
        for(String arg : args) {
            message.append(arg).append(" ");
        }

        Core.getInstance().getProxy().getPlayers().forEach((proxiedPlayer -> {
            proxiedPlayer.sendMessage(getPrefix() + ChatColor.RED + sender.getName() + ChatColor.DARK_GRAY + " ➡ " + ChatColor.GRAY + message.toString());
        }));
    }
}
