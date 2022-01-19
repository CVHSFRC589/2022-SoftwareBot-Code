// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;
 
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.goSlow;
// import frc.robot.subsystems.ExampleSubsystem;
// import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
 
//import the classes we have created
import frc.robot.commands.TankDrive;
import frc.robot.commands.goFast;
import frc.robot.subsystems.DriveTrainSubsystem;
//import edu.wpi.first.wpilibj2.command.CommandBase;
 
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem m_drivetrain = new DriveTrainSubsystem();
 
 
  //joysticks we are going to use
  private final Joystick m_joystick1 = new Joystick(0);
  private final Joystick m_joystick2 = new Joystick(1);
  //j1
  final int speedUpJ1 = 3;
  final int slowDownJ1 = 2;
 
  //j2
  final int speedUpJ2 = 3;
  final int slowDownJ2 = 2;
 
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drivetrain.setDefaultCommand(
      new TankDrive(
        () -> m_joystick1.getY(), () -> m_joystick2.getY(), m_drivetrain
      )
    );
    // Configure the button bindings
    configureButtonBindings();
  }
 
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings()
  {
    JoystickButton j1SpeedIncr = new JoystickButton(m_joystick1, speedUpJ1);
    j1SpeedIncr.whenPressed(new goFast(m_drivetrain));
    JoystickButton j1SpeedDecr = new JoystickButton(m_joystick1, slowDownJ1);
    j1SpeedDecr.whenPressed(new goSlow(m_drivetrain));
 
    JoystickButton j2SpeedIncr = new JoystickButton(m_joystick2, speedUpJ2);
    j2SpeedIncr.whenPressed(new goFast(m_drivetrain));
    JoystickButton j2SpeedDecr = new JoystickButton(m_joystick2, slowDownJ2);
    j2SpeedDecr.whenPressed(new goSlow(m_drivetrain));
  }
 
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
 // }
 
}