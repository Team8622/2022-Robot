
package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DriveTrain_ArcadeDrive;
import frc.robot.commands.DriveTrain_TankDrive;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.DriveTrain;
import frc.robot.gyro;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    public static final DriveTrain m_driveTrain = new DriveTrain();
    public static final Shooter m_shooter = new Shooter();
    public static final gyro m_gyro = new gyro();

    public static final Command m_tankDrive = new DriveTrain_TankDrive(m_driveTrain);
    public static final Command m_arcadeDrive = new DriveTrain_ArcadeDrive(m_driveTrain);

    public RobotContainer() {
        configureButtonBindings();
        m_gyro.init();
        //place any object initializaition code here
    }

    /**
     * 
     * Use this method to define your button->command mappings.  Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
     * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
    }
}
