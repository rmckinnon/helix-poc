package rob.local;

import org.apache.helix.*;
import org.apache.helix.model.ExternalView;
import org.apache.helix.model.InstanceConfig;
import org.apache.helix.model.LiveInstance;
import org.apache.helix.spectator.RoutingTableProvider;

import java.util.List;


public class STEP8_Spectator {
    public static void main(String[] args) {
        try {
            HelixManager manager = HelixManagerFactory.getZKHelixManager(
                    Conf.CLUSTER_NAME,
                    "spec_1",
                    InstanceType.SPECTATOR,
                    Conf.ZK_ADDRESS);

            manager.connect();



            //RoutingTableProvider routingTableProvider = new RoutingTableProvider();
//            RoutingTableProvider routingTableProvider = new RoutingTableProvider(manager, PropertyType.EXTERNALVIEW);
            RoutingTableProvider routingTableProvider = new RoutingTableProvider(manager);

            manager.addExternalViewChangeListener(routingTableProvider);

            List<InstanceConfig> masterList = routingTableProvider.getInstances("data", "data_0",
                    "MASTER");

            List<InstanceConfig> slaveList = routingTableProvider.getInstances("data", "data_0",
                    "SLAVE");

            List<InstanceConfig> list = masterList;
            list.addAll(slaveList);

            System.out.println(list.size()); // This is coming back as zero

            System.out.println(manager.getClusterManagmentTool()
                    .getResourceExternalView(Conf.CLUSTER_NAME, "data"));



//            String clusterName = manager.getClusterName();
//            String instanceName = manager.getInstanceName();
//            InstanceConfig instanceConfig = manager.getClusterManagmentTool().getInstanceConfig(clusterName, "localhost_7001");
//
//
//
//
//
//            MyRouter myRouter = new MyRouter(instanceConfig, "data", "data_0" );
//            manager.addExternalViewChangeListener(myRouter);
//
//            myRouter.makeCall();




        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("exit");
    }


}
