package frc.robot.commands.Drive_Commands;
 
import frc.robot.subsystems.DriveTrainSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
 
public class toggleDriveState extends CommandBase {
    private final DriveTrainSubsystem m_drivetrain;
    /**
     * toggleDriveState Command
     * Swaps the driving type between 
     * arcade and tank drive
     * drivetrain is the subsystem to drive
     */
   
    public toggleDriveState(DriveTrainSubsystem drivetrain) {
        m_drivetrain = drivetrain;

        addRequirements(m_drivetrain);
    }
 
    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        m_drivetrain.switchDrive();
    }
 
  // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return true; // Runs until interrupted
    }
    // Called once after isFinished returns true
    @Override
    public void end(boolean interrupted) {}
}