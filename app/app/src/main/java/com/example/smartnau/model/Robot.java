package com.example.smartnau.model;

public class Robot {
    private RobotStates currentState;

    public Robot() {
        currentState = RobotStates.BOOT;
    }

    public void bye() {
        currentState = RobotStates.BYE;
    }

    public void ok() {
        currentState = RobotStates.OK;
    }

    public void idle() {
        currentState = RobotStates.IDLE;
    }

    public void exclamationMark() {
        currentState = RobotStates.EXCLAMATION_MARK;
    }

    public void question() {
        currentState = RobotStates.QUESTION;
    }

    public RobotStates getCurrentState() {
        return currentState;
    }
}
