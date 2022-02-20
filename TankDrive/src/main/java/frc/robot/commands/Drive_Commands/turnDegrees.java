// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
package frc.robot.commands.Drive_Commands;
 
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSubsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
 
public class TurnDegrees extends CommandBase {
    private final DriveTrainSubsystem m_drivetrain;
    private double m_speed = 0;
    private double m_distanceInches = 0;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnDegrees(double speed, double degrees, DriveTrainSubsystem subsystem) {
    m_drivetrain = subsystem;
    m_speed = speed;
    m_distanceInches = degrees*Constants.ROBOT_TURN_CIRCUM/360;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }
 
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.reset();
  }
 
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    double direction = m_distanceInches/Math.abs(m_distanceInches);
    m_drivetrain.setMotors(-direction*m_speed, direction*m_speed);
  }
 
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.reset();
  }
 
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    System.out.println("Left Inches: "+m_drivetrain.getLeftEncoderInches());
    System.out.println("Requested Distance: "+m_distanceInches);
    return(m_drivetrain.getAverageEncoderInches() >= m_distanceInches-2*m_speed);
  }//TODO: Encoder distance resetting/not working properly
  
}
 


