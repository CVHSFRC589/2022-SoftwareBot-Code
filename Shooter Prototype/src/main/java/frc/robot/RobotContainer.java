// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ChangeShooterSpeed;
import frc.robot.commands.ResetAverageAmps;
import frc.robot.commands.SetShooterSpeed;
import frc.robot.commands.Shoot;
import frc.robot.commands.ToggleAverageAmps;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.Joystick;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Joystick m_joystick0 = new Joystick(0);

  private double m_shooterSpeed = .2;

  private final ShooterSubsystem m_shootSubsystem = new ShooterSubsystem();

  //private final ShooterSpeed m_autoCommand = new ShooterSpeed(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    m_shootSubsystem.setDefaultCommand(new Shoot(m_shootSubsystem));
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton j1MaxSpeed = new JoystickButton(m_joystick0, 1);
    j1MaxSpeed.whenPressed(new SetShooterSpeed(1, m_shootSubsystem));
    JoystickButton j1Stop = new JoystickButton(m_joystick0, 2);
    j1Stop.whenPressed(new SetShooterSpeed(0, m_shootSubsystem));
    JoystickButton j1Shoot = new JoystickButton(m_joystick0, 3);
    j1Shoot.whenPressed(new SetShooterSpeed(m_shooterSpeed, m_shootSubsystem));
    JoystickButton j1IncreaseSpeed = new JoystickButton(m_joystick0, 4);
    j1IncreaseSpeed.whenPressed(new ChangeShooterSpeed(.1, m_shootSubsystem));
    JoystickButton j1DecreaseSpeed = new JoystickButton(m_joystick0, 5);
    j1DecreaseSpeed.whenPressed(new ChangeShooterSpeed(-.1, m_shootSubsystem));
    JoystickButton j1ToggleAverageAmps = new JoystickButton(m_joystick0, 6);
    j1ToggleAverageAmps.whenPressed(new ToggleAverageAmps(m_shootSubsystem));
    JoystickButton j1ResetAverageAmps = new JoystickButton(m_joystick0, 7);
    j1ResetAverageAmps.whenPressed(new ResetAverageAmps(m_shootSubsystem));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
   public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
