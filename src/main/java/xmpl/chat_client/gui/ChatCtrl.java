package xmpl.chat_client.gui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import xmpl.chat_client.ChatClient;
import xmpl.chat_client.ChatMessageListener;

import javax.jms.JMSException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatCtrl implements Initializable {
    @FXML
    protected TextArea chat;
    @FXML
    protected TextField message;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChatMessageListener.setOnReceive((msg) -> {
            chat.setText(chat.getText() + msg + "\n");
            return 0;
        });
    }

    @FXML
    public void send(ActionEvent event) throws JMSException {
        ChatClient.send(message.getText());
        message.clear();
    }

    @FXML
    public void sendKB(KeyEvent event) throws JMSException {
        if(event.getCode() == KeyCode.ENTER){
            ChatClient.send(message.getText());
            message.clear();
        }
    }

}