package rob.local;

import org.apache.helix.NotificationContext;
import org.apache.helix.model.Message;
import org.apache.helix.participant.statemachine.StateModel;
import org.apache.helix.participant.statemachine.StateModelFactory;
import org.apache.helix.participant.statemachine.StateModelInfo;
import org.apache.helix.participant.statemachine.Transition;

public class MasterSlaveStateModelFactory extends StateModelFactory<StateModel> {
    @Override
    public StateModel createNewStateModel(String resourceName, String partitionName) {
        return new SlaveMasterStateModel();
    }

    @StateModelInfo(states = "{'OFFLINE', 'SLAVE', 'MASTER'}", initialState = "OFFLINE")
    public static class SlaveMasterStateModel extends StateModel {

        @Transition(from = "MASTER", to = "SLAVE")
        public void onBecomeSlaveFromMaster(Message message, NotificationContext notificationContext) {
            System.out.println("MasterSlaveStateModelFactory.onBecomeSlaveFromMaster()");
        }


        @Transition(from = "SLAVE", to = "MASTER")
        public void onBecomeMasterFromSlave(Message message, NotificationContext notificationContext) {
            System.out.println("MasterSlaveStateModelFactory.onBecomeMasterFromSlave()");
        }

        @Transition(from = "OFFLINE", to = "SLAVE")
        public void onBecomeSlaveFromOffline(Message message, NotificationContext notificationContext) {
            System.out.println("MasterSlaveStateModelFactory.onBecomeSlaveFromOffline()");
        }

        @Transition(from = "SLAVE", to = "OFFLINE")
        public void onBecomeOfflineFromSlave(Message message, NotificationContext notificationContext) {
            System.out.println("MasterSlaveStateModelFactory.onBecomeOfflineFromSlave()");
        }
    }
}
