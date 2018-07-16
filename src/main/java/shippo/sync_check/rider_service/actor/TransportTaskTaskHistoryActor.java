package shippo.sync_check.rider_service.actor;

import shippo.global.Mapping;
import shippo.global.entities.v0.rider.TaskHistory;
import shippo.global.entities.v1.TransportTask;
import shippo.sync_check.sync_check_common.actor.AbstractSync;
import shippo.sync_check.sync_check_common.constant.ErrorCode;

import java.util.*;

public class TransportTaskTaskHistoryActor extends AbstractSync<TransportTask, TaskHistory> {

    public TransportTaskTaskHistoryActor(String firstDatabase, String secondDatabase) {
        super(TransportTask.class, TaskHistory.class, firstDatabase, secondDatabase);
    }

    @Override
    public int syncCheck(List<TransportTask> firstTableData, List<TaskHistory> secondTableData) {
        int rs = ErrorCode.OK;
        StringBuilder builder = new StringBuilder();
        builder.append("TransportTask to TaskHistory : ");
        Map<Integer, TransportTask> transportTaskMap = new HashMap<>();
        // TransportTask to TaskHistory
        for (TransportTask transportTask : firstTableData) {
            transportTaskMap.put(transportTask.getId(), transportTask);
            for (TaskHistory taskHistory : Mapping.mapTaskToTaskHistories(transportTask)) {
                if (taskHistory.getCreatedAt().getTime() < startTime) continue;
                if (!secondTableData.contains(taskHistory)) {
                    builder.append(taskHistory.getTaskId() + ",");
                    rs = ErrorCode.DATA_NOT_MATCH;
                }
            }
        }

        // TaskHistory to TransportTask
        builder.append("\nTaskHistory to TransportTask +:");
        for (TaskHistory taskHistory : secondTableData) {
            TransportTask transportTask = transportTaskMap.get(taskHistory.getTaskId());
            if (transportTask == null || !Mapping.mapTaskToTaskHistories(transportTask).contains(taskHistory)) {
                builder.append(taskHistory.getTaskId() + ",");
                rs = ErrorCode.DATA_NOT_MATCH;
            }
        }

        errorNotify = builder.toString();
        return rs;
    }

}
