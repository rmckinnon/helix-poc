package rob.local;


import org.apache.helix.HelixManager;
import org.apache.helix.controller.rebalancer.Rebalancer;
import org.apache.helix.controller.stages.ClusterDataCache;
import org.apache.helix.controller.stages.CurrentStateOutput;
import org.apache.helix.model.IdealState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyRebalancer implements Rebalancer {

    HelixManager _manager;

    public void init(HelixManager manager) {
        _manager = manager;
    }

    public IdealState computeNewIdealState(String resourceName, IdealState currentIdealState, CurrentStateOutput currentStateOutput, ClusterDataCache clusterData) {

        // Get the list of live participants in the cluster
        List<String> liveParticipants = new ArrayList<String>(clusterData.getLiveInstances().keySet());

        // Count the number of participants allowed to lock each lock (in this example, this is 1)
        int lockHolders = Integer.parseInt(currentIdealState.getReplicas());

        // Fairly assign the lock state to the participants using a simple mod-based sequential
        // assignment. For instance, if each lock can be held by 3 participants, lock 0 would be held
        // by participants (0, 1, 2), lock 1 would be held by (1, 2, 3), and so on, wrapping around the
        // number of participants as necessary.
        int i = 0;
        for (String partition : currentIdealState.getPartitionSet()) {
            List<String> preferenceList = new ArrayList<String>();
            for (int j = i; j < i + lockHolders; j++) {
                int participantIndex = j % liveParticipants.size();
                String participant = liveParticipants.get(participantIndex);
                // enforce that a participant can only have one instance of a given lock
                if (!preferenceList.contains(participant)) {
                    preferenceList.add(participant);
                }
            }
            currentIdealState.setPreferenceList(partition, preferenceList);
            i++;
        }

        return currentIdealState;
    }
}
