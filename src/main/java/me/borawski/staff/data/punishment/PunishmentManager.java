package me.borawski.staff.data.punishment;

import me.borawski.staff.Core;
import me.borawski.staff.data.Mongo;
import org.mongodb.morphia.query.Query;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PunishmentManager {

    private Core instance;

    private Map<UUID, Mute> activeMutes;

    public PunishmentManager(Core instance) {
        this.instance = instance;
        activeMutes = new ConcurrentHashMap<>();
    }

    public Core getInstance() {
        return instance;
    }

    public Map<UUID, Mute> getActiveMutes() {
        return activeMutes;
    }

    public Mongo getMongo() {
        return getInstance().getMongo();
    }

    public void savePunishment(Punishment punishment) {
        getMongo().getDatastore().save(punishment);
        System.out.println("Received and saved punishment: " + punishment.getId());
    }

    public List getPunishmentHistory(UUID uuid) {
        Query query = getMongo().getDatastore().createQuery(Punishment.class)
                .filter("target", uuid);
        return query.asList();
    }



}

