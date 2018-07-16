package shippo.sync_check.rider_service.actor;

import shippo.global.Mapping;
import shippo.global.Utils;
import shippo.global.entities.v0.rider.Metadata;
import shippo.global.entities.v0.rider.RiderShift;
import shippo.global.entities.v1.MetaFields;
import shippo.global.type.Type;
import shippo.sync_check.sync_check_common.actor.AbstractSync;
import shippo.sync_check.sync_check_common.constant.ErrorCode;

import java.util.*;

public class MetaFieldsRiderShiftActor extends AbstractSync<MetaFields,RiderShift> {

    public MetaFieldsRiderShiftActor(String firstDatabase, String secondDatabase) {
        super(MetaFields.class, RiderShift.class, firstDatabase, secondDatabase);
    }

    @Override
    public int syncCheck(List<MetaFields> firstTableData, List<RiderShift> secondTableData) {
        int rs = ErrorCode.OK;
        StringBuilder builder = new StringBuilder();
        builder.append("TransportationTask to MetaFields : ");
        Map<Integer, RiderShift> riderShiftMap = new HashMap<>();
        for (RiderShift riderShift : secondTableData){
            riderShiftMap.put(riderShift.getId(),riderShift);
            for (MetaFields metaFields : Mapping.mapTaskToMetaFields(riderShift)){
                if(metaFields.getModifiedTime().getTime() < startTime) continue;
                if(!secondTableData.contains(metaFields)){
                    builder.append(metaFields.getObjectId() + ",");
                    rs = ErrorCode.DATA_NOT_MATCH;
                }
            }
        }

        builder.append("\nMetaFields to TransportationTask +:");
        for (MetaFields metaFields : firstTableData){
            RiderShift riderShift = riderShiftMap.get(metaFields.getObjectId());
            if(!metaFields.getObjectType().equals("TaskBatch")) continue;
            if(riderShift == null || !Mapping.mapTaskToMetaFields(riderShift).contains(metaFields)){
                builder.append(metaFields.getObjectId() + "_ "+ metaFields.getModifiedTime().toString() + ",");
                rs = ErrorCode.DATA_NOT_MATCH;
            }
        }

        errorNotify = builder.toString();
        return rs;
    }

}
