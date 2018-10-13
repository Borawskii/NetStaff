package me.borawski.staff.data.auth;

import me.borawski.staff.Core;
import me.borawski.staff.data.auth.key.StaffKey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StaffKeyManager {

    private Core instance;

    public StaffKeyManager(Core instance) {
        this.instance = instance;
    }

    public Core getInstance() {
        return instance;
    }

    public boolean hasKey(UUID uuid) {
        try {
            ResultSet set = getInstance().getMySQL().query(
                    "SELECT key FROM sp_keys WHERE uuid='" + uuid.toString().replace("-", "") + "';"
            );

            return set.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
}

    public String createKey(UUID uuid) {
        StaffKey key = new StaffKey();

        String staffKey = key.nextString();
        try {
            getInstance().getMySQL().update("INSERT INTO sp_keys VALUES (NULL, '" + uuid.toString().replace("-", "") + "', '" + staffKey + "', " + false + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return staffKey;
    }

    public String getKey(UUID uuid) {
        try {
            ResultSet set = getInstance().getMySQL().query(
                    "SELECT key FROM sp_keys WHERE uuid='" + uuid.toString().replace("-", "") + "';"
            );

            while(set.next()) {
                return set.getString("key");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean hasUsed(UUID uuid) {
        try {
            ResultSet set = getInstance().getMySQL().query(
                    "SELECT used FROM sp_keys WHERE uuid='" + uuid.toString().replace("-", "") + "';"
            );

            while(set.next()) {
                return set.getBoolean("used");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
