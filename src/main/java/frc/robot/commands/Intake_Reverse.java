package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

public class Intake_Reverse extends InstantCommand{
    public Intake_Reverse(){
        addRequirements(RobotContainer.m_intake);
    }

    //called immediately (hence InstantCommand)
    @Override
    public void initialize(){
        if(!RobotContainer.m_intake.isintakeon){
            RobotContainer.m_intake.reverseon();
        }else{
            RobotContainer.m_intake.intakeoff();
        }
    }
}
