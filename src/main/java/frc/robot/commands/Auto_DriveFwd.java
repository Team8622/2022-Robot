package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
            RobotContainer.m_driveTrain.setMotors(0.5, -0.5);
            SmartDashboard.putBoolean("Auto On", true);
        }else{
                RobotContainer.m_driveTrain.setMotors(0, 0); 
        }
    }
}