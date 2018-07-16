package shippo.sync_check.rider_service.actor;

import shippo.global.Mapping;
import shippo.global.entities.v0.rider.RiderShift;
import shippo.global.entities.v1.TaskBatch;
import shippo.sync_check.sync_check_common.actor.AbstractSync;
import shippo.sync_check.sync_check_common.constant.ErrorCode;

import java.util.List;

public class TaskBatchRiderShiftsActor extends AbstractSync<TaskBatch, RiderShift> {
    public TaskBatchRiderShiftsActor(String firstDatabase, String secondDatabase) {
        super(TaskBatch.class, RiderShift.class, firstDatabase, secondDatabase);
    }

    @Override
    public int syncCheck(List<TaskBatch> firstTableData, List<RiderShift> secondTableData) {
        int rs = ErrorCode.OK;
        StringBuilder builder = new StringBuilder();

        //TaskBatch To rider_shifts
        builder.append("TaskBatch to RiderShifts:");
        for(TaskBatch taskBatch : firstTableData){
            RiderShift riderShift = Mapping.mapTaskBatch2RiderShift(taskBatch);
            if(!secondTableData.contains(riderShift)){
                builder.append(taskBatch.getId()+",");
                rs = ErrorCode.DATA_NOT_MATCH;
            }
        }
        //rider_shifts to TaskBatch
        builder.append("\nRiderShifts to TaskBatch:");
        for(RiderShift riderShift : secondTableData){
            TaskBatch taskBatch = Mapping.mapRiderShiftsToTaskBatch(riderShift);
            if(!firstTableData.contains(taskBatch)){
                builder.append(riderShift.getId()+",");
                rs = ErrorCode.DATA_NOT_MATCH;
            }
        }

        errorNotify = builder.toString();
        return rs;
    }

}
