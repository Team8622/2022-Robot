package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
//import frc.robot.Constants;

public class DriveTrain_ArcadeDrive extends CommandBase{
    
    /**
     * Creates a new DriveTrain_ArcadeDrive
     */
    public DriveTrain_ArcadeDrive (DriveTrain subsystem){
        addRequirements(RobotContainer.m_driveTrain);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        RobotContainer.m_driveTrain.ArcadeDrive(
            RobotContainer.stick.getX(),
            -RobotContainer.stick.getY()
        );
    }
}
