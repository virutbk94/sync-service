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
import shippo.rider_service.entities.v1.TimeLine;
import shippo.rider_service.entities.v1.TransportTask;
import shippo.global.sync_actor.AbstractSyncActor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TaskHistorySyncActor extends AbstractSyncActor<TaskHistory> {

    public TaskHistorySyncActor() {
        LOG = LoggerFactory.getLogger(TaskHistorySyncActor.class);
        sourceVersion = 0;
        destinationVersion = 1;
        typeClass = TaskHistory.class;
    }

    public static Props props() {
        return Props.create(TaskHistorySyncActor.class,() -> new TaskHistorySyncActor());
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected TaskHistory mappingEvent(JSONObject data) {
        if (!data.isNull("extra_fields")) {
            String extra_fields = data.getString("extra_fields");
            Object object = new Gson().fromJson(extra_fields, Object.class);
            data.put("extra_fields", object);
        }
        return super.mappingEvent(data);
    }

    @Override
    protected boolean executeSync() {
        TimeLine timeLine;
        EbeanServer server = PostgressDbConf.getOldDb();
        switch (crudOperation) {
            case 'd': {
                timeLine = Mapping.mapTaskHistory2TimeLine(before);
                try {
                    TransportTask transportTask = (TransportTask) CRUD.read("id",before.getTaskId(),TransportTask.class,server);
                    List<TimeLine> timeLines = transportTask.getTimeline();
                    int index = 0;
                    for(TimeLine oldTimeLine : timeLines){
                        if(Objects.equals(oldTimeLine.getLabel(),timeLine.getLabel())){
                            break;
                        }
                        index ++;
                    }
                    timeLines.remove(index);
                    CRUD.update(transportTask, server);
                } catch (Exception e) {
                    LOG.error("Can't delete timeLine " + timeLine + " TaskHistory " + before + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'u': {
                timeLine = Mapping.mapTaskHistory2TimeLine(after);
                try {
                    TransportTask transportTask = (TransportTask) CRUD.read("id",after.getTaskId(),TransportTask.class,server);
                    List<TimeLine> timeLines = transportTask.getTimeline();
                    for(TimeLine oldTimeLine : timeLines){
                        if(Objects.equals(oldTimeLine.getLabel(),timeLine.getLabel())){
                            oldTimeLine.setColor(timeLine.getColor());
                            oldTimeLine.setScope(timeLine.getScope());
                            oldTimeLine.setTime(timeLine.getTime());
                            break;
                        }
                    }
                    CRUD.update(transportTask, server);

                } catch (Exception e) {
                    LOG.error("Can't update timeLine " + timeLine + " TaskHistory " + after + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'c': {
                timeLine = Mapping.mapTaskHistory2TimeLine(after);
                try {
                    TransportTask transportTask = (TransportTask) CRUD.read("id",after.getTaskId(),TransportTask.class,server);
                    List<TimeLine> timeLines = transportTask.getTimeline();
                    if(timeLines == null) timeLines = new ArrayList<>();
                    timeLines.add(timeLine);
                    CRUD.update(transportTask, server);

                } catch (Exception e) {
                    LOG.error("Can't update create " + timeLine + " TaskHistory " + after + "\n" +
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
