package shippo.sync_check.rider_service.actor;

import org.json.JSONArray;
import shippo.global.Mapping;
import shippo.global.entities.v0.rider.TransportationTask;
import shippo.global.entities.v1.TransportTask;
import shippo.sync_check.sync_check_common.actor.AbstractSync;
import shippo.sync_check.sync_check_common.constant.ErrorCode;

import java.util.List;
import java.util.regex.Pattern;

public class TransportTaskTransportationTasksActor extends AbstractSync<TransportTask, TransportationTask> {

    public TransportTaskTransportationTasksActor(String firstDatabase, String secondDatabase) {
        super(TransportTask.class, TransportationTask.class, firstDatabase, secondDatabase);
    }

    @Override
    public int syncCheck(List<TransportTask> firstTableData, List<TransportationTask> secondTableData) {
        int rs = ErrorCode.OK;

        // convert reason code
//        for(TransportationTask transportationTask : secondTableData){
//            for(TransportationTask.ReasonCode reasonCode : transportationTask.getReasonCode()){
//                System.out.println(reasonCode);
//            }
//        }

        StringBuilder builder = new StringBuilder();
        //TransportTask to TransportationTask
        builder.append("TransportTask to TransportationTask:");
        for (TransportTask transportTask : firstTableData) {
            TransportationTask transportationTask = Mapping.mapTransportTaskToTransportationTask(transportTask);
            if (!secondTableData.contains(transportationTask)) {
                rs = ErrorCode.DATA_NOT_MATCH;
                builder.append(transportTask.getId() + ",");
            }
        }
        //TransportationTask to TransportTask
        builder.append("TransportationTask to TransportTask:");
        for (TransportationTask transportationTask : secondTableData) {
            TransportTask transportTask = Mapping.mapTransportationTaskToTransportTask(transportationTask);
            if (!firstTableData.contains(transportTask)) {
                rs = ErrorCode.DATA_NOT_MATCH;
                builder.append(transportationTask.getId() + ",");
            }
        }
        errorNotify = builder.toString();
        return rs;
    }

}
