package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;


public class DriveTrain_Twist extends InstantCommand {
    private double speed; 
    public DriveTrain_Twist(double speed){
        addRequirements(RobotContainer.m_driveTrain);
        this.speed = speed;
    }

    @Override 
    public void initialize() {
        RobotContainer.m_driveTrain.twist(speed);
        RobotContainer.m_driveTrain.twist(-speed);
    }


}