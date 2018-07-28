package shippo.rider_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.Utils;
import shippo.global.api.CRUD;
import shippo.rider_service.Mapping;
import shippo.rider_service.conf.Constants;
import shippo.rider_service.entities.v0.Metadata;
import shippo.rider_service.entities.v0.RiderShift;
import shippo.rider_service.entities.v0.TransportationTask;
import shippo.rider_service.entities.v1.MetaFields;
import shippo.global.sync_actor.AbstractSyncActor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MetaFieldsSyncActor extends AbstractSyncActor<MetaFields> {

    private MetaFieldsSyncActor() {
        LOG = LoggerFactory.getLogger(MetaFieldsSyncActor.class);
        sourceVersion = 1;
        destinationVersion = 0;
        typeClass = MetaFields.class;
    }

    public static Props props() {
        return Props.create(MetaFieldsSyncActor.class,MetaFieldsSyncActor::new);
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected boolean executeSync() {
        Metadata metadata;
        EbeanServer server = PostgressDbConf.getRiderDb();
        switch (crudOperation) {
            case 'd': {
                metadata = Mapping.mapMetaField2MetaData(before);
                try {
                    Object task;
                    List<Metadata> metadataList;
                    if (before.getObjectType().equals(Constants.ObjectType.TASK_BATCH)) {
                        task = CRUD.read("id", before.getObjectId(), RiderShift.class, server);
                        metadataList = ((RiderShift) task).getMetadata();
                    } else if (before.getObjectType().equals(Constants.ObjectType.TRANSPORT_TASK)) {
                        task = CRUD.read("id", before.getObjectId(), TransportationTask.class, server);
                        metadataList = ((TransportationTask) task).getMetadata();
                    } else {
                        LOG.info("Meta data is other Object Type " + before);
                        return false;
                    }
                    int index = 0;
                    for (Metadata metadata1 : metadataList) {
                        if (Objects.equals(metadata1.getKey(), metadata.getKey())) {
                            break;
                        }
                        index++;
                    }
                    metadataList.remove(index);
                    CRUD.update(task, server);
                } catch (Exception e) {
                    LOG.error("Can't delete metadata " + metadata + " Metafields " + before + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'u': {
                metadata = Mapping.mapMetaField2MetaData(after);
                try {
                    Object task;
                    List<Metadata> metadataList;
                    if (after.getObjectType().equals(Constants.ObjectType.TASK_BATCH)) {
                        task = CRUD.read("id", after.getObjectId(), RiderShift.class, server);
                        metadataList = ((RiderShift) task).getMetadata();
                    } else if (after.getObjectType().equals(Constants.ObjectType.TRANSPORT_TASK)) {
                        task = CRUD.read("id", after.getObjectId(), TransportationTask.class, server);
                        metadataList = ((TransportationTask) task).getMetadata();
                    } else {
                        LOG.info("Meta data is other Object Type " + after);
                        return false;
                    }
                    for (Metadata metadata1 : metadataList) {
                        if (Objects.equals(metadata1.getLabel(), metadata.getLabel())) {
                            break;
                        }
                    }
                    CRUD.update(task, server);

                } catch (Exception e) {
                    LOG.error("Can't update metadata " + metadata + " Metafields " + after + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'c': {
                metadata = Mapping.mapMetaField2MetaData(after);
                try {
                    Object task;
                    List<Metadata> metadataList;
                    if (after.getObjectType().equals(Constants.ObjectType.TASK_BATCH)) {
                        task = CRUD.read("id", after.getObjectId(), RiderShift.class, server);
                        metadataList = ((RiderShift) task).getMetadata();
                    } else if (after.getObjectType().equals(Constants.ObjectType.TRANSPORT_TASK)) {
                        task = CRUD.read("id", after.getObjectId(), TransportationTask.class, server);
                        metadataList = ((TransportationTask) task).getMetadata();
                    } else {
                        LOG.info("Meta data is other Object Type " + after);
                        return false;
                    }
                    if (metadataList == null) metadataList = new ArrayList<>();
                    metadataList.add(metadata);
                    CRUD.update(task, server);

                } catch (Exception e) {
                    LOG.error("Can't update create " + metadata + " Metafields " + after + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            default:
                return false;
        }
    }
}
