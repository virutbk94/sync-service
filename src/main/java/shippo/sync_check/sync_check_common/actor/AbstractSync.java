package shippo.sync_check.sync_check_common.actor;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import shippo.sync_check.sync_check_common.constant.ErrorCode;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractSync<T,U> implements Runnable{

    private Class<T> firstType;
    private Class<U> secondType;
    protected long startTime;
    protected long endTime;
    private static EbeanServer oldServer;
    private static EbeanServer riderServer;
    private static EbeanServer authServer;

    protected String firstDatabase;
    protected String secondDatabase;

    private int n = 0;

    protected String errorNotify;

    public AbstractSync(String firstDatabase, String secondDatabase) {
        this.firstDatabase = firstDatabase;
        this.secondDatabase = secondDatabase;
    }

    public AbstractSync(Class<T> firstType, Class<U> secondType, String firstDatabase, String secondDatabase) {

        this.firstType = firstType;
        this.secondType = secondType;
        this.firstDatabase = firstDatabase;
        this.secondDatabase = secondDatabase;
    }

    public AbstractSync(Class firstType, Class secondType){
        this.firstType = firstType;
        this.secondType = secondType;
        this.errorNotify = "";
    }

    public List<T> getFirstTableData(){
//        System.out.println("Get first data ...");
        EbeanServer ebeanServer = Ebean.getServer(firstDatabase);
        List<T> rs;
        long currentTime = System.currentTimeMillis();
        startTime = currentTime - 2*3600*1000;
//        startTime = 0;
        endTime = currentTime - 5*60*1000;
        rs = ebeanServer.find(firstType).where()
                .lt("modifiedTime", new Timestamp(endTime))
                .ge("modifiedTime", new Timestamp(startTime))
                .findList();
        System.out.println(this.getClass().getName() + " get first done with size " + rs.size());
        return rs;
    };

    @Override
    public void run() {
        n ++;
        System.out.println(firstType.getName() + "_" + secondType.getName() + "_Start check number " + n + " in time " + new Date(System.currentTimeMillis()));
        int errorCode = ErrorCode.OK;
        try {
            errorCode = syncCheck(getFirstTableData(),getSecondTableData());
            if(errorCode != ErrorCode.OK){
                // Send error code for email service...
//                EmailApi emailApi = new EmailApi();
//                errorNotify = errorCode + "_" + errorNotify;
//                System.out.println(errorNotify);
//                try {
//                    emailApi.setEmail("hanamtien@shippo.vn");
//                    emailApi.setHtml(errorNotify);
//                    emailApi.setSubject(firstType.getSimpleName() + " khong dong bo!");
//                    emailApi.sendMessage();
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
            }else {
                System.out.println("Du lieu " + this.getClass().getName() + " da duoc dong bo");
            }
        }catch (Exception e){
            errorCode = ErrorCode.CANNOT_SYNC_DATA;
            errorNotify = e.toString();
            e.printStackTrace();
        }
    }

    public List<U> getSecondTableData(){
//        System.out.println("Get second data ...");
        EbeanServer ebeanServer = Ebean.getServer(secondDatabase);
        List<U> rs = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        startTime = currentTime - 2*3600*1000;
//        startTime = 0;
        endTime = currentTime - 5*60*1000;
        rs = ebeanServer.find(secondType).where()
                .lt("updated_at", new Timestamp(endTime))
                .ge("updated_at", new Timestamp(startTime))
                .findList();
        System.out.println(this.getClass().getName() + " get second done with size " + rs.size());
        return rs;
    };

    public Class<T> getFirstType() {
        return firstType;
    }

    public void setFirstType(Class<T> firstType) {
        this.firstType = firstType;
    }

    public Class<U> getSecondType() {
        return secondType;
    }

    public void setSecondType(Class<U> secondType) {
        this.secondType = secondType;
    }

    public String getFirstDatabase() {
        return firstDatabase;
    }

    public void setFirstDatabase(String firstDatabase) {
        this.firstDatabase = firstDatabase;
    }

    public String getSecondDatabase() {
        return secondDatabase;
    }

    public void setSecondDatabase(String secondDatabase) {
        this.secondDatabase = secondDatabase;
    }

    public abstract int syncCheck(List<T> firstTableData, List<U> secondTableData);

    public String getErrorNotify() {
        return errorNotify;
    }
}
