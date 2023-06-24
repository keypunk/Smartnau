package com.example.smartnau.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.smartnau.model.BaseMessage;
import com.example.smartnau.model.MqttClient;
import com.example.smartnau.model.Publish;
import com.example.smartnau.model.RobotMessage;
import com.example.smartnau.model.Subscribe;
import com.example.smartnau.model.UserMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {
    private List<BaseMessage> mMessageList = new ArrayList<BaseMessage>();
    private Subscribe subscribe = new Subscribe(new MqttClient());
    private Publish publish = new Publish(new MqttClient());

    public List<BaseMessage> getMessageList() {
        return mMessageList;
    }

    public void addMessage(BaseMessage message) {
        mMessageList.add(message);
    }

    public void sendMessage(String message) throws Exception {
        if (!message.equals("")){
            UserMessage emessage = new UserMessage();
            emessage.setMessage(message);
            emessage.setCreatedAt(System.currentTimeMillis());
            mMessageList.add(emessage);
        } else {
            throw new Exception("Message is empty");
        }
    }

    public void greetUser() {
        if (mMessageList.size() == 0) {
            RobotMessage greetingMessage = new RobotMessage();
            greetingMessage.setMessage("Hello fellow human. My name is E42 and i was created to ask questions for you. I'm located at Finkenau Campus and can survey about any topic you're interested in.");
            greetingMessage.setCreatedAt(System.currentTimeMillis());
            RobotMessage greetingMessage2 = new RobotMessage();
            greetingMessage2.setMessage("What do you want to know?");
            greetingMessage2.setCreatedAt(System.currentTimeMillis());
            mMessageList.add(greetingMessage);
            mMessageList.add(greetingMessage2);
        }
    }

}
