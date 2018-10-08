package rob.local;

import org.apache.helix.*;
import org.apache.helix.examples.BootstrapHandler;
import org.apache.helix.messaging.AsyncCallback;
import org.apache.helix.model.Message;

import java.util.UUID;


public class STEP8_SendMessage {
    public static void main(String[] args) {
        try {
            HelixManager manager = HelixManagerFactory.getZKHelixManager(
                    Conf.CLUSTER_NAME,
                    "spec_2",
                    InstanceType.SPECTATOR,
                    Conf.ZK_ADDRESS);

            manager.connect();

            ClusterMessagingService service = manager.getMessagingService();


            Message msg = new Message(Message.MessageType.USER_DEFINE_MSG.name(), UUID.randomUUID().toString());
            msg.setMsgState(Message.MessageState.NEW);
            msg.setMsgSubType("mem");

            Criteria criteria = new Criteria();
            criteria.setInstanceName("localhost_7001");
            criteria.setRecipientInstanceType(InstanceType.PARTICIPANT);
            criteria.setResource("data");
            criteria.setPartition("");
            criteria.setSessionSpecific(true);


            int timeout = 300000;

            MyCallBack myCallBack = new MyCallBack();

            int sentMessageCount = service.sendAndWait(criteria, msg, myCallBack, timeout);
            System.out.println(String.format("messages sent %d", sentMessageCount));

            manager.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("exit");
    }


    static class MyCallBack extends AsyncCallback {

        public void onTimeOut() {
            System.out.println("Timeout occurred!!!");

        }

        public void onReplyMessage(Message message) {
            System.out.println("Got message back");
        }
    }


}
