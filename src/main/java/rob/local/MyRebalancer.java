package rob.local;


import org.apache.helix.HelixManager;
import org.apache.helix.controller.rebalancer.Rebalancer;
import org.apache.helix.controller.stages.ClusterDataCache;
import org.apache.helix.controller.stages.CurrentStateOutput;
import org.apache.helix.model.IdealState;

import java.util.Arrays;

public class MyRebalancer implements Rebalancer {

    HelixManager _manager;

    public void init(HelixManager manager) {
        _manager = manager;
    }

    public IdealState computeNewIdealState(String resourceName, IdealState currentIdealState, CurrentStateOutput currentStateOutput, ClusterDataCache clusterData) {

        currentIdealState.setPreferenceList("data_0", Arrays.asList("localhost_7001", "localhost_7002"));
        currentIdealState.setPreferenceList("data_1", Arrays.asList("localhost_7003", "localhost_7004"));

        return currentIdealState;
    }
}
