package me.borawski.staff.data.punishment;

import me.borawski.staff.Core;
import net.md_5.bungee.api.ChatColor;

import java.util.UUID;

public class Kick extends Punishment {

    public String message;

    public Kick() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void action(UUID uuid) {
        Core.getInstance().getProxy().getPlayer(uuid).disconnect(
                ChatColor.RED + "-----[HexCore]-----\n"
                        + ChatColor.DARK_RED + "You have been kicked!\n"
                        + "\n"
                        + ChatColor.RED + "Player: " + ChatColor.YELLOW + Core.getInstance().getProxy().getPlayer(uuid).getName() + "\n"
                        + ChatColor.RED + "Reason: " + ChatColor.GRAY + getReason() + "\n"
                        + ChatColor.RED + "-------------------"
        );
    }

    @Override
    public String getType() {
        return "kick";
    }
}
