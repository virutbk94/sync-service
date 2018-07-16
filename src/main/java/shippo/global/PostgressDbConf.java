package shippo.global;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;

public class PostgressDbConf {
    private static EbeanServer riderDb;
    private static EbeanServer authDb;
    private static EbeanServer oldDb;

    private static String riderDbName;
    private static String authDbName;
    private static String oldDbName;

    static {
        loadConfig();
    }

    // Load file config , khoi tao ebean server
    private static void loadConfig() {
        // TODO
        // Cấu hình db từ file
        riderDbName = "rider_service";
        authDbName = "oauth_service";
        oldDbName = "shippo_vn_1905";
    }

    public static EbeanServer getAuthDb() {
        if (authDb == null)
            authDb = Ebean.getServer(authDbName);
        return authDb;
    }

    public static EbeanServer getOldDb() {
        if (oldDb == null)
            oldDb = Ebean.getServer(oldDbName);
        return oldDb;
    }

    public static EbeanServer getRiderDb() {
        if (riderDb == null)
            riderDb = Ebean.getServer(riderDbName);
        return riderDb;
    }
}
