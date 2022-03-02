package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

public class Intake_Instant extends InstantCommand{
    public Intake_Instant(){
        addRequirements(RobotContainer.m_intake);
    }

    //called immediately
    @Override
    public void initialize(){
        if(!RobotContainer.m_intake.isintakeon){
            RobotContainer.m_intake.intakeon();
        }else{
            RobotContainer.m_intake.intakeoff();
        }
    }
}
