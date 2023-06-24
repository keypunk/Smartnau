package com.example.smartnau.viewmodels;

import com.example.smartnau.model.BaseMessage;
import com.example.smartnau.model.RobotMessage;
import com.example.smartnau.model.UserMessage;

import junit.framework.TestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModelTest extends TestCase {
    ChatViewModel chatViewModel = new ChatViewModel();



    public void testAddMessage() {
        BaseMessage message = new BaseMessage();
        message.setMessage("Testing addMessage...");
        chatViewModel.addMessage(message);

        BaseMessage actual = chatViewModel.getMessageList().get(0);
        BaseMessage expected = message;

        assertEquals(expected,actual);
    }

    @Test
    public void testGetMessageList() {

        BaseMessage message = new BaseMessage();
        List<BaseMessage> expected = new ArrayList<BaseMessage>();
        List<BaseMessage> actual = chatViewModel.getMessageList();

        expected.add(message);
        chatViewModel.addMessage(message);

        assertEquals(expected, actual);
    }

    public void testSendMessage() throws Exception {
        UserMessage message = new UserMessage();
        message.setMessage("Testing sendMessage...");
        message.setCreatedAt(System.currentTimeMillis());

        chatViewModel.sendMessage(message.getMessage());
        String actual = chatViewModel.getMessageList().get(0).getMessage();
        String expected = message.getMessage();

        assertEquals(expected,actual);
    }

    public void testGreetUser() {
        RobotMessage message = new RobotMessage();
        String expected = String.valueOf(message.getClass());

        chatViewModel.greetUser();
        String actual = String.valueOf(chatViewModel.getMessageList().get(0).getClass());

        assertEquals(expected,actual);
    }
}