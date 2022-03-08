package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

public class Winch_InstantRelease extends InstantCommand {
    public Winch_InstantRelease(){
      addRequirements(RobotContainer.m_winch);
    }
    @Override
    public void initialize() {
        if(!RobotContainer.m_winch.iswinchon){
          RobotContainer.m_winch.release();
        } else{
          RobotContainer.m_winch.winchoff();
        }
      }
    }
