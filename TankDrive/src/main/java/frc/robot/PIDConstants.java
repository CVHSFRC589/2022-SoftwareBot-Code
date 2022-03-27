// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class PIDConstants {
    //Shooter PID Values
    public static final double SHOOTER_P = 0.0002; // 2.25 :)
    public static final double SHOOTER_I = 0.0000005;
    public static final double SHOOTER_D = 0;
    public static final double SHOOTER_Iz = 0;
    public static final double SHOOTER_FF = 0;
    public static final double SHOOTER_MAX_OUTPUT = 1;
    public static final double SHOOTER_MIN_OUTPUT = -1;
    
    //Feeder PID Values
    public static final double FEEDER_P = 0.0002; // 2.25 :)
    public static final double FEEDER_I = 0.0000005;
    public static final double FEEDER_D = 0;
    public static final double FEEDER_Iz = 0;
    public static final double FEEDER_FF = 0;
    public static final double FEEDER_MAX_OUTPUT = 1;
    public static final double FEEDER_MIN_OUTPUT = -1;
    
    //Drive PID Values
    public static final double DRIVE_P = 0.0002; // 2.25 :)
    public static final double DRIVE_I = 0.0000005;
    public static final double DRIVE_D = 0;
    public static final double DRIVE_Iz = 0;
    public static final double DRIVE_FF = 0;
    public static final double DRIVE_MAX_OUTPUT = 1;
    public static final double DRIVE_MIN_OUTPUT = -1;

}