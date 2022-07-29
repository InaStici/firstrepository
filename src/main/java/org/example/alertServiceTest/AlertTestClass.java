package org.example.alertServiceTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AlertTestClass {


}

interface AlertDAO {
    UUID addAlert(Date time);
    Date getAlert(UUID id);
}

class AlertService {

    private final AlertDAO storage;

    public AlertService(AlertDAO alertDao) {
        this.storage = alertDao;
    }

    public UUID raiseAlert() {
        return storage.addAlert(new Date());
    }

    public Date getAlertTime(UUID id) {
        return storage.getAlert(id);
    }
}

class MapAlertDAO implements AlertDAO {
    private final Map<UUID, Date> alerts = new HashMap<UUID, Date>();

    @Override
    public UUID addAlert(Date time) {
        UUID id = UUID.randomUUID();
        this.alerts.put(id, time);
        return id;
    }

    @Override
    public Date getAlert(UUID id) {
        return this.alerts.get(id);
    }
}