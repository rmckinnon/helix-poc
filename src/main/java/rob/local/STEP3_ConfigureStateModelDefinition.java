package rob.local;

import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.MasterSlaveSMD;
import org.apache.helix.model.OnlineOfflineSMD;

public class STEP3_ConfigureStateModelDefinition {
    public static void main(String[] args) {
        ZKHelixAdmin admin = new ZKHelixAdmin(Conf.ZK_ADDRESS);

//        MasterSlaveSMD smd = new MasterSlaveSMD();
        OnlineOfflineSMD smd = new OnlineOfflineSMD();

//        StateModelDefinition.Builder builder = new StateModelDefinition.Builder(Conf.STATE_MODEL_NAME);
//        builder.addState(Conf.MASTER, 1)
//            .addState(Conf.SLAVE, 2)
//            .addState(Conf.OFFLINE, -1)
//            .addState(Conf.DROPPED)
//            .initialState(Conf.OFFLINE)
//            .addTransition(Conf.OFFLINE, Conf.SLAVE)
//            .addTransition(Conf.SLAVE, Conf.OFFLINE)
//            .addTransition(Conf.SLAVE, Conf.MASTER)
//            .addTransition(Conf.MASTER, Conf.SLAVE)
//            .addTransition(Conf.MASTER, Conf.DROPPED)
//            .addTransition(Conf.DROPPED, Conf.OFFLINE)
//            .upperBound(Conf.MASTER, 1)
//            .dynamicUpperBound(Conf.SLAVE, "R");

//        StateModelDefinition stateModelDefinition = builder.build();

        admin.addStateModelDef(Conf.CLUSTER_NAME, smd.name, smd);

    }
}

