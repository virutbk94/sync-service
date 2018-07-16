package shippo.global.entities.test;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import org.json.JSONArray;
import shippo.global.entities.v1.TransportTask;
import shippo.global.Mapping;
import shippo.global.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EbeanServer ebeanServer = Ebean.getServer("shippo_vn_1905");

        TransportTask transportTask = ebeanServer.find(TransportTask.class)
                .where().idEq(50293).findUnique();

        List<TransportTask.TimeLine> list1 = transportTask.getTimeline();

        JSONArray jsonArray = new JSONArray(list1);

        List<TransportTask.TimeLine> list2 = Mapping.mapStringToTimeLines(jsonArray.toString());

        System.out.println(Utils.eqList(list1,list2));

//        timeLine.setTime(transportTask.getTimeline().get(0).getTime());
//        timeLine.setLabel("Test");
//        timeLine.setColor("Test");
//        timeLine.setScope("Test");

//        boolean c = transportTask.getTimeline().contains(timeLine);
//        System.out.println(c);
//        ebeanServer.save(transportTask);
    }
}
