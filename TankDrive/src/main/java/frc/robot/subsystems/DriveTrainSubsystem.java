package frc.robot.subsystems;
 
//might want to check any methods using this
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 
public class DriveTrainSubsystem extends SubsystemBase {
    private final CANSparkMax m_leftMotor = new CANSparkMax(Constants.kLeftMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(Constants.kRightMotorPort, MotorType.kBrushless);

    private final RelativeEncoder leftEncoder = m_leftMotor.getEncoder();
    private final RelativeEncoder rightEncoder = m_rightMotor.getEncoder();
 
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private static BooleanSupplier driveType = () -> true;

    private double m_scaleFactor = 0.8;
 
    /**create a new drive train subsystem */
    public DriveTrainSubsystem() {
        super();

        m_leftMotor.setInverted(true);
        m_rightMotor.setInverted(false); //swap inverted
        reset();
    }
 
    public void drive(double left, double right) {
      if(driveType.getAsBoolean())
      {
        m_drive.tankDrive(left*m_scaleFactor, right*m_scaleFactor);
      }
      else
      {
        m_drive.arcadeDrive(left*m_scaleFactor, -right*m_scaleFactor);
      }
    }

    public void reset(){
      setLeftEncoder(0);
      setRightEncoder(0);
      setMotors(0,0);
      m_scaleFactor = .5;
    }

    public void setMotors(double leftSpeed, double rightSpeed){
      m_leftMotor.set(leftSpeed);
      m_rightMotor.set(rightSpeed);
    }

    public void tankDrive(double leftSpeed, double rightSpeed){
      m_drive.tankDrive(leftSpeed, rightSpeed);
    }
    
    public void setScaleFactor(double scaleFactor){
      m_scaleFactor = scaleFactor;
    }

    public void switchDrive() {
      if(driveType.getAsBoolean()) 
          driveType = () -> false;
      else
          driveType = () -> true;
    }

    public BooleanSupplier getDriveType(){
      return driveType;
    }

    public double getLeftEncoderInches()
    {
        double distanceInches = leftEncoder.getPosition()*Constants.driveWheelCircum/Constants.gearRatio;
        return distanceInches;
    }

    public double getRightEncoderInches()
    {
        return rightEncoder.getPosition()*Constants.driveWheelCircum/Constants.gearRatio;
    }

    public double getAverageEncoderInches(){
      double avgEncoderInches = (Math.abs(getLeftEncoderInches())+Math.abs(getLeftEncoderInches()))/2;
      SmartDashboard.putNumber("AvgEncoderInches", avgEncoderInches);
      return avgEncoderInches;
    }

    private void setLeftEncoder(int position)
    {
        leftEncoder.setPosition(position);
    }

    private void setRightEncoder(int position)
    {
        rightEncoder.setPosition(position);
    }

      /** Call log method every loop. */
  @Override
  public void periodic() {
    //log();
  }
}