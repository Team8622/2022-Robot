/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

package frc.robot;

public class Constants {
    public static final class CANBusID{
        public static final int driveLeft1 = 1;
        public static final int driveLeft2 = 2;
        public static final int driveRight1 = 3;
        public static final int driveRight2 = 4;

        //Arm Controllers
        public static final int armLead = 5;
        public static final int armFollow = 6;

        //Shooter Controller Indexes
        public static final int leftShooterMotor = 7;
        public static final int rightShooterMotor = 8;

        public static final int intakeMotor = 9;

    }

    public static final class ControllerConstants{
        //Tank Drive Controller Indexes
        public static final int leftYAxis = 1;
        public static final int rightYAxis = 5;
    }

    public static final class SpeedConstants{
        //motor speed measured in RPMS (I hope)
        public static final int shootermotorspeed = 10800;
        public static final int intakemotorspeed = 6000;
    }
}
