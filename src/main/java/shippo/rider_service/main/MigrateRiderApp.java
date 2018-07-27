package shippo.rider_service.main;

import shippo.rider_service.migrate.BatchTaskMigrate;
import shippo.rider_service.migrate.TransportTaskMigrate;
import shippo.rider_service.migrate.UserIntegrationMigrate;
import shippo.rider_service.migrate.UsersMigrate;

public class MigrateRiderApp {
    public static void main(String[] args) {
        new TransportTaskMigrate().start();
        new BatchTaskMigrate().start();
        new UserIntegrationMigrate().start();
        new UsersMigrate().start();
    }
}
