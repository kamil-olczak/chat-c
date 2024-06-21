package xmpl.chat_client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.function.Function;

public class ChatMessageListener implements MessageListener {
    private static Function<String, Integer> onReceive;

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                onReceive.apply(textMessage.getText());
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void setOnReceive(Function<String, Integer> onReceiveF){
        onReceive = onReceiveF;
    }
}
