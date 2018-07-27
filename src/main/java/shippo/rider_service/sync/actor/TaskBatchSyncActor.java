package shippo.rider_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.api.CRUD;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.RiderShift;
import shippo.rider_service.entities.v1.TaskBatch;
import shippo.global.sync_actor.AbstractSyncActor;

public class TaskBatchSyncActor extends AbstractSyncActor<TaskBatch> {

    public TaskBatchSyncActor() {
        LOG = LoggerFactory.getLogger(TaskBatchSyncActor.class);
        sourceVersion = 1;
        destinationVersion = 0;
        typeClass = TaskBatch.class;
    }

    public static Props props() {
        return Props.create(TaskBatchSyncActor.class,() -> new TaskBatchSyncActor());
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected TaskBatch mappingEvent(JSONObject data) {
        if (!data.isNull("batchData")) {
            String batchData = data.getString("batchData");
            Object object = new Gson().fromJson(batchData, Object.class);
            data.put("batchData", object);
        }
        return super.mappingEvent(data);
    }

    @Override
    protected boolean executeSync() {
        RiderShift riderShift;
        EbeanServer server = PostgressDbConf.getRiderDb();
        switch (crudOperation) {
            case 'd': {
                riderShift = Mapping.mapTaskBatch2RiderShift(before);
                try {
                    CRUD.delete(riderShift, server);
                } catch (Exception e) {
                    LOG.error("Can't delete riderShift " + riderShift + " TaskBatch " + before + "\n" +
                            e.getStackTrace());
                    return false;
                }
                return true;
            }
            case 'u': {
                riderShift = Mapping.mapTaskBatch2RiderShift(after);
                try {
                    CRUD.update(riderShift, server);
                } catch (Exception e) {
                    LOG.error("Can't update riderShift " + riderShift + " TaskBatch " + after + "\n" +
                            e.getStackTrace());
                    return false;
                }
                return true;
            }
            case 'c': {
                riderShift = Mapping.mapTaskBatch2RiderShift(after);
                try {
                    CRUD.insert(riderShift, server);
                } catch (Exception e) {
                    LOG.error("Can't create riderShift " + riderShift + " TaskBatch " + after + "\n" +
                            e.getStackTrace());
                    return false;
                }
                return true;
            }
            default:
                return false;
        }
    }
}
