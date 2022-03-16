package frc.robot.subsystems;
 
//might want to check any methods using this
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.LimeLightAiming;
import frc.robot.ControlMode.StreamType;
 
public class DriveTrainSubsystem extends SubsystemBase {
    private final CANSparkMax m_leftMotor = new CANSparkMax(Constants.DRIVE_LEFT_MOTOR_PORT, MotorType.kBrushless);
    private final CANSparkMax m_rightMotor = new CANSparkMax(Constants.DRIVE_RIGHT_MOTOR_PORT, MotorType.kBrushless);

    private final RelativeEncoder leftEncoder = m_leftMotor.getEncoder();
    private final RelativeEncoder rightEncoder = m_rightMotor.getEncoder();

    private final SparkMaxPIDController m_leftPIDController = m_leftMotor.getPIDController();
    private final SparkMaxPIDController m_rightPIDController = m_rightMotor.getPIDController();

 
    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    private static BooleanSupplier driveType = () -> false;
    private static LimeLightAiming m_aiming = new LimeLightAiming();
    private static AnalogInput m_sonic = new AnalogInput(Constants.ULTRASONIC_SENSOR_CHANNEL);

    private double m_scaleFactor = Constants.DRIVE_STARTING_SCALE_FACTOR; //Max
 
    /**create a new drive train subsystem */
    public DriveTrainSubsystem() {
        super();
        m_leftMotor.setInverted(true); //true
        m_rightMotor.setInverted(false); //false
        m_leftMotor.setIdleMode(IdleMode.kBrake);
        m_rightMotor.setIdleMode(IdleMode.kBrake);
        setPipeline(1);
        reset();
        
        m_leftPIDController.setP(Constants.kP);
        m_leftPIDController.setI(Constants.kI);
        m_leftPIDController.setD(Constants.kD);
        m_leftPIDController.setIZone(Constants.kIz);
        m_leftPIDController.setFF(Constants.kFF);
        m_leftPIDController.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);

        m_rightPIDController.setP(Constants.kP);
        m_rightPIDController.setI(Constants.kI);
        m_rightPIDController.setD(Constants.kD);
        m_rightPIDController.setIZone(Constants.kIz);
        m_rightPIDController.setFF(Constants.kFF);
        m_rightPIDController.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);
    }
 
    public void drive(double left, double right) {
     //if(driveType.getAsBoolean())
      // {
      //   m_drive.tankDrive(left*m_scaleFactor, right*m_scaleFactor);
      // }
      //else
      // {
        m_drive.arcadeDrive(left*m_scaleFactor, -right*m_scaleFactor);
      //}
    }

    public void reset(){
      setLeftEncoder(0);
      setRightEncoder(0);
      setMotors(0,0);
      m_scaleFactor = Constants.DRIVE_STARTING_SCALE_FACTOR;
    }

    public void setMotors(double leftSpeed, double rightSpeed){
      m_leftMotor.set(leftSpeed);
      m_rightMotor.set(rightSpeed);
    }

    public void setDriveMotorsRPM(double leftRPM, double rightRPM){
      m_leftPIDController.setReference(leftRPM, CANSparkMax.ControlType.kVelocity);
      m_rightPIDController.setReference(rightRPM, CANSparkMax.ControlType.kVelocity);
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


    public void switchPIP()
    {
      if(m_aiming.getStream().equals(StreamType.kPiPSecondary)){
        m_aiming.setStream(StreamType.kPiPMain);
      }else
      {
        m_aiming.setStream(StreamType.kPiPSecondary);
      } 
    }

    public void setPipeline(int pipe)
    {
      m_aiming.setPipeline(pipe);
    }

    public double getUltraDistInches()
    {
      return m_sonic.getVoltage()*Constants.ULTRASONIC_VOLTAGE_MULTIPLIER;
    }

    public void updateShuffleboard()
    {
      // SmartDashboard.putNumber("LeftInches", getLeftEncoderInches());
      // SmartDashboard.putNumber("RightInches", getRightEncoderInches());
      SmartDashboard.putNumber("LeftMotorRPM", leftEncoder.getVelocity());
      SmartDashboard.putNumber("RightMotorRPM", rightEncoder.getVelocity());
      SmartDashboard.putNumber("UltraSonic Inches", getUltraDistInches());
    }

    public double getLeftEncoderInches()
    {
        return leftEncoder.getPosition()*Constants.DRIVE_WHEEL_CIRCUM/Constants.DRIVE_GEAR_RATIO;
    }

    public double getRightEncoderInches()
    {
        return rightEncoder.getPosition()*Constants.DRIVE_WHEEL_CIRCUM/Constants.DRIVE_GEAR_RATIO;
    }

    public double getAverageEncoderInches(){
      double avgEncoderInches = (Math.abs(getRightEncoderInches())+Math.abs(getLeftEncoderInches()))/2;
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
    public LimeLightAiming getLimeLight()
    {
      return m_aiming;
    }

    /** Call log method every loop. */
    @Override
    public void periodic() {
      m_aiming.updateLimelightValues();
      updateShuffleboard();
      //log();
    }
}

// package frc.robot.subsystems;
 
// //might want to check any methods using this
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import com.revrobotics.RelativeEncoder;
// import com.revrobotics.CANSparkMax.IdleMode;

// import java.util.function.BooleanSupplier;

// import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.Constants;
// import frc.robot.LimeLightAiming;
// import frc.robot.ControlMode.StreamType;
 
// public class DriveTrainSubsystem extends SubsystemBase {
//     private final CANSparkMax m_leftMotor = new CANSparkMax(Constants.DRIVE_LEFT_MOTOR_PORT, MotorType.kBrushless);
//     private final CANSparkMax m_rightMotor = new CANSparkMax(Constants.DRIVE_RIGHT_MOTOR_PORT, MotorType.kBrushless);

//     private final RelativeEncoder leftEncoder = m_leftMotor.getEncoder();
//     private final RelativeEncoder rightEncoder = m_rightMotor.getEncoder();
 
//     private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotor, m_rightMotor);
//     private static BooleanSupplier driveType = () -> false;
//     private static LimeLightAiming m_aiming = new LimeLightAiming();
//     private static AnalogInput m_sonic = new AnalogInput(Constants.ULTRASONIC_SENSOR_CHANNEL);

//     private double m_scaleFactor = Constants.DRIVE_STARTING_SCALE_FACTOR; //Max
 
//     /**create a new drive train subsystem */
//     public DriveTrainSubsystem() {
//       super();
//       m_leftMotor.setInverted(true); //true
//       m_rightMotor.setInverted(false); //false
//       m_leftMotor.setIdleMode(IdleMode.kBrake);
//       m_rightMotor.setIdleMode(IdleMode.kBrake);
//       setPipeline(1);
//       reset();
      
//   }

//   public void drive(double left, double right) {
//    //if(driveType.getAsBoolean())
//     // {
//     //   m_drive.tankDrive(left*m_scaleFactor, right*m_scaleFactor);
//     // }
//     //else
//     // {
//       m_drive.arcadeDrive(left*m_scaleFactor, -right*m_scaleFactor);
//     //}
//   }

//   public void reset(){
//     setLeftEncoder(0);
//     setRightEncoder(0);
//     setMotors(0,0);
//     m_scaleFactor = Constants.DRIVE_STARTING_SCALE_FACTOR;
//   }

//   public void setMotors(double leftSpeed, double rightSpeed){
//     m_leftMotor.set(leftSpeed);
//     m_rightMotor.set(rightSpeed);
//   }

//   public void tankDrive(double leftSpeed, double rightSpeed){
//     m_drive.tankDrive(leftSpeed, rightSpeed);
//   }
  
//   public void setScaleFactor(double scaleFactor){
//     m_scaleFactor = scaleFactor;
//   }

//   public void switchDrive() {
//     if(driveType.getAsBoolean()) 
//         driveType = () -> false;
//     else
//         driveType = () -> true;
//   }

//   public BooleanSupplier getDriveType(){
//     return driveType;
//   }


//   public void switchPIP()
//   {
//     if(m_aiming.getStream().equals(StreamType.kPiPSecondary)){
//       m_aiming.setStream(StreamType.kPiPMain);
//     }else
//     {
//       m_aiming.setStream(StreamType.kPiPSecondary);
//     } 
//   }

//   public void setPipeline(int pipe)
//   {
//     m_aiming.setPipeline(pipe);
//   }

//   public double getUltraDistInches()
//   {
//     return m_sonic.getVoltage()*Constants.ULTRASONIC_VOLTAGE_MULTIPLIER;
//   }

//   public void updateShuffleboard()
//   {
//     // SmartDashboard.putNumber("LeftInches", getLeftEncoderInches());
//     // SmartDashboard.putNumber("RightInches", getRightEncoderInches());
//     SmartDashboard.putNumber("LeftMotorRPM", leftEncoder.getVelocity());
//     SmartDashboard.putNumber("RightMotorRPM", rightEncoder.getVelocity());
//     SmartDashboard.putNumber("UltraSonic Inches", getUltraDistInches());
//   }

//   public double getLeftEncoderInches()
//   {
//       return leftEncoder.getPosition()*Constants.DRIVE_WHEEL_CIRCUM/Constants.DRIVE_GEAR_RATIO;
//   }

//   public double getRightEncoderInches()
//   {
//       return rightEncoder.getPosition()*Constants.DRIVE_WHEEL_CIRCUM/Constants.DRIVE_GEAR_RATIO;
//   }

//   public double getAverageEncoderInches(){
//     double avgEncoderInches = (Math.abs(getRightEncoderInches())+Math.abs(getLeftEncoderInches()))/2;
//     return avgEncoderInches;
//   }

//   private void setLeftEncoder(int position)
//   {
//       leftEncoder.setPosition(position);
//   }

//   private void setRightEncoder(int position)
//   {
//       rightEncoder.setPosition(position);
//   }
//   public LimeLightAiming getLimeLight()
//   {
//     return m_aiming;
//   }

//   /** Call log method every loop. */
//   @Override
//   public void periodic() {
//     m_aiming.updateLimelightValues();
//     updateShuffleboard();
//     //log();
//   }
// }
