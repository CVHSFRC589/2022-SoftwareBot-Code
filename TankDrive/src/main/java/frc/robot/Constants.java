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
    
    public static final int button1 = 1;
    public static final int button2 = 2;
    public static final int button3 = 3;
    public static final int button4 = 4;
    public static final int button6 = 6;
    public static final int button9 = 9;    
    public static final int button11 = 11;
    
    public static final int radius = 3;
    
    public static final double driveWheelCircum = 2*3.141592*radius;
    public static final double gearRatio = 10.71;
}