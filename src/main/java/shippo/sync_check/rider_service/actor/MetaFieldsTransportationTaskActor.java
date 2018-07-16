package shippo.sync_check.rider_service.actor;

import shippo.global.Mapping;
import shippo.global.entities.v0.rider.Metadata;
import shippo.global.entities.v0.rider.TransportationTask;
import shippo.global.entities.v1.MetaFields;
import shippo.global.type.Type;
import shippo.sync_check.sync_check_common.actor.AbstractSync;
import shippo.sync_check.sync_check_common.constant.ErrorCode;

import java.util.*;

public class MetaFieldsTransportationTaskActor extends AbstractSync<MetaFields,TransportationTask> {

    public MetaFieldsTransportationTaskActor(String firstDatabase, String secondDatabase) {
        super(MetaFields.class, TransportationTask.class, firstDatabase, secondDatabase);
    }

    @Override
    public int syncCheck(List<MetaFields> firstTableData, List<TransportationTask> secondTableData) {
        int rs = ErrorCode.OK;
        StringBuilder builder = new StringBuilder();
        builder.append("TransportationTask to MetaFields : ");
        Map<Integer, TransportationTask> transportationTaskHashMap = new HashMap<>();
        for (TransportationTask transportationTask : secondTableData){
            transportationTaskHashMap.put(transportationTask.getId(),transportationTask);
            for (MetaFields metaFields : Mapping.mapTaskToMetaFields(transportationTask)){
                if(metaFields.getModifiedTime().getTime() < startTime) continue;
                if(!secondTableData.contains(metaFields)){
                    builder.append(metaFields.getObjectId() + ",");
                    rs = ErrorCode.DATA_NOT_MATCH;
                }
            }
        }

        builder.append("\nMetaFields to TransportationTask +:");
        for (MetaFields metaFields : firstTableData){
            TransportationTask transportationTask = transportationTaskHashMap.get(metaFields.getObjectId());
            if(!metaFields.getObjectType().equals("TransportTask")) continue;
            if(transportationTask == null || Mapping.mapTaskToMetaFields(transportationTask).contains(metaFields)){
                builder.append(metaFields.getObjectId() + "_ "+ metaFields.getModifiedTime().toString() + ",");
                rs = ErrorCode.DATA_NOT_MATCH;
            }
        }

        errorNotify = builder.toString();
        return rs;
    }

}
