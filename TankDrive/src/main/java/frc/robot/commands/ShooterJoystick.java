package frc.robot.commands;
 
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 
public class ShooterJoystick extends CommandBase {
    private final ShooterSubsystem m_shooter;
    private final DoubleSupplier m_left;
    private final DoubleSupplier m_right;
 
    /**
     * TankDrive Command
     * takes in left and right control inputs for each motor
     * drivetrain is the subsystem to drive
     */
   
    public ShooterJoystick(DoubleSupplier left, DoubleSupplier right, ShooterSubsystem drivetrain) {
        //setting parameters to local vars
        m_shooter = drivetrain;
        m_left = left;
        m_right = right;
 
        addRequirements(m_shooter);
    }
 
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_shooter.drive(m_left.getAsDouble());
        //SmartDashboard.putNumber("LeftMotorEncoderDistanceInches", m_shooter.getLeft());
        //System.out.println("encoderworkingyay: " + m_drivetrain.getLeft());
    }
 
  // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false; // Runs until interrupted
    }
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {
        m_shooter.drive(0);
    }
}