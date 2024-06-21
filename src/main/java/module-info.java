module xmpl.chat_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires activemq.all;
    requires java.naming;


    opens xmpl.chat_client to javafx.fxml;
    opens xmpl.chat_client.gui to javafx.fxml;
    exports xmpl.chat_client;
    exports xmpl.chat_client.gui;
}