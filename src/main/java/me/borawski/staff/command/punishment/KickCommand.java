package me.borawski.staff.command.punishment;

import me.borawski.staff.Core;
import me.borawski.staff.command.Command;
import me.borawski.staff.data.punishment.Kick;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;

public class KickCommand implements Command {

    @Override
    public String getPermission() {
        return "hexcore.kick";
    }

    @Override
    public String getPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "STAFF | " + ChatColor.RESET + "" + ChatColor.GRAY;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"kick"};
    }

    @Override
    public void run(CommandSender sender, String[] args) {
        if(args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Invalid arguments. /kick <user>");
            return;
        }

        String target = args[0];
        if(Core.getInstance().getProxy().getPlayer(target) != null) {
            ProxiedPlayer targetPlayer = Core.getInstance().getProxy().getPlayer(target);

            Kick kick = new Kick();

            kick.setDate(System.currentTimeMillis());

            kick.setTarget(Core.getInstance().getProxy().getPlayer(target).getUniqueId());
            kick.setPunisher(targetPlayer.getUniqueId());
            kick.setPardoned(true);
            kick.setEvidence(new ArrayList<String>() {
                {
                    this.add("[Evidence for kick goes here]");
                }
            });

            Core.getInstance().getPunishmentManager().savePunishment(kick);

            kick.action(targetPlayer.getUniqueId());
        }

    }
}
