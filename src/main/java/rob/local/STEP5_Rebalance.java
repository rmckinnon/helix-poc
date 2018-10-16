package rob.local;

import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.IdealState;
import org.apache.helix.model.MasterSlaveSMD;

import java.util.Arrays;

public class STEP5_Rebalance {

    static String RESOURCE_NAME = "data";
    static int NUM_REPLICAS = 1;

    public static void main(String[] args) {
        ZKHelixAdmin admin = new ZKHelixAdmin(Conf.ZK_ADDRESS);

        IdealState idealState = admin.getResourceIdealState(Conf.CLUSTER_NAME, Conf.RESOURCE_NAME);
        idealState.setRebalanceMode(IdealState.RebalanceMode.USER_DEFINED);
        idealState.setRebalancerClassName(MyRebalancer.class.getName());
        admin.setResourceIdealState(Conf.CLUSTER_NAME, Conf.RESOURCE_NAME, idealState);

        admin.rebalance(Conf.CLUSTER_NAME, RESOURCE_NAME, NUM_REPLICAS);
    }
}
