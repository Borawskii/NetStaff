package me.borawski.staff.command.staff;

import me.borawski.staff.Core;
import me.borawski.staff.command.Command;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class StaffChatCommand implements Command {

    @Override
    public String getPermission() {
        return "hexcore.staff";
    }

    @Override
    public String getPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "STAFF | " + ChatColor.RESET;
    }

    @Override
    public String[] getAliases() {
        return new String[] {"s", "st"};
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;

            if(!Core.getInstance().getStaffChat().contains(player.getUniqueId())) {
                player.sendMessage(getPrefix() + ChatColor.GRAY + "Staff chat has been " + ChatColor.GREEN + "enabled" + ChatColor.GRAY + ".");
                Core.getInstance().getStaffChat().add(player.getUniqueId());

            } else {
                player.sendMessage(getPrefix() + ChatColor.GRAY + "Staff chat has been " + ChatColor.RED + "disabled" + ChatColor.GRAY + ".");
                Core.getInstance().getStaffChat().remove(player.getUniqueId());

            }
        }
    }
}
