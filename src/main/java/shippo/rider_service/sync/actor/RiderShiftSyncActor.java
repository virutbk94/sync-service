package shippo.rider_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.api.CRUD;
import shippo.global.sync_actor.AbstractSyncActor;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.RiderShift;
import shippo.rider_service.entities.v1.MetaFields;
import shippo.rider_service.entities.v1.TaskBatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiderShiftSyncActor extends AbstractSyncActor<RiderShift> {

    public RiderShiftSyncActor() {
        LOG = LoggerFactory.getLogger(RiderShiftSyncActor.class);
        sourceVersion = 0;
        destinationVersion = 1;
        typeClass = RiderShift.class;
    }

    public static Props props() {
        return Props.create(RiderShiftSyncActor.class,RiderShiftSyncActor::new);
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected RiderShift mappingEvent(JSONObject data) {
        if (!data.isNull("metadata")) {
            String metadata = data.getString("metadata");
            Object object = new Gson().fromJson(metadata, Object.class);
            data.put("metadata", object);
        }
        if (!data.isNull("barcodes")) {
            String barcodes = data.getString("barcodes");
            Object object = new Gson().fromJson(barcodes, Object.class);
            data.put("barcodes", object);
        }
        return super.mappingEvent(data);
    }

    @Override
    protected boolean executeSync() {
        TaskBatch taskBatch;
        EbeanServer server = PostgressDbConf.getOldDb();
        switch (crudOperation) {
            case 'd': {
                taskBatch = Mapping.mapRiderShift2TaskBatch(before);
                try {
                    CRUD.delete(taskBatch, server);
                    List<MetaFields> metaFieldsList = Mapping.mapTask2MetaFields(before);
                    if (metaFieldsList != null)
                        for (MetaFields metaFields : metaFieldsList) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("\"objectId\"", metaFields.getObjectId());
                            map.put("key", metaFields.getKey());
                            map.put("label", metaFields.getLabel());
                            MetaFields metaFieldsToDelete = (MetaFields) CRUD.read(map, MetaFields.class, server);
                            CRUD.delete(metaFieldsToDelete, server);
                        }
                } catch (Exception e) {
                    LOG.error("Can't delete taskBatch " + taskBatch + " RiderShift " + before + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'u': {
                taskBatch = Mapping.mapRiderShift2TaskBatch(after);
                try {
                    CRUD.update(taskBatch, server);
                    List<MetaFields> metaFieldsList = Mapping.mapTask2MetaFields(after);
                    if (metaFieldsList != null)
                        for (MetaFields metaFields : metaFieldsList) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("\"objectId\"", metaFields.getObjectId());
                            map.put("key", metaFields.getKey());
                            map.put("label", metaFields.getLabel());
                            MetaFields metaFieldsToDelete = (MetaFields) CRUD.read(map, MetaFields.class, server);
                            if (metaFieldsToDelete != null) {
                                metaFields.setId(metaFieldsToDelete.getId());
                                CRUD.update(metaFields, server);
                            } else CRUD.insert(metaFields, server);
                        }
                } catch (Exception e) {
                    LOG.error("Can't update taskBatch " + taskBatch + " RiderShift " + after + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'c': {
                taskBatch = Mapping.mapRiderShift2TaskBatch(after);
                try {
                    CRUD.insert(taskBatch, server);
                    List<MetaFields> metaFieldsList = Mapping.mapTask2MetaFields(before);
                    if (metaFieldsList != null)
                        for (MetaFields metaFields : metaFieldsList) {
                            CRUD.insert(metaFields, server);
                        }
                } catch (Exception e) {
                    LOG.error("Can't create taskBatch " + taskBatch + " RiderShift " + after + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            default:
                return false;
        }
    }
}
