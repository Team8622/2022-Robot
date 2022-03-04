package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

public class Field_Instant extends InstantCommand {
    //constructor
    public Field_Instant(){
        addRequirements(RobotContainer.m_driveTrain);
    }

    //Called when command is initialized
    @Override
    public void initialize(){
        //if field isn't on, turn it on. If it is on, go back to robot oriented
        if(!RobotContainer.m_driveTrain.fieldoriented){
           RobotContainer.m_driveTrain.setDefaultCommand(new FieldOriented_ArcadeDrive(RobotContainer.m_driveTrain)); 
        }else{
            RobotContainer.m_driveTrain.setDefaultCommand(new DriveTrain_ArcadeDrive(RobotContainer.m_driveTrain));
        }
    }
}