package me.borawski.staff.data.punishment;

import me.borawski.staff.Core;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.mongodb.morphia.annotations.Property;

import java.util.UUID;

public class Mute extends Punishment {

    @Property("perma")
    private boolean permanent;

    @Property("until")
    private long until;

    @Override
    public void action(UUID uuid){
        ProxiedPlayer player = Core.getInstance().getProxy().getPlayer(uuid);
        player.sendMessage(getPrefix() + "You have been " + ChatColor.YELLOW + "muted" + ChatColor.GRAY + "!");
        player.sendMessage(getPrefix() + ChatColor.GREEN + "Reason: " + ChatColor.YELLOW + getReason());
        player.sendMessage(getPrefix() + ChatColor.GREEN + "Time: " + ChatColor.YELLOW + getDate());
        player.sendMessage(getPrefix() + ChatColor.GREEN + "Until: " + ChatColor.YELLOW + (permanent?"Forever":until));
        //player.sendMessage(getPrefix() + ChatColor.GREEN + "Issuer: " + ChatColor.YELLOW + getPunisher());

        Core.getInstance().getPunishmentManager().getActiveMutes().put(uuid, this);
    }

    @Override
    public String getType() {
        return "mute";
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public long getUntil() {
        return until;
    }

    public void setUntil(long until) {
        this.until = until;
    }

    private String getPrefix() {
        return ChatColor.DARK_RED + "" + ChatColor.BOLD + "STAFF | " + ChatColor.RESET + "" + ChatColor.GRAY;
    }
}
