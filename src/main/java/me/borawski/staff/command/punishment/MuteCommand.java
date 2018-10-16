package me.borawski.staff.command.punishment;

import me.borawski.staff.Core;
import me.borawski.staff.command.Command;
import me.borawski.staff.data.punishment.Mute;
import me.borawski.staff.data.punishment.Punishment;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.ArrayList;
import java.util.List;

public class MuteCommand implements Command {
    @Override
    public String getPermission() {
        return "hexcore.mute";
    }

    @Override
    public String getPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "STAFF | " + ChatColor.RESET + "" + ChatColor.GRAY;
    }

    @Override
    public String[] getAliases() {
        return new String[]{"mute"};
    }

    /**
     * /mute <player> [-p, length("1D, 1h, 30m, etc...")] {reason}
     * @param sender
     * @param args
     */
    @Override
    public void run(CommandSender sender, String[] args) {
        if(args.length <= 2) {
            sender.sendMessage(getPrefix() + ChatColor.RED + "/mute <player> [-p, length(\"1D, 1h, 30m, etc...\")] {reason}");
            return;
        }

        String user = args[0];
        final boolean[] currentlyMuted = {false};

        if(Core.getInstance().getProxy().getPlayer(user) == null) {
            sender.sendMessage(ChatColor.RED + "Player is not online!");
            return;
        }

        List<Punishment> punishments = (List<Punishment>) Core.getInstance().getPunishmentManager().getPunishmentHistory(Core.getInstance().getProxy().getPlayer(user).getUniqueId());
        punishments.forEach((p) -> {
            if(p.getType().equalsIgnoreCase("mute")) {
                if(!p.isPardoned()) {
                    currentlyMuted[0] = true;
                }
            }
        });

        if(currentlyMuted[0]) {
            sender.sendMessage(ChatColor.RED + "That player is currently muted!");
            return;
        }

        String until = args[1];

        Mute mute = new Mute();
        mute.setDate(System.currentTimeMillis());
        mute.setPunisher(((ProxiedPlayer)sender).getUniqueId());
        mute.setTarget(Core.getInstance().getProxy().getPlayer(user).getUniqueId());

        if (until.equalsIgnoreCase("-p")) {
            mute.setPermanent(true);

        } else if(until.endsWith("M") || until.endsWith("W") || until.endsWith("D") || until.endsWith("H") || until.endsWith("m")) {
            int length = until.length();
            int time = Integer.valueOf(until.substring(0, length-1));
            String unit = until.substring(length-1, length);

            long current = System.currentTimeMillis();
            long minute = 60000;
            switch(unit) {
                case "M":
                    current += minute * 60 * 24 * 7 * 4;
                    break;
                case "W":
                    current += minute * 60 * 24 * 7;
                    break;
                case "D":
                    current += minute * 60 * 24;
                    break;
                case "H":
                    current += minute * 60;
                    break;
                case "m":
                    current += minute;
                    break;
            }

            long total = time * current;

            mute.setPermanent(false);
            mute.setUntil(total);
        }

        StringBuilder reason = new StringBuilder();
        for(int i = 2; i < args.length; i++) {
            reason.append(args[i]);
        }

        mute.setReason(reason.toString());
        mute.setPardoned(false);

        mute.setEvidence(new ArrayList<String>() {
            {
                this.add("[Evidence for mute goes here]");
            }
        });

        Core.getInstance().getPunishmentManager().savePunishment(mute);
        mute.action(Core.getInstance().getProxy().getPlayer(user).getUniqueId());

    }
}
