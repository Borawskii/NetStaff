package me.borawski.staff.command.staff;

import me.borawski.staff.Core;
import me.borawski.staff.command.Command;
import me.borawski.staff.data.auth.StaffKeyManager;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class StaffPanelCommand implements Command {
    @Override
    public String getPermission() {
        return "hexcore.login.panel";
    }

    @Override
    public String getPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "STAFF | " + ChatColor.RESET;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"l"};
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if(args.length == 1) {
            if (args[0].equalsIgnoreCase("staff")) {
                if(sender instanceof ProxiedPlayer) {
                    ProxiedPlayer player = (ProxiedPlayer) sender;
                    StaffKeyManager keyManager = Core.getInstance().getStaffKeys();
                    if(!keyManager.hasKey(player.getUniqueId())) {
                        player.sendMessage(getPrefix() + ChatColor.GREEN + "Generating new staff-panel registration key...");
                        String key = keyManager.createKey(player.getUniqueId());
                        player.sendMessage(getPrefix() + ChatColor.GREEN + player.getName() + "'s staff-panel key: " + ChatColor.YELLOW + key);

                    } else if (keyManager.hasKey(player.getUniqueId()) && !keyManager.hasUsed(player.getUniqueId())){
                        player.sendMessage(getPrefix() + ChatColor.RED + "You have a staff-panel key but have not used it yet!");
                        player.sendMessage(getPrefix() + ChatColor.GREEN + player.getName() + "'s staff-panel key: " + ChatColor.YELLOW + keyManager.getKey(player.getUniqueId()));

                    } else if(keyManager.hasUsed(player.getUniqueId())) {
                        player.sendMessage(getPrefix() + ChatColor.RED + "You have already used your staff-panel registration key");
                        player.sendMessage(getPrefix() + ChatColor.GRAY + "If you need to re-register, speak with an admin.");
                    } else {
                        player.sendMessage(getPrefix() + ChatColor.RED + "Error with the authentication engine");
                    }

                }

            }
        }
    }
}
