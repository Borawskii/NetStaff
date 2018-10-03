package me.borawski.staff;

import me.borawski.staff.command.CommandHandler;
import me.borawski.staff.command.staff.StaffChatCommand;
import me.borawski.staff.command.utility.SayCommand;
import me.borawski.staff.listener.PlayerChatEvent;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Core extends Plugin {

    private static Core instance;

    private CommandHandler commandHandler;
    private List<UUID> staffChat;

    @Override
    public void onEnable() {
        instance = this;

        commandHandler = new CommandHandler(this);
        commandHandler.add("staffchat", new StaffChatCommand());
        commandHandler.add("say", new SayCommand());
        commandHandler.registerCommands();

        getProxy().getPluginManager().registerListener(this, new PlayerChatEvent());

        staffChat = new ArrayList<>();

        getLogger().info("NetStaff has been loaded");
    }

    public static Core getInstance() {
        return instance;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public List<UUID> getStaffChat() {
        return staffChat;
    }
}
