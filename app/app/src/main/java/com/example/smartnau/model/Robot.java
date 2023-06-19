package com.example.smartnau.model;

public class Robot {
    private RobotStates currentState;

    public Robot() {
        currentState = RobotStates.GREET;
    }

    public void bowOut() {
        currentState = RobotStates.BOW_OUT;
    }

    public void thumbsUp() {
        currentState = RobotStates.THUMBS_UP;
    }

    public void idle() {
        currentState = RobotStates.IDLE;
    }

    public void wave() {
        currentState = RobotStates.WAVE;
    }

    public void question() {
        currentState = RobotStates.QUESTION;
    }

    public RobotStates getCurrentState() {
        return currentState;
    }
}
