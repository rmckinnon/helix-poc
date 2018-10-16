package rob.local;

import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.MasterSlaveSMD;
import org.apache.helix.model.OnlineOfflineSMD;

public class STEP3_ConfigureStateModelDefinition {
    public static void main(String[] args) {
        ZKHelixAdmin admin = new ZKHelixAdmin(Conf.ZK_ADDRESS);
        OnlineOfflineSMD smd = new OnlineOfflineSMD();
        admin.addStateModelDef(Conf.CLUSTER_NAME, smd.name, smd);
    }
}

