package xmpl.chat_client.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import xmpl.chat_client.ChatClient;
import xmpl.chat_client.ChatMessageListener;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.IOException;

public class LoginCtrl{

    @FXML
    private Label info;

    @FXML
    private TextField nickName;

    @FXML
    void login(ActionEvent event) throws JMSException, NamingException, IOException {
        if(nickName.getText().isEmpty()){
            info.setText("Podaj nickname");
        } else {
            if(ChatClient.runChatClient(nickName.getText())) {
                SceneCtrl.switchToChat(event);
            } else {
                info.setText("Nickname zajęty");
            }
        }
    }

    @FXML
    public void loginKB(KeyEvent event) throws JMSException, NamingException, IOException {
        if (event.getCode() == KeyCode.ENTER) {
            if(nickName.getText().isEmpty()){
                info.setText("Podaj nickName");
            } else {
                if (ChatClient.runChatClient(nickName.getText())) {
                    SceneCtrl.switchToChat(event);
                } else {
                    info.setText("Nickname zajęty");
                }
            }
        }
    }
}
