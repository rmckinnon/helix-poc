package rob.local;


import org.apache.helix.ClusterMessagingService;
import org.apache.helix.Criteria;
import org.apache.helix.InstanceType;
import org.apache.helix.NotificationContext;
import org.apache.helix.messaging.handling.HelixTaskResult;
import org.apache.helix.messaging.handling.MessageHandler;
import org.apache.helix.messaging.handling.MessageHandlerFactory;
import org.apache.helix.model.Message;

import java.util.UUID;

public class MyMessageFactory implements MessageHandlerFactory {

    public MessageHandler createHandler(Message message, NotificationContext context) {
        return new MyMessageHandler(message, context);

    }

    public String getMessageType() {
        return Message.MessageType.USER_DEFINE_MSG.name();
    }

    public void reset() {

    }

    static class MyMessageHandler extends MessageHandler {

        public MyMessageHandler(Message message, NotificationContext context) {
            super(message, context);
        }

        public HelixTaskResult handleMessage() throws InterruptedException {
            Message msg = getMessage();
            System.out.println("working with message");
            System.out.println(String.format("sub message %s", msg.getMsgSubType()));

            ClusterMessagingService service = _notificationContext.getManager().getMessagingService();

            Message respmsg = new Message(Message.MessageType.TASK_REPLY.name(), UUID.randomUUID().toString());
            respmsg.setCorrelationId(msg.getCorrelationId());
            respmsg.setSrcInstanceType(msg.getSrcInstanceType());
            respmsg.setMsgState(Message.MessageState.NEW);

            String srcInstance = msg.getRecord().getSimpleField("SRC_NAME");

            Criteria criteria = new Criteria();
            criteria.setInstanceName(srcInstance);
            criteria.setRecipientInstanceType(InstanceType.SPECTATOR);


            int dest = service.send(criteria, respmsg);


            HelixTaskResult result = new HelixTaskResult();
            result.setMessage("hello");
            result.setSuccess(true);
            return result;
        }

        public void onError(Exception e, ErrorCode code, ErrorType type) {
            System.out.println("snap!");
        }
    }


}
