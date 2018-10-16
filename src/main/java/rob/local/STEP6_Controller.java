package rob.local;

import org.apache.helix.HelixManager;
import org.apache.helix.HelixManagerFactory;
import org.apache.helix.InstanceType;
import org.apache.helix.model.MasterSlaveSMD;
import org.apache.helix.model.Message;
import org.apache.helix.participant.StateMachineEngine;

public class STEP6_Controller {
    public static void main(String[] args) {
        try {
            HelixManager manager = HelixManagerFactory.getZKHelixManager(
                    Conf.CLUSTER_NAME,
                    "cnt_1",
                    InstanceType.CONTROLLER,
                    Conf.ZK_ADDRESS);

            manager.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
