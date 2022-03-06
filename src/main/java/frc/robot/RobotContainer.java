
package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.buttons.Trigger;
//import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.POVButton;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveTrain_ArcadeDrive;
import frc.robot.commands.DriveTrain_TankDrive;
import frc.robot.commands.Intake_Instant;
import frc.robot.commands.Intake_Reverse;
import frc.robot.commands.Shooter_Instant;
import frc.robot.commands.Field_Instant;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Winch;
import frc.robot.commands.Winch_Instant;
import frc.robot.commands.Winch_InstantReverse;
import frc.robot.commands.AutoGrabBall;
//import frc.robot.gyro;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    public static final DriveTrain m_driveTrain = new DriveTrain();
    public static final Shooter m_shooter = new Shooter();
    public static final Intake m_intake = new Intake();
    public static final gyro m_gyro = new gyro();
    public static final Winch m_winch = new Winch();

    public static final Command m_tankDrive = new DriveTrain_TankDrive(m_driveTrain);
    public static final Command m_arcadeDrive = new DriveTrain_ArcadeDrive(m_driveTrain);

    //buttons for the flightstick (MARC)
    public static final Joystick stick = new Joystick(0);
    public static final JoystickButton intakeButton  = new JoystickButton(stick, 1);
    public static final JoystickButton intakeReverse = new JoystickButton(stick, 2);
    public static final JoystickButton fieldOrient = new JoystickButton(stick, 5);
    //public static final JoystickButton shooterButton = new JoystickButton(stick, 12); 

    //buttons for controller (ALEX/SLADE)
    public static final Joystick controller = new Joystick(1);
    public static final JoystickButton shooterButton = new JoystickButton(controller, 6);
    //public static final POVButton shooterUp =  new POVButton(controller, 0);
    //public static final POVButton shooterDown = new POVButton(controller, 180);
    public static final JoystickButton winchButtonUp = new JoystickButton(controller, 1);
    public static final JoystickButton winchButtonDown = new JoystickButton(controller, 2);


    public RobotContainer() {
        //initializes code
        configureButtonBindings();
        //initialize objects here
        m_gyro.init();
        m_shooter.init();
        m_intake.init();
        m_winch.init();
        
    }

    /**
     * 
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        //shooterButton.whenPressed(new InstantCommand(m_shooter :: shooteron, m_shooter));
        //flightstick button bindings
        intakeButton.whenPressed(new Intake_Instant());
        intakeButton.whenReleased(new Intake_Instant());

        intakeReverse.whenPressed(new Intake_Reverse());
        intakeReverse.whenReleased(new Intake_Reverse());

        fieldOrient.whenPressed(new Field_Instant());

        //controller button bindings
        shooterButton.whenPressed(new Shooter_Instant());

        //shooterUp.whenPressed();
        //shooterDown.whenPressed();

        winchButtonUp.whenPressed(new Winch_Instant());
        //winchButtonUp.whenReleased(new Winch_Instant());

        winchButtonDown.whenPressed(new Winch_InstantReverse());
        //winchButtonDown.whenReleased(new Winch_InstantReverse());

    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
        new AutoGrabBall();

    return null;
    }
}
