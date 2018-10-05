package rob.local;

import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.InstanceConfig;

import java.util.UUID;

public class STEP2_ConfigureNodes {
    public static void main(String[] args) {
        ZKHelixAdmin admin = new ZKHelixAdmin(Conf.ZK_ADDRESS);

        InstanceConfig config1 = new InstanceConfig("localhost_7001");
        config1.setHostName("localhost");
        config1.setPort("7001");
        config1.setInstanceEnabled(true);

        // Add some configuration
        config1.getRecord().setSimpleField("uuid", UUID.randomUUID().toString());
        admin.addInstance(Conf.CLUSTER_NAME, config1);


        InstanceConfig config2 = new InstanceConfig("localhost_7002");
        config2.setHostName("localhost");
        config2.setPort("7002");
        config2.setInstanceEnabled(true);

        // Add some configuration
        config2.getRecord().setSimpleField("uuid", UUID.randomUUID().toString());
        admin.addInstance(Conf.CLUSTER_NAME, config2);

        InstanceConfig config3 = new InstanceConfig("localhost_7003");
        config3.setHostName("localhost");
        config3.setPort("7003");
        config3.setInstanceEnabled(true);

        // Add some configuration
        config3.getRecord().setSimpleField("uuid", UUID.randomUUID().toString());
        admin.addInstance(Conf.CLUSTER_NAME, config3);
    }
}
