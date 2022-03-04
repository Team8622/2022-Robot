package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.gyro;

//I currently have no idea if this will work
//Marc gets to test it out

public class FieldOriented_ArcadeDrive extends CommandBase{

    //constructor
    public FieldOriented_ArcadeDrive (DriveTrain subsystem){
        addRequirements((RobotContainer.m_driveTrain));
    }

    @Override
    public void execute(){
        double forwrd = RobotContainer.stick.getY() * -1; /* Invert stick Y axis*/
        double strafe = RobotContainer.stick.getX();
        
        double pi = 3.1415926;
        
        /* Adjust Joystick X/Y inputs by navX MXP yaw angle */
        
        double gyro_degrees = gyro.ahrs.getYaw();
        double gyro_radians = gyro_degrees * pi/180; 
        double temp = forwrd * Math.cos(gyro_radians) + 
        strafe * Math.sin(gyro_radians);
        strafe = -forwrd * Math.sin(gyro_radians) + 
        strafe * Math.cos(gyro_radians);
        double fwd = temp;
        

        /* At this point, Joystick X/Y (strafe/forwrd) vectors have been */
        /* rotated by the gyro a
        ngle, and can be sent to drive system */

        RobotContainer.m_driveTrain.ArcadeDrive(
            strafe, 
            fwd
            );
    }
}
