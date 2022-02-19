package frc.robot.misc;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;
//import frc.robot.RobotContainer;
//import commands to be connected to joystick buttons
import frc.robot.commands.Shooter_Instant;
import frc.robot.subsystems.Shooter;

public class Controls {
    public static Joystick stick = new Joystick(0);
    //public static Joystick rightJoy = new Joystick(1);
    //Define buttons here
    //Button exampleButton = new JoystickButton(exampleJoystick, index).whenPressed(new command());
    //Button shooterButton = new JoystickButton(stick, 1).whenPressed(new InstantCommand(RobotContainer.m_shooter :: shooteron, RobotContainer.m_shooter));
    Button shooter_button = new JoystickButton(stick,1).whenReleased(new Shooter_Instant());
    //if we want to configure this for different drivers
}
