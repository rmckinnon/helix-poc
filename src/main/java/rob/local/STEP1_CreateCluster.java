package rob.local;

import org.apache.helix.manager.zk.ZKHelixAdmin;

public class STEP1_CreateCluster {
    public static void main(String[] args) {
        ZKHelixAdmin admin = new ZKHelixAdmin(Conf.ZK_ADDRESS);
        admin.addCluster(Conf.CLUSTER_NAME);
    }
}
