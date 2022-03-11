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
    //Motor/Solenoid Ports
    public static final int DRIVE_LEFT_MOTOR_PORT = 11; //11  
    public static final int DRIVE_RIGHT_MOTOR_PORT = 12; //12
    public static final int ULTRASONIC_SENSOR_CHANNEL = 0;//Arbitrary
    public static final int ULTRASONIC_VOLTAGE_MULTIPLIER = 1;//Arbitrary

    public static final int SHOOTER_MOTOR_PORT = 21; //21 Revisit ports  
    public static final int FEEDER_MOTOR_PORT = 20; //20     

    public static final int INTAKE_MOTOR_PORT = 30; //30  //Revisit ports
    public static final int VISUAL_FEEDBACK_MOTOR_PORT = 9; //PWM port 9 on both

    public static final int CLIMBER_LEFT_ARM_ON = 1;
    public static final int CLIMBER_LEFT_ARM_OFF = 0;
    public static final int CLIMBER_RIGHT_ARM_ON = 3;
    public static final int CLIMBER_RIGHT_ARM_OFF = 2;
    
    public static final int INTAKE_LEFT_ARM_ON = 4;
    public static final int INTAKE_LEFT_ARM_OFF = 5;
    public static final int INTAKE_RIGHT_ARM_ON = 6;
    public static final int INTAKE_RIGHT_ARM_OFF = 7;

    

    //Joystick 0 Buttons
    public static final int SHOOTER_FEEDER_MOTOR_BUTTON = 1;
    public static final int FEED_ONE_BALL_BUTTON = 2;
    public static final int REVERSE_FEEDER_MOTOR_BUTTON = 3;
    public static final int FACE_TARGET_BUTTON = 4;
    public static final int GO_TO_TARGET_DISTANCE_BUTTON = 5;
    public static final int AUTO_DRIVE_DISTANCE_BUTTON = 6;
    public static final int TOGGLE_LED_BUTTON = 7;
    public static final int TOGGLE_DRIVE_STATE_BUTTON = 8;
    public static final int FACE_TARGET_CONTINUOUS_BUTTON = 9;
    // public static final int EMPTY_BUTTON = 10; DO NOT USE
    public static final int STOP_DRIVE_TRAIN_BUTTON = 11;


    //Joystick 2 Buttons
    public static final int TOGGLE_SHOOTING_BUTTON = 1;
    public static final int SPIT_OUT_BALL_BUTTON = 2;
    public static final int LIMELIGHT_RPM_SHOOT_BUTTON = 3;
    public static final int CLOSE_SHOOTING_BUTTON = 4;
    public static final int FAR_SHOOTING_BUTTON = 5;
    public static final int STOP_SHOOTER_BUTTON = 6;
    public static final int EAT_BALL_BUTTON = 7;
    // public static final int TOGGLE_INTAKE_ARMS_BUTTON = 8; 
    // public static final int TOGGLE_INTAKE_MOTOR_BUTTON = 9;
    // public static final int RETRACT_CLIMBER_ARMS_BUTTON = 10; DO NOT USE
    public static final int TOGGLE_CLIMBER_ARMS_BUTTON = 11;
    
    
    

    //Robot Measurement Constants
    public static final int DRIVE_WHEEL_RADIUS = 3;
    public static final double ROBOT_TURN_CIRCUM = Math.PI*2*11;
    public static final double SOFIE_TURN_CIRCUM = Math.PI*2*9.875;
    public static final double DRIVE_WHEEL_CIRCUM = 2*Math.PI*DRIVE_WHEEL_RADIUS;
    public static final double DRIVE_GEAR_RATIO = 10.71;
    public static final double SHOOTER_GEAR_RATIO = 1.25;

    public static final double HUB_HEIGHT = 108;//108
    public static final double LIMELIGHT_HEIGHT = 26.5;
    public static final double LIMELIGHT_MOUNT_ANGLE = 32;

    //misc
    public static final String NETWORK_TABLE_NAME = "Visual Feedback";
    public static final String VISUAL_FEEDBACK_TABLE_ENTRY_NAME = "VF Pattern";
    public static final String PATTERN_FINISHED_ENTRY_NAME = "VF Pattern done";
    public static final String SHOOTER_RPM_ENTRY_NAME = "Shooter RPM";
    public static final String LIMELIGHT_PIPELINE_ENTRY_NAME = "new pipeline number";
    public static final double DRIVE_STARTING_SCALE_FACTOR = 1;
    
    
    //Shooting Constants
    public static final double MAX_SHOOTER_RPM = 4900.0;
    public static final double STARTING_SHOOTER_RPM = 2200;
    public static final double CLOSE_SHOOTING_RPM = 1800;   //x ft from hub
    public static final double FAR_SHOOTING_RPM = 2200; //z ft from hub
    public static final double FEEDER_MOTOR_SPEED = 0.3;
    public static final double SHOOTING_LEVER_RPM_MULTIPLIER = -300;
    public static final double SHOOTING_DISTANCE = 60; //Arbitrary num, We dont have a shooter yet; 60

    public static final double kP = 0.0002; //2.25 :)
    public static final double kI = 0.0000005;
    public static final double kD = 0; 
    public static final double kIz = 0; 
    public static final double kFF = 0;
    public static final double kMaxOutput = 1; 
    public static final double kMinOutput = -1;
}