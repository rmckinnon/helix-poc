package rob.local;

import org.apache.helix.NotificationContext;
import org.apache.helix.model.Message;
import org.apache.helix.participant.statemachine.StateModel;
import org.apache.helix.participant.statemachine.StateModelFactory;
import org.apache.helix.participant.statemachine.StateModelInfo;
import org.apache.helix.participant.statemachine.Transition;

public class OnlineOfflineStateModelFactory extends StateModelFactory<StateModel> {
    @Override
    public StateModel createNewStateModel(String resourceName, String partitionName) {
        return new SlaveMasterStateModel();
    }

    @StateModelInfo(states = "{'OFFLINE', 'ONLINE'}", initialState = "OFFLINE")
    public static class SlaveMasterStateModel extends StateModel {

        @Transition(from = "OFFLINE", to = "ONLINE")
        public void onBecomeOnlineFromOffline(Message message, NotificationContext notificationContext) {
            System.out.println(".onBecomeOnlineFromOffline()");
        }

        @Transition(from = "ONLINE", to = "OFFLINE")
        public void onBecomeOfflineFromOnline(Message message, NotificationContext notificationContext) {
            System.out.println(".onBecomeOfflineFromOnline()");
        }
    }
}
