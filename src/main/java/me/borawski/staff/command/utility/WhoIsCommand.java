package me.borawski.staff.command.utility;

import me.borawski.staff.Core;
import me.borawski.staff.command.Command;
import me.borawski.staff.data.punishment.Mute;
import me.borawski.staff.data.punishment.Punishment;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.User;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.List;

public class WhoIsCommand implements Command {

    @Override
    public String getPermission() {
        return "hexcore.whois";
    }

    @Override
    public String getPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "STAFF | " + ChatColor.RESET + "" + ChatColor.GREEN;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"whois"};
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if(args.length > 1 || args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Invalid arguments. /find <user>");
            return;
        }

        ProxiedPlayer target = Core.getInstance().getProxy().getPlayer(args[0]);
        String location = target == null ? ChatColor.RED + "n/a" : ChatColor.YELLOW + target.getServer().getInfo().getName();

        if(target == null) {
            sender.sendMessage(ChatColor.RED + "Player can't be found");
            return;
        }

        LuckPermsApi api = LuckPerms.getApi();
        User user = api.getUser(target.getUniqueId());
        final boolean[] currentlyMuted = {false};

        List<Punishment> punishments = (List<Punishment>) Core.getInstance().getPunishmentManager().getPunishmentHistory(target.getUniqueId());
        punishments.forEach((p) -> {
            if(p.getType().equalsIgnoreCase("mute")) {
                if(!p.isPardoned()) {
                    currentlyMuted[0] = true;
                }
            }
            // TODO: Check if punishment is a ban
        });

        sender.sendMessage(getPrefix() + "Whois for player: " + ChatColor.YELLOW + args[0]);
        sender.sendMessage(getPrefix() + "UUID: " + ChatColor.YELLOW + target.getUniqueId());
        sender.sendMessage(getPrefix() + "Banned: " + ChatColor.YELLOW + "(N/A)");
        sender.sendMessage(getPrefix() + "Muted: " + ChatColor.YELLOW + currentlyMuted[0]);
        sender.sendMessage(getPrefix() + "Online: " + ChatColor.YELLOW + (target.isConnected() ? "True":"False"));
        sender.sendMessage(getPrefix() + "Rank: " + ChatColor.YELLOW + user.getPrimaryGroup());
        sender.sendMessage(getPrefix() + "Where is: " + location);
    }
}
