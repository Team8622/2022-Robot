/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */

package frc.robot;

import edu.wpi.first.math.util.Units;

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
    public static final class ranValue{
        public static final double wheelCir = Units.inchesToMeters(37.7);
    }

    public static final class ControllerConstants{
        //Tank Drive Controller Indexes
        public static final int leftYAxis = 1;
        public static final int rightYAxis = 5;
    }

    public static final class SpeedConstants{
        //motor speed measured in RPMS
        public static final int shootermotorspeed = 9500;
        public static final int intakemotorspeed = 7000;
        public static final int winchmotorspeed = 4000;

        public static final double wheelConversion = 2 * Units.inchesToMeters(3) * Math.PI;

        public static final double auto_closeSpeed = 0.62;
    }
    
    public static final class DriveConstants{
        public static final double MaxAlignSpeed = 0.2; 
        public static final double kDriveP = 1;
        public static final double kDriveI = 0;
        public static final double kDriveD = 0;
  
        public static final double kPositionToleranceInches = 3;
        public static final double feetPerSec = 0.5;
        public static final double kPositionRateToleranceInchesPerS = feetPerSec*12;
  
        public static final double kTurnP = 1;
        public static final double kTurnI = 0;
        public static final double kTurnD = 0;
    
        public static final double kMaxTurnRateDegPerS = 100;
        public static final double kMaxTurnAccelerationDegPerSSquared = 300;
        public static final double kMaxTurnPIDTurnSpeed = 0.2;
          
        public static final double kTurnToleranceDeg = 0.5;
        public static final double kTurnRateToleranceDegPerS = 10; // degrees per second
    }
}
