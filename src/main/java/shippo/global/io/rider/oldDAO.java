package shippo.global.io.rider;

import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import shippo.global.PostgressDbConf;
import shippo.global.type.Type;

public class oldDAO {
    public static int getOrderCount(int requestId){
        int orderCount = 0;

        String queryString = String.format("select count(*) as orderCount from \"RequestOrder\" as A, \"DeliveryOrder\" as B " +
                "where A.\"requestId\" = %d and A.\"orderId\" = B.id " +
                "and B.status in ('%s','%s');", requestId, Type.DeliverOrderStatus.WAITING_FOR_PICKUP, Type.DeliverOrderStatus.PICKING_UP);
        SqlQuery query = PostgressDbConf.getOldDb().createSqlQuery(queryString);
        SqlRow sqlRow = query.findUnique();
        if(sqlRow != null){
            return sqlRow.getInteger("orderCount");
        }

        return orderCount;
    }

    public static String getPhoneOfCustomer(int custommerId){
        String phone = "";

        String queryString = "select * from \"Users\" where id = " + custommerId;
        SqlQuery query = PostgressDbConf.getOldDb().createSqlQuery(queryString);
        SqlRow sqlRow = query.findUnique();
        if(sqlRow != null){
            return sqlRow.getString("mobile");
        }

        return phone;
    }

}
