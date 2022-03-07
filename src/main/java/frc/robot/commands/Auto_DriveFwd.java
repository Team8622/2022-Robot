package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class Auto_DriveFwd extends CommandBase{
    //constructor
    public Auto_DriveFwd(DriveTrain subsystem){
        addRequirements(RobotContainer.m_driveTrain);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        double time = Timer.getFPGATimestamp();

        if(time - Robot.autoTime < 2){
            RobotContainer.m_driveTrain.ArcadeDrive(0, -0.75);
        }else{
            RobotContainer.m_driveTrain.ArcadeDrive(0, 0);
        }
    }
}