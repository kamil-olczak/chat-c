package xmpl.chat_client;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Locale;

public class ChatClient {
    private static Connection connection;
    private static Session session;
    private static MessageProducer sender;
    private static MessageConsumer receiver;
    private static String nickName;

    public static boolean runChatClient(String nick) throws JMSException, NamingException {
        Hashtable env = new Hashtable(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        env.put("topic.myTopic", "myTopic");
        Context context = new InitialContext(env);

        ConnectionFactory fact = (ConnectionFactory) context.lookup("ConnectionFactory");
        String admDestName = "myTopic";
        Destination dest = (Destination) context.lookup(admDestName);
        nickName = nick;
        connection = fact.createConnection();
        try {
            connection.setClientID(nickName);
        } catch (InvalidClientIDException e) {
            e.printStackTrace();
            return false;
        }
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        connection.start();
        sender = session.createProducer(dest);
        receiver = session.createConsumer(dest);
        MessageListener listener = new ChatMessageListener();
        receiver.setMessageListener(listener);
        return true;
    }

    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("Closing connection.");
        } catch (JMSException | NullPointerException e){
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void send(String msg) throws JMSException {
        TextMessage textMessage = session.createTextMessage();
        textMessage.setText(nickName + ": " + msg);
        sender.send(textMessage);
    }
}
