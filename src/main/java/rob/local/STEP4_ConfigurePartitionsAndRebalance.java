package rob.local;

import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.MasterSlaveSMD;

public class STEP4_ConfigurePartitionsAndRebalance {

    static String RESOURCE_NAME = "data";
    static int NUM_PARTITIONS = 6;
    static String MODE = "SEMI_AUTO";

    public static void main(String[] args) {
        ZKHelixAdmin admin = new ZKHelixAdmin(Conf.ZK_ADDRESS);

        admin.addResource(Conf.CLUSTER_NAME, RESOURCE_NAME, NUM_PARTITIONS, MasterSlaveSMD.name, MODE);
    }
}
