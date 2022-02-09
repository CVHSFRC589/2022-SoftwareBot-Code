// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;
 
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int kLeftMotorPort = 11; //11
    public static final int kRightMotorPort = 12; //12
    public static final int kShooterMotorPort = 20; //20
    public static final int INTAKE_MOTOR_PORT = 30;
    
    public static final int MAX_SHOOTER_SPEED_BUTTON = 1;
    public static final int SHOOTER_STOP_BUTTON = 2;
    public static final int START_SHOOTER_MOTOR_BUTTON = 3;
    public static final int INCREASE_SHOOTER_SPEED_BUTTON = 4;
    public static final int DECREASE_SHOOTER_SPEED_BUTTON = 5;
    public static final int TOGGLE_AVERAGE_SHOOTER_AMPS_BUTTON = 6;
    public static final int RESET_AVERAGE_SHOOTER_AMPS_BUTTON = 7;
    public static final int DECREASE_DRIVE_SPEED_BUTTON = 2;
    public static final int INCREASE_DRIVE_SPEED_BUTTON = 3;
    public static final int FREEZE_DRIVE_TRAIN_BUTTON = 4;
    public static final int AUTO_DRIVE_DISTANCE_BUTTON = 6;
    public static final int TOGGLE_DRIVE_STATE_BUTTON = 8;
    public static final int TURN_RIGHT_BUTTON = 9;
    public static final int EXTEND_CLIMBER_ARMS_BUTTON = 11;    
    public static final int RETRACT_CLIMBER_ARMS_BUTTON = 10;
    
    public static final int radius = 3;
    public static final double robotTurnCircum = 2*Math.PI*9.875;

    
    public static final double driveWheelCircum = 2*3.141592*radius;
    public static final double gearRatio = 10.71;
    public static final double SHOOTER_GEAR_RATIO = 1;
}