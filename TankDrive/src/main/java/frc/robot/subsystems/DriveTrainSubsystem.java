package frc.robot.subsystems;
 
//might want to check any methods using this
//import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxRelativeEncoder.Type;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
 
public class DriveTrainSubsystem extends SubsystemBase {
    //not entirely sure about these two??
    private final CANSparkMax m_leftMotor = new CANSparkMax(Constants.kLeftMotorPort, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(Constants.kRightMotorPort, MotorType.kBrushless);
 
    private final RelativeEncoder leftEncoder = m_leftMotor.getEncoder();
    private final RelativeEncoder rightEncoder = m_rightMotor.getEncoder();
 
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private double m_scaleFactor = 1.0;
 
    /**create a new drive train subsystem */
    public DriveTrainSubsystem() {
        super();
       
        leftEncoder.setPosition(0);
        rightEncoder.setPosition(0);
    }
 
    // public void drive(double left, double right) {
    //   m_drive.tankDrive(-left*m_scaleFactor, right*m_scaleFactor);
    // }
    public void drive(double both) {
      m_drive.tankDrive(-both*m_scaleFactor, both*m_scaleFactor);
    }
 
    public void goFast()
    {
        m_scaleFactor = 1.0;
    }
 
    public void goSlow()
    {
        m_scaleFactor = 0.5;
    }
 
    public void driveFiveFeet()
    {
      // //m_scaleFactor = 0.1;
      if(getLeft()<=60){
        m_leftMotor.set(0.5);
        m_rightMotor.set(-0.5);
        System.out.println("Hi");
      }
    }
 
    public void goFreeze()
    /**does not actually kill the robot */
    {
      m_scaleFactor = 0;
      //m_leftMotor.set(0.0);
      //m_rightMotor.set(0.0);
    }
    public double getLeft()
    {
        double k = leftEncoder.getPosition();
        System.out.println(k);
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