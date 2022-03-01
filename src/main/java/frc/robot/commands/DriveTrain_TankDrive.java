package frc.robot.commands;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.Constants;

public class DriveTrain_TankDrive extends CommandBase{
    //this looks useful but I literally do not think its used anywhere else
    double driveSpeed = 10; //maximum percentage of motor to use
    
    /**
     * Creates a new DriveTrain_TankDrive
     */
    public DriveTrain_TankDrive(DriveTrain subsystem){
        addRequirements(RobotContainer.m_driveTrain);
    }

    //called when the command is initially scheduled
    @Override
    public void initialize() {
        //driveSpeed = SmartDashboard.getNumber("Speed Percentage", 100);
    }

    //called when the scheduler runs while the command is scheduled
    @Override
    public void execute() {
        RobotContainer.m_driveTrain.TankDrive(
            -RobotContainer.stick.getRawAxis(Constants.ControllerConstants.leftYAxis),
            -RobotContainer.stick.getRawAxis(Constants.ControllerConstants.rightYAxis)
        );
    }

    //called when the command ends or is interrupted
    @Override
    public void end(boolean interrupted){
    } 

    //returns true when command should end
    @Override
    public boolean isFinished() {
        return false;
    }

}
