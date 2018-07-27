package shippo.global.sync_actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class AbstractSyncActor<T> extends AbstractActor {
    protected Logger LOG = LoggerFactory.getLogger(AbstractSyncActor.class);
    protected char crudOperation;
    protected T before;
    protected T after;
    protected Class<T> typeClass;
    protected int sourceVersion;
    protected int destinationVersion;

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(String.class, eventString -> {
            JSONObject payload = getPayload(eventString);
            // Neu payload = null bo qua event
            if (payload == null) return;
            boolean payloadValid = checkPayload(payload);
            if(!payloadValid) return;
            if(!needSync()) return;
            boolean ok = executeSync();
        }).build();
    }

    protected abstract boolean needSync();

    protected abstract boolean executeSync();

    protected boolean isLoop(){
        if(crudOperation == 'd'){
            JSONObject jsonObject = new JSONObject(before);
            if(jsonObject.getInt("version") % 2 == sourceVersion) return false;
            else return true;
        }else {
            JSONObject jsonObject = new JSONObject(after);
            if(jsonObject.getInt("version") % 2 == sourceVersion) return false;
            else return true;
        }
    }

    protected boolean checkPayload(JSONObject payload) {
        crudOperation = getOperation(payload);
        switch (crudOperation) {
            case 'c': {
                after = mappingEvent((JSONObject)payload.get("after"));
                if (after == null) return false;
                return true;
            }
            case 'u': {
                before = mappingEvent((JSONObject)payload.get("before"));
                if (before == null) return false;
                after = mappingEvent((JSONObject)payload.get("after"));
                if (after == null) return false;
                return true;
            }
            case 'd': {
                before = mappingEvent((JSONObject)payload.get("before"));
                if (before == null) return false;
                return true;
            }
            case 'r':
            return false;
            default: {
                LOG.error("payload error " + payload.toString());
                return false;
            }
        }
    }

    protected T mappingEvent(JSONObject obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            if(obj.toString().equals("null")) return null;
            return mapper.readValue(obj.toString(),typeClass);
        } catch (IOException e) {
            LOG.error(obj.toString() + "\n" + e.toString());
            return null;
        }
    }

    public char getOperation(JSONObject payload) {
        String type = payload.getString("op");
        return type.charAt(0);
    }

    protected JSONObject getPayload(String eventString) {
        JSONObject event = new JSONObject(eventString);

        if (event.isNull("payload")) {
            LOG.info("Payload is nullllllllllllllllllllllllllll!");
            return null;
        }

        JSONObject payload = (JSONObject) event.get("payload");
        return payload;
    }
}
