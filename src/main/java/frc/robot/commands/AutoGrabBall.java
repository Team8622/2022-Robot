package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Timer;

//import frc.robot.subsystems.DriveTrain;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
//import frc.robot.Robot;
import frc.robot.RobotContainer;

//import frc.robot.commands.DriveTrain_ArcadeDrive;

public class AutoGrabBall extends InstantCommand{
    public AutoGrabBall(){
        addRequirements(RobotContainer.m_driveTrain);
      }

      @Override
      public void initialize() {
          RobotContainer.m_driveTrain.ArcadeDrive(.75,0);
          Timer.delay(2.0);
          RobotContainer.m_driveTrain.ArcadeDrive(0, 0);

      }
}
