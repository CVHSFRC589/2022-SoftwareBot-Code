// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber_Commands;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.PatternMap;

public class RetractBothArms extends CommandBase {
  /** Creates a new raiseBothArms. */
  private ClimberSubsystem m_climber;
  private NetworkTable m_table;
  private NetworkTableEntry m_pattern;
  private NetworkTableEntry m_patternOver;
  //private double[] patterns = {-0.97, -0.95, -0.93, -0.89, -0.79, -0.61, -0.57, -0.53, -0.41, -0.29, -0.21, -0.17, -0.07, -0.01, 0.13};
  private PatternMap patterns = new PatternMap();
  private int iterator = 0;

  public RetractBothArms(ClimberSubsystem subsystem) {
    m_climber=subsystem;
    m_table = NetworkTableInstance.getDefault().getTable(Constants.NETWORK_TABLE_NAME);
    m_pattern = m_table.getEntry(Constants.VISUAL_FEEDBACK_TABLE_ENTRY_NAME);
    m_patternOver = m_table.getEntry(Constants.PATTERN_FINISHED_ENTRY_NAME);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    m_climber.pullLeft();
    m_climber.pullRight();
    m_patternOver.setString("nope");
    m_pattern.setString(patterns.getPattern(iterator));
    if(iterator<patterns.getPatternsNum()) { //Number of 5V patterns
      iterator++;
    } else {iterator=0;}      //TODO: Color cycle
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
