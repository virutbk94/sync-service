package sync.rider_service;

import jdk.jfr.Description;
import org.junit.Assert;
import org.junit.Test;
import shippo.global.Mapping;
import shippo.global.entities.v0.rider.RiderShift;
import shippo.global.entities.v1.TaskBatch;

import java.sql.Timestamp;

public class TestTaskBatchRiderShift {

    @Test
    @Description("Nếu thời gian tạo nhiệm vụ từ 21h00- truoc 11h00 => phân nhiệm vụ vào ca sáng")
    public void testTaskBatchToMorning(){
        TaskBatch taskBatch = new TaskBatch();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setHours(10);
        taskBatch.setCreatedTime(timestamp);
        RiderShift riderShift = Mapping.mapTaskBatch2RiderShift(taskBatch);
        int real = riderShift.getShiftId();
        Assert.assertEquals(real , 1);
    }

    @Test
    @Description("Nếu thời gian tạo nhiệm vụ từ 11h00- truoc 17h00 => phân nhiệm vụ vào ca chiều")
    public void testTaskBatchToAfternoon(){
        TaskBatch taskBatch = new TaskBatch();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setHours(15);
        taskBatch.setCreatedTime(timestamp);
        RiderShift riderShift = Mapping.mapTaskBatch2RiderShift(taskBatch);
        int real = riderShift.getShiftId();
        Assert.assertEquals(real , 2);
    }

    @Test
    @Description("Nếu thời gian tạo nhiệm vụ từ sau 17h00- truoc 21h00 => phân nhiệm vụ vào ca tối")
    public void testTaskBatchToEvening(){
        TaskBatch taskBatch = new TaskBatch();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        timestamp.setHours(19);
        taskBatch.setCreatedTime(timestamp);
        RiderShift riderShift = Mapping.mapTaskBatch2RiderShift(taskBatch);
        int real = riderShift.getShiftId();
        Assert.assertEquals(real , 3);
    }
}
