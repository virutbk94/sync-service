package shippo.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {
    private static Logger LOG = LoggerFactory.getLogger(Utils.class);


    public static boolean eq(Object obj1, Object obj2){
        if ((obj1 == null && obj2 != null) || (obj1 != null && !obj1.equals(obj2))) {
            return false;
        }
        return true;
    }

    public static boolean eqList(List<?> list1, List<?> list2){
        if ((list1 == null && list2 != null) || (list2 == null && list1 != null)) {
            return false;
        }
        if(list1.size() != list2.size()) return false;
        return list1.containsAll(list2) && list2.containsAll(list1);
    }

    public static List<Object> getDiffList(List<?> list1, List<?> list2){
        List<Object> listDiff = new ArrayList<>();
        list2.forEach(o->{
            if(!list1.contains(o)) listDiff.add(o);
        });
        return listDiff;
    }

    public static String formatMonney(Double money){
        try {
            NumberFormat formatter = new DecimalFormat("###,###.##đ");
            String resp = formatter.format(money);
            return resp;
        } catch (Exception e) {
            return "0";
        }
    }

    public static Timestamp postgreTimestampStringToTimestamp(String timestampString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'");
        try {
            Date date = sdf.parse(timestampString);
            // lay time stamp cua gmt +7
            return new Timestamp(date.getTime() + 7 * 3600000L);
        } catch (ParseException e) {
            e.printStackTrace();
            LOG.error(e.toString());
        }
        return null;
    }

    public static String timespampToPostgreTimestampString(Timestamp timestamp) {
        // lay time stamp cua gmt - 7h truoc
        Timestamp gmtTimestamp = new Timestamp(timestamp.getTime() - 7 * 3600000L);
        java.sql.Date date = new java.sql.Date(gmtTimestamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'");
        return sdf.format(date);
    }

    public static String getExceptionMessage(Exception e){
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    public static void main(String[] args) {
        System.out.println(formatMonney(10000.299));
    }
}
