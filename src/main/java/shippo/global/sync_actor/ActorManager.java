package shippo.global.sync_actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.Constants;
import shippo.rider_service.sync.actor.*;

import java.util.HashMap;
import java.util.Map;

public class ActorManager {
    private static Logger LOG = LoggerFactory.getLogger(ActorManager.class);
    final private static ActorSystem system = ActorSystem.create(Constants.SYSTEM_ACTOR);

    private static Map<String, ActorRef> systemActors = new HashMap();

    public static ActorRef getActor(String name){
        if(!systemActors.containsKey(name)){
            createActor(name);
        }
        return systemActors.get(name);
    }

    private static void createActor(String actorName){
        switch (actorName){
            case Constants.USER_INTERG_ACTOR:{
                systemActors.put(actorName, system.actorOf(UserIntegrationAcountSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.RIDER_ACTOR:{
                systemActors.put(actorName, system.actorOf(RiderSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.USER_ACTOR:{
                systemActors.put(actorName, system.actorOf(UsersSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.TRANSPORT_TASK_ACTOR:{
                systemActors.put(actorName, system.actorOf(TransportTaskSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.TRANSPORTATION_TASK_ACTOR:{
                systemActors.put(actorName, system.actorOf(TransportationTaskSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.BATCH_TASK_ACTOR:{
                systemActors.put(actorName, system.actorOf(TaskBatchSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.RIDER_SHIFT_ACTOR:{
                systemActors.put(actorName, system.actorOf(RiderShiftSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.TASK_HISTORY_ACTOR:{
                systemActors.put(actorName,system.actorOf(TaskHistorySyncActor.props(),actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.META_FIELD_ACTOR:{
                systemActors.put(actorName, system.actorOf(MetaFieldsSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.TEAM_ACTOR:{
                systemActors.put(actorName, system.actorOf(TeamSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            case Constants.TOOKAN_AGENT_ACTOR: {
                systemActors.put(actorName,
                        system.actorOf(TookanAgentSyncActor.props(), actorName));
                LOG.info("Init akka actor {}", actorName);
                break;
            }
            default:{
                LOG.warn("Actor {} havent been defined yet");
            }
        }
    }

    public static ActorSystem getSystem(){
        return system;
    }

}
