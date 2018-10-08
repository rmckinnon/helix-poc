package rob.local;

import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.MasterSlaveSMD;

public class STEP5_Rebalance {

    static String RESOURCE_NAME = "data";
    static int NUM_REPLICAS = 2;

    public static void main(String[] args) {
        ZKHelixAdmin admin = new ZKHelixAdmin(Conf.ZK_ADDRESS);

        admin.rebalance(Conf.CLUSTER_NAME, RESOURCE_NAME, NUM_REPLICAS);
    }
}
