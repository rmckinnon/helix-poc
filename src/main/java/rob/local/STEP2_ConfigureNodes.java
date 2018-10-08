package rob.local;

import org.apache.helix.manager.zk.ZKHelixAdmin;
import org.apache.helix.model.InstanceConfig;
import org.w3c.dom.ranges.Range;

import java.util.List;
import java.util.UUID;

public class STEP2_ConfigureNodes {
    public static void main(String[] args) {
        ZKHelixAdmin admin = new ZKHelixAdmin(Conf.ZK_ADDRESS);

        int numberOfPorts = 6;
        int startingPort = 7001;

        for (int i=0; i <=numberOfPorts; i++) {

            int port = startingPort++;

            InstanceConfig config = new InstanceConfig("localhost_" + port);
            config.setHostName("localhost");
            config.setPort(String.valueOf(port));
            config.setInstanceEnabled(true);

            // Add some configuration
            config.getRecord().setSimpleField("uuid", UUID.randomUUID().toString());
            admin.addInstance(Conf.CLUSTER_NAME, config);

        }
    }
}
