package me.borawski.staff.listener;

import me.borawski.staff.Core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerChatEvent implements Listener {

    @EventHandler
    public void onChat(ChatEvent event) {
        ProxiedPlayer player = (ProxiedPlayer) event.getSender();

        if (Core.getInstance().getStaffChat().contains(player.getUniqueId())) {
            event.setCancelled(true);
            Core.getInstance().getStaffChat().forEach((s) -> {
                Core.getInstance().getProxy().getPlayer(s).sendMessage(
                        Core.getInstance().getCommandHandler().getCommandMap().get("staffchat").getPrefix()
                                + ChatColor.RED + player.getName() + "" + ChatColor.DARK_GRAY + " ➡ " + ChatColor.GRAY
                                + event.getMessage()
                );
            });

        }
    }

}
