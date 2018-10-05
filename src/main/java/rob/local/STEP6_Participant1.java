package rob.local;

import org.apache.helix.HelixManager;
import org.apache.helix.HelixManagerFactory;
import org.apache.helix.InstanceType;
import org.apache.helix.model.MasterSlaveSMD;
import org.apache.helix.participant.StateMachineEngine;

public class STEP6_Participant1 {
    public static void main(String[] args) {
        try {
            HelixManager manager = HelixManagerFactory.getZKHelixManager(
                    Conf.CLUSTER_NAME,
                    "localhost_7001",
                    InstanceType.PARTICIPANT,
                    Conf.ZK_ADDRESS);

            StateMachineEngine stateMachineEngine = manager.getStateMachineEngine();

            MasterSlaveStateModelFactory stateModelFactory = new MasterSlaveStateModelFactory();
            stateMachineEngine.registerStateModelFactory(MasterSlaveSMD.name, stateModelFactory);
            manager.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
