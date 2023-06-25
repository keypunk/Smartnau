package com.example.smartnau.model;

import junit.framework.TestCase;

public class RobotTest extends TestCase {

    public void testBye() {
        // Arrange
        Robot robot = new Robot();
        robot.bye();
        RobotStates expectedResult = RobotStates.BYE;

        // Act
        RobotStates actualResult = robot.getCurrentState();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    public void testOk() {
        // Arrange
        Robot robot = new Robot();
        robot.ok();
        RobotStates expectedResult = RobotStates.OK;

        // Act
        RobotStates actualResult = robot.getCurrentState();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    public void testIdle() {
        // Arrange
        Robot robot = new Robot();
        robot.idle();
        RobotStates expectedResult = RobotStates.IDLE;

        // Act
        RobotStates actualResult = robot.getCurrentState();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    public void testQuestion() {
        // Arrange
        Robot robot = new Robot();
        robot.question();
        RobotStates expectedResult = RobotStates.QUESTION;

        // Act
        RobotStates actualResult = robot.getCurrentState();

        // Assert
        assertEquals(expectedResult, actualResult);
    }

    public void testGetCurrentState() {
        // Arrange
        Robot robot = new Robot();
        RobotStates expectedResult = RobotStates.BOOT; // Default state when object is instantiated

        // Act
        RobotStates actualResult = robot.getCurrentState();

        // Assert
        assertEquals(expectedResult, actualResult);
    }
}