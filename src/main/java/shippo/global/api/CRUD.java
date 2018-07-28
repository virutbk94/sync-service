package shippo.global.api;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Expr;
import com.avaje.ebean.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CRUD {
    static Logger LOG = LoggerFactory.getLogger(CRUD.class);

    public static void save(Object object, Class beanType, EbeanServer server) throws Exception {
        Object object1 = server.find(beanType).where().idEq(object.hashCode()).findUnique();
        if (object1 == null) server.insert(object);
        else server.update(object);
        LOG.info("save {} done!", object);
    }

    public static void save(Object object, String key, Object value, Class beanType, EbeanServer server) throws Exception {
        Object object1 = server.find(beanType).where().eq(key, value).findUnique();
        if (object1 == null) server.insert(object);
        else server.update(object);
        LOG.info("save {} done!", object);
    }

    public static void update(Object object, EbeanServer server) throws Exception {
        server.update(object);
        LOG.info("update {} done!", object);
    }

    public static void insert(Object object, EbeanServer server) throws Exception {
        server.insert(object);
        LOG.info("insert {} done!", object);
    }

    public static void delete(Object object, EbeanServer server) throws Exception {
        server.delete(object);
        LOG.info("delete {} done!", object);
    }

    public static Object read(String key, Object value, Class beanType, EbeanServer server) throws Exception {
        return server.find(beanType).where().eq(key, value).findUnique();
    }

    public static Object read(Map<String, Object> map, Class beanType, EbeanServer server) throws Exception {
        Expression expression = server.getExpressionFactory().allEq(map);
        return server.find(beanType).where(expression).findUnique();
    }
}
