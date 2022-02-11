// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import frc.robot.Constants;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystemyay. */
  private final DoubleSolenoid m_leftArm;
  private final DoubleSolenoid m_rightArm;
  private final CANSparkMax m_intakeMotor = new CANSparkMax(Constants.INTAKE_MOTOR_PORT, MotorType.kBrushless);;

  public IntakeSubsystem() {
    m_leftArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_LEFT_ARM_ON, Constants.INTAKE_LEFT_ARM_OFF);
    m_rightArm = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_RIGHT_ARM_ON, Constants.INTAKE_RIGHT_ARM_OFF);
  }

  public void toggleIntake(){
    if (DoubleSolenoid.Value.kOff == m_leftArm.get()){
      extendIntake();
      
    }
   else if (m_rightArm.get() == m_leftArm.get()){
      m_leftArm.toggle();
      m_rightArm.toggle();
    }
  }

  public void extendIntake()
  {
    m_leftArm.set(DoubleSolenoid.Value.kForward);
    m_rightArm.set(DoubleSolenoid.Value.kForward);
  }

  public void retractIntake()
  {
    m_leftArm.set(DoubleSolenoid.Value.kReverse);
    m_rightArm.set(DoubleSolenoid.Value.kReverse);
  }

  public void startMotor(double speed)
  {
    m_intakeMotor.set(speed);
  }

  public void stopMotor()
  {
    m_intakeMotor.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
