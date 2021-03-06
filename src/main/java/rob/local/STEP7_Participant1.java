package rob.local;

import org.apache.helix.HelixManager;
import org.apache.helix.HelixManagerFactory;
import org.apache.helix.InstanceType;
import org.apache.helix.messaging.handling.AsyncCallbackService;
import org.apache.helix.model.MasterSlaveSMD;
import org.apache.helix.model.Message;
import org.apache.helix.model.OnlineOfflineSMD;
import org.apache.helix.participant.StateMachineEngine;

public class STEP7_Participant1 {
    public static void main(String[] args) {
        try {
            HelixManager manager = HelixManagerFactory.getZKHelixManager(
                    Conf.CLUSTER_NAME,
                    "localhost_7001",
                    InstanceType.PARTICIPANT,
                    Conf.ZK_ADDRESS);



            StateMachineEngine stateMachineEngine = manager.getStateMachineEngine();
            OnlineOfflineStateModelFactory stateModelFactory = new OnlineOfflineStateModelFactory();
            stateMachineEngine.registerStateModelFactory(OnlineOfflineSMD.name, stateModelFactory);

            manager.getMessagingService()
                    .registerMessageHandlerFactory(
                            Message.MessageType.USER_DEFINE_MSG.name(), new MyMessageFactory());


            manager.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
