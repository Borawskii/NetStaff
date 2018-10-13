package me.borawski.staff;

import me.borawski.staff.command.CommandHandler;
import me.borawski.staff.command.staff.StaffChatCommand;
import me.borawski.staff.command.utility.SayCommand;
import me.borawski.staff.command.utility.WhoIsCommand;
import me.borawski.staff.data.Mongo;
import me.borawski.staff.data.MySQL;
import me.borawski.staff.data.auth.StaffKeyManager;
import me.borawski.staff.data.punishment.PunishmentManager;
import me.borawski.staff.listener.PlayerChatEvent;
import me.lucko.luckperms.api.LuckPermsApi;
import net.md_5.bungee.api.plugin.Plugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Core extends Plugin {

    private static Core instance;

    private PunishmentManager punishmentManager;
    private CommandHandler commandHandler;

    private LuckPermsApi luckPermsApi;

    private List<UUID> staffChat;

    private Mongo mongo;
    private MySQL mySQL;

    private StaffKeyManager staffKeys;

    @Override
    public void onEnable() {
        instance = this;

        punishmentManager = new PunishmentManager(this);

        commandHandler = new CommandHandler(this);
        commandHandler.add("staffchat", new StaffChatCommand());
        commandHandler.add("say", new SayCommand());
        commandHandler.add("whois", new WhoIsCommand());
        commandHandler.registerCommands();

        getProxy().getPluginManager().registerListener(this, new PlayerChatEvent());

        staffChat = new ArrayList<>();

        mongo = new Mongo();
        mySQL = new MySQL("localhost", 3306, "staffcore", "root", "B1VSGVCb");
        try {
            mySQL.update(
                    "CREATE TABLE IF NOT EXISTS sp_keys(" +
                            "ID INT NOT NULL AUTO_INCREMENT," +
                            "uuid VARCHAR," +
                            "key VARCHAR," +
                            "used BOOLEAN," +
                            "PRIMARY KEY(ID)" +
                            ");"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

        staffKeys = new StaffKeyManager(this);

        getLogger().info("NetStaff has been loaded");
    }

    public static Core getInstance() {
        return instance;
    }

    public PunishmentManager getPunishmentManager() {
        return punishmentManager;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public List<UUID> getStaffChat() {
        return staffChat;
    }

    public Mongo getMongo() {
        return mongo;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public StaffKeyManager getStaffKeys() {
        return staffKeys;
    }
}
