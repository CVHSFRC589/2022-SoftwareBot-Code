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
    private boolean m_normal = true;
    private double m_scaleFactor = 0.25;
    private double m_driveInches = 0;
 
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
      if(m_normal)
      {
        m_drive.tankDrive(-both*m_scaleFactor, both*m_scaleFactor);
      }
      else
      {
        goToDistance();
      }
    }
 
    public void goFast()
    {
        m_scaleFactor = 1.0;
    }
 
    public void goSlow()
    {
        m_scaleFactor = 0.5;
    }

    // public void goFiveFeet()
    // {
    //   // //m_scaleFactor = 0.1;
    //   if(getLeft()<=60){ 
    //     m_leftMotor.set(0.25);
    //     m_rightMotor.set(-0.25);
    //     System.out.println("Hi");
    //   }
    // }
    public void goToDistance()
    {
     // m_driveInches = distanceInches;
      if(getLeft()<= m_driveInches){ 
        m_leftMotor.set(0.25);
        m_rightMotor.set(-0.25);
        System.out.println("it is working");
      }
    }
    public void driveToDistance(double distanceInches)
    {
      m_normal = false;
      leftEncoder.setPosition(0);
      rightEncoder.setPosition(0);
      m_driveInches = distanceInches;
    }
    public void driveFiveFeet()
    {
      m_normal = false;
      leftEncoder.setPosition(0);
      rightEncoder.setPosition(0);
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
        double distanceInches = leftEncoder.getPosition()*Constants.driveWheelCircum/Constants.gearRatio;
        System.out.println(distanceInches);
        return distanceInches;
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