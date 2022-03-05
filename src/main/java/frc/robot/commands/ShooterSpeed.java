package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotContainer;

public class ShooterSpeed extends InstantCommand{
    boolean speedUp;
    public ShooterSpeed(boolean up){
        speedUp = up;
    }

    @Override
    public void initialize(){
        double newSpeed;
        if(speedUp){
            newSpeed = RobotContainer.m_shooter.motorspeed += 250;
        }else{
            newSpeed = RobotContainer.m_shooter.motorspeed += 250;
        }
        SmartDashboard.putNumber("Shooter Velocity", newSpeed);
    }
    
}
