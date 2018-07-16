package shippo.global;

import java.util.ArrayList;
import java.util.List;

public class Utils {

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
}
