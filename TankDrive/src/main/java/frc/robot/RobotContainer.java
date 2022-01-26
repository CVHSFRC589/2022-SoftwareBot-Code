// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
 
package frc.robot;
 
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
 
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSubsystem m_drivetrain = new DriveTrainSubsystem();
  private final ShooterSubsystem m_shooter= new ShooterSubsystem();
 
  //joysticks we are going to use
  private final Joystick m_joystick1 = new Joystick(0);
  private final Joystick m_joystick2 = new Joystick(1);
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_drivetrain.setDefaultCommand(
      new TankDrive(
         () -> m_joystick1.getY(), () -> 0, m_drivetrain
       )
    );
    m_shooter.setDefaultCommand(
       new ShooterJoystick(
          () -> m_joystick2.getY(), () -> 0, m_shooter
        )
     );
    //SmartDashboard.putNumber("LeftMotorEncoder", m_drivetrain.getLeft());
    SmartDashboard.putNumber("ShooterEncoder", m_shooter.getShooterSpeed());
    System.out.println("Shooterworkingyay: " + m_shooter.getShooterSpeed());
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
    JoystickButton j1SpeedIncr = new JoystickButton(m_joystick1, Constants.button3);
    j1SpeedIncr.whenPressed(new goFast(m_drivetrain));
    JoystickButton j1SpeedDecr = new JoystickButton(m_joystick1, Constants.button2);
    j1SpeedDecr.whenPressed(new goSlow(m_drivetrain));
    JoystickButton j1GoToDistance = new JoystickButton(m_joystick1, Constants.button6);
    j1GoToDistance.whenPressed(new goToDistance(78, m_drivetrain));
    JoystickButton j1SetDead= new JoystickButton(m_joystick1, Constants.button4);
    j1SetDead.whenPressed(new goFreeze(m_drivetrain));
    JoystickButton j1ToggleShooter= new JoystickButton(m_joystick1, Constants.button1);
    j1ToggleShooter.whenPressed(new toggleShooterMode(m_shooter));

    JoystickButton j2SpeedIncr = new JoystickButton(m_joystick2, Constants.button3);
    j2SpeedIncr.whenPressed(new goFast(m_drivetrain));
    JoystickButton j2SpeedDecr = new JoystickButton(m_joystick2, Constants.button2);
    j2SpeedDecr.whenPressed(new goSlow(m_drivetrain));
    JoystickButton j2SetDead= new JoystickButton(m_joystick2, Constants.button4);
    j2SetDead.whenPressed(new goFreeze(m_drivetrain));
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