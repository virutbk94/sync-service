package shippo.global.sync_actor;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class AbstractSyncTookanActor<T> extends AbstractActor {
    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(String.class, eventString -> {
            // Check event neu la event dbz => loai bo nhung event
        }).build();
    }
}
