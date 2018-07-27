package shippo.rider_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import com.google.gson.Gson;
import org.eclipse.jetty.client.api.ContentResponse;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.Utils;
import shippo.global.api.CRUD;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.Metadata;
import shippo.rider_service.entities.v0.TransportationTask;
import shippo.rider_service.entities.v1.MetaFields;
import shippo.rider_service.entities.v1.TransportTask;
import shippo.rider_service.tookan_api.TaskApi;
import shippo.global.sync_actor.AbstractSyncActor;
import shippo.tookan_service.entities.tookan.Task;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransportationTaskSyncActor extends AbstractSyncActor<TransportationTask> {

    public TransportationTaskSyncActor() {
        LOG = LoggerFactory.getLogger(TransportationTaskSyncActor.class);
        sourceVersion = 0;
        destinationVersion = 1;
        typeClass = TransportationTask.class;
    }

    public static Props props() {
        return Props.create(TransportationTaskSyncActor.class,() -> new TransportationTaskSyncActor());
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected TransportationTask mappingEvent(JSONObject data) {
        if (!data.isNull("reason_code")) {
            String reason_code = data.getString("reason_code");
            Object object = new Gson().fromJson(reason_code, Object.class);
            data.put("reason_code", object);
        }
        if (!data.isNull("metadata")) {
            String metadata = data.getString("metadata");
            Object object = new Gson().fromJson(metadata, Object.class);
            data.put("metadata", object);
        }
        return super.mappingEvent(data);
    }

    @Override
    protected boolean executeSync() {
        TransportTask transportTask;
        EbeanServer server = PostgressDbConf.getOldDb();
        switch (crudOperation) {
            case 'd': {
                transportTask = Mapping.mapTransportationTask2TransportTask(before);
                try {
                    CRUD.delete(transportTask, server);
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
                    LOG.error("Can't delete transportTask " + transportTask + " TransportationTask " + before + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'u': {
                transportTask = Mapping.mapTransportationTask2TransportTask(after);
                try {
                    CRUD.update(transportTask, server);
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
                    LOG.error("Can't update transportTask " + transportTask + " TransportationTask " + after + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'c': {
                transportTask = Mapping.mapTransportationTask2TransportTask(after);
                try {
                    CRUD.insert(transportTask, server);
                    List<MetaFields> metaFieldsList = Mapping.mapTask2MetaFields(after);
                    if (metaFieldsList != null)
                        for (MetaFields metaFields : metaFieldsList) {
                            CRUD.insert(metaFields, server);
                        }
                    // Tao moi tookan task
                    Task task = Mapping.mapTransportationTask2Task(after);
                    ContentResponse contentResponse = new TaskApi().insert(task);
                    JSONObject jsonObject = new JSONObject(contentResponse.getContentAsString());
                    if (jsonObject.getInt("status") == 200) {
                        LOG.info("Create task tookan success!");
                        Integer job_id = ((JSONObject) jsonObject.get("data")).getInt("job_id");
                        String tracking_link = ((JSONObject) jsonObject.get("data")).getString("tracking_link");
                        after.setTookanJobId(job_id.toString());
                        Metadata metadata = new Metadata();
                        metadata.setKey("tookan_tracking_link");
                        metadata.setLabel("Link theo dõi nhiệm vụ");
                        metadata.setValue(tracking_link);
                        metadata.setTime(Utils.timespampToPostgreTimestampString(new Timestamp(System.currentTimeMillis())));
                        if (after.getMetadata() == null || after.getMetadata().size() == 0) after.setMetadata(new ArrayList<>());
                        after.getMetadata().add(metadata);
                        CRUD.update(after,PostgressDbConf.getRiderDb());
                    } else LOG.warn(jsonObject.toString());
                } catch (Exception e) {
                    LOG.error("Can't create transportTask " + transportTask + " TransportationTask " + after + "\n" +
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
