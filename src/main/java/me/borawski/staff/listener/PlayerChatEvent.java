package me.borawski.staff.listener;

import me.borawski.staff.Core;
import me.borawski.staff.data.punishment.Mute;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerChatEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent event) {
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if(Core.getInstance().getPunishmentManager().getActiveMutes().containsKey(player.getUniqueId())) {
            Mute mute = Core.getInstance().getPunishmentManager().getActiveMutes().get(player.getUniqueId());
            if(System.currentTimeMillis() >= mute.getUntil()) {
                event.setCancelled(true);

                mute.setPardoned(true);
                player.sendMessage(ChatColor.GREEN + "You have been un-muted!");
                Core.getInstance().getPunishmentManager().getActiveMutes().remove(player.getUniqueId());

                player.chat(event.getMessage());
                return;

            } else {
                if(event.getMessage().startsWith("/")) {
                    return;
                }

                event.setCancelled(true);

                String prefix = ChatColor.DARK_RED + "" + ChatColor.BOLD + "STAFF | " + ChatColor.RESET + "" + ChatColor.GRAY;
                player.sendMessage(prefix + "You are " + ChatColor.YELLOW + "muted" + ChatColor.GRAY + "!");
                player.sendMessage(prefix + ChatColor.GREEN + "Reason: " + ChatColor.YELLOW + mute.getReason());
                player.sendMessage(prefix + ChatColor.GREEN + "Time: " + ChatColor.YELLOW + mute.getDate());
                player.sendMessage(prefix + ChatColor.GREEN + "Until: " + ChatColor.YELLOW + (mute.isPermanent()?"Forever":mute.getUntil()));
                //player.sendMessage(prefix + ChatColor.GREEN + "Issuer: " + ChatColor.YELLOW + mute.getPunisher());
                return;

            }

        }

        if (Core.getInstance().getStaffChat().contains(player.getUniqueId())) {
            if(event.getMessage().startsWith("/")) {
                return;
            }

            event.setCancelled(true);
            Core.getInstance().getProxy().getPlayers().forEach((s) -> {
                if(!s.hasPermission("hexcore.staff")) {
                    return;
                }

                s.sendMessage(
                        Core.getInstance().getCommandHandler().getCommandMap().get("staffchat").getPrefix()
                                + ChatColor.RED + player.getName() + "" + ChatColor.DARK_GRAY + " ➡ " + ChatColor.GRAY
                                + event.getMessage()
                );
            });

        }
    }

}
