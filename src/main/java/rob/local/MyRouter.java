package rob.local;

import org.apache.helix.NotificationContext;
import org.apache.helix.model.ExternalView;
import org.apache.helix.model.InstanceConfig;
import org.apache.helix.model.LiveInstance;
import org.apache.helix.spectator.RoutingTableProvider;

import java.util.List;

public class MyRouter extends RoutingTableProvider {

    private final InstanceConfig localInstanceConfig;
    private final String resourceName;
    private final String partition;

    public MyRouter(InstanceConfig localInstanceConfig, String resourceName, String partition) {
        this.localInstanceConfig = localInstanceConfig;
        this.resourceName = resourceName;
        this.partition = partition;
    }


    Object makeCall() {

        List<InstanceConfig> instances = getInstances(resourceName, partition, "MASTER");

        System.out.println("size: " + instances.size());
        return null;
    }


    @Override
    public void onExternalViewChange(List<ExternalView> externalViewList, NotificationContext changeContext) {
        System.out.println("external view change");

//            List<InstanceConfig> list = getInstances("data2", "data2_0",
//                    "SLAVE");
//
//            System.out.println(list.size());
    }

    @Override
    public void onConfigChange(List<InstanceConfig> configs, NotificationContext changeContext) {
        System.out.println("config change");
    }

    @Override
    public void onInstanceConfigChange(List<InstanceConfig> configs, NotificationContext changeContext) {
        System.out.println("instance config change");
    }

    @Override
    public void onLiveInstanceChange(List<LiveInstance> liveInstances, NotificationContext changeContext) {
        System.out.println("live instance change");
    }

}
