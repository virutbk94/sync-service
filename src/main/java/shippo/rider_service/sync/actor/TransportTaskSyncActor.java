package shippo.rider_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.api.CRUD;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.TaskHistory;
import shippo.rider_service.entities.v1.TransportTask;
import shippo.rider_service.entities.v0.TransportationTask;
import shippo.global.sync_actor.AbstractSyncActor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransportTaskSyncActor extends AbstractSyncActor<TransportTask> {

    public TransportTaskSyncActor() {
        LOG = LoggerFactory.getLogger(TransportTaskSyncActor.class);
        sourceVersion = 1;
        destinationVersion = 0;
        typeClass = TransportTask.class;
    }

    public static Props props() {
        return Props.create(TransportTaskSyncActor.class,() -> new TransportTaskSyncActor());
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected TransportTask mappingEvent(JSONObject data) {
        if(!data.isNull("timeline")) {
            String timeline = data.getString("timeline");
            Object object = new Gson().fromJson(timeline, Object.class);
            data.put("timeline", object);
        }
        return super.mappingEvent(data);
    }

    @Override
    protected boolean executeSync() {
        TransportationTask transportationTask;
        EbeanServer server = PostgressDbConf.getRiderDb();
        switch (crudOperation) {
            case 'd': {
                transportationTask = Mapping.mapTransportTask2TransportationTask(before);
                try {
                    CRUD.delete(transportationTask, server);
                    List<TaskHistory> taskHistories = Mapping.mapTransportTask2TaskHistories(before);
                    if (taskHistories != null)
                        for (TaskHistory taskHistory : taskHistories) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("task_id", taskHistory.getTaskId());
                            map.put("label_description", taskHistory.getLabelDescription());
                            map.put("created_at", taskHistory.getCreatedAt());
                            TaskHistory taskHistoryToDelete = (TaskHistory) CRUD.read(map, TaskHistory.class, server);
                            CRUD.delete(taskHistoryToDelete, server);
                        }
                } catch (Exception e) {
                    LOG.error("Can't delete transportationTask " + transportationTask + " TransportTask " + before + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'u': {
                transportationTask = Mapping.mapTransportTask2TransportationTask(after);
                try {
                    CRUD.update(transportationTask, server);
                    List<TaskHistory> taskHistories = Mapping.mapTransportTask2TaskHistories(after);
                    if (taskHistories != null)
                        for (TaskHistory taskHistory : taskHistories) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("task_id", taskHistory.getTaskId());
                            map.put("label_description", taskHistory.getLabelDescription());
                            map.put("created_at", taskHistory.getCreatedAt());
                            TaskHistory taskHistoryToUpdate = (TaskHistory) CRUD.read(map, TaskHistory.class, server);
                            if (taskHistoryToUpdate != null) {
                                taskHistory.setId(taskHistoryToUpdate.getId());
                                CRUD.update(taskHistory, server);
                            } else CRUD.insert(taskHistory, server);
                        }
                } catch (Exception e) {
                    LOG.error("Can't update transportationTask " + transportationTask + " TransportTask " + after + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'c': {
                transportationTask = Mapping.mapTransportTask2TransportationTask(after);
                try {
                    CRUD.insert(transportationTask, server);
                    List<TaskHistory> taskHistories = Mapping.mapTransportTask2TaskHistories(after);
                    if (taskHistories != null)
                        for (TaskHistory taskHistory : taskHistories) {
                            CRUD.insert(taskHistory, server);
                        }
                } catch (Exception e) {
                    LOG.error("Can't create transportationTask " + transportationTask + " TransportTask " + after + "\n" +
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
