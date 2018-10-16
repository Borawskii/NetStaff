package me.borawski.staff.data.punishment;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

import java.util.List;
import java.util.UUID;

@Entity("punishment")
public abstract class Punishment {

    @Id
    @Property("id")
    ObjectId id;

    @Property("target")
    UUID target;

    @Property("punisher")
    UUID punisher;

    @Property("reason")
    String reason;

    @Property("evidence")
    List<String> evidence;

    @Property("date")
    long date;

    @Property
    boolean pardoned;

    public Punishment() {
        super();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public UUID getTarget() {
        return target;
    }

    public void setTarget(UUID target) {
        this.target = target;
    }

    public UUID getPunisher() {
        return punisher;
    }

    public void setPunisher(UUID punisher) {
        this.punisher = punisher;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<String> getEvidence() {
        return evidence;
    }

    public void setEvidence(List<String> evidence) {
        this.evidence = evidence;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public boolean isPardoned() {
        return pardoned;
    }

    public void setPardoned(boolean pardoned) {
        this.pardoned = pardoned;
    }

    /**
     * Immediate action as soon as the punishment is made
     * @param uuid
     */
    public abstract void action(UUID uuid);

    public abstract String getType();
}
