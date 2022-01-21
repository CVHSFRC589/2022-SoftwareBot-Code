package frc.robot.subsystems;
 
//might want to check any methods using this
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
 
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 
public class DriveTrainSubsystem extends SubsystemBase {
    private final CANSparkMax m_leftMotor = new CANSparkMax(Constants.kLeftMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(Constants.kRightMotorPort, MotorType.kBrushless);

    private final CANEncoder leftEncoder = m_leftMotor.getEncoder(EncoderType.kHallSensor, 42);
    private final CANEncoder rightEncoder = m_rightMotor.getEncoder(EncoderType.kHallSensor, 42);
 
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private final double encoderPerInch = 1;
    private double m_scaleFactor = 1.0;
 
    /**create a new drive train subsystem */
    public DriveTrainSubsystem() {

       leftEncoder.setPosition(0);
       rightEncoder.setPosition(0);

      
      super();
    }
 
    public void drive(double left, double right) {
      // left = Constants.LeftSpeed/m_scaleFactor;
      // right = Constants.RightSpeed/m_scaleFactor;
 
      m_drive.tankDrive(-left*m_scaleFactor, right*m_scaleFactor);
    }
 
    public void goFast()
    {
        m_scaleFactor = 1.0;
    }
 
    public void goSlow()
    {
        m_scaleFactor = 0.5;
    }

    public double getLeft()
    {
        return leftEncoder.getPosition();
    }

    public double getRight()
    {
        return rightEncoder.getPosition();
    }

    public void setLeft(int position)
    {
        leftEncoder.setPosition(position);
    }

    public void setRight(int position)
    {
        rightEncoder.setPosition(position);
    }
      /** Call log method every loop. */
  @Override
  public void periodic() {
    //log();
  }
}