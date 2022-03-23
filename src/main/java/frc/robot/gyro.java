package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.time.chrono.ThaiBuddhistChronology;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.DriverStation;

//call the gyro and enable the measuring
public class gyro extends SubsystemBase {
    public static AHRS ahrs;
    public void init() {
        try {
            ahrs = new AHRS(SPI.Port.kMXP);
            ahrs.enableLogging(true);
            ahrs.resetDisplacement();
        } 
        catch (RuntimeException ex ) {
            DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
        }
    }
    public double getHeading() {
        try {
         return Math.IEEEremainder(ahrs.getAngle(), 360);
        } catch (RuntimeException ex) 
        {
            DriverStation.reportError("Error Getting heading:  " + ex.getMessage(), true);
            return 0;
        }
    }
    
    public void zero(){
        // Offsets gyro so current heading is zero
        // No affect if currently calibrating 
        ahrs.zeroYaw();
    }

    public void reset(){
        // Causes Navx to recalibrate which will reset heading to zero
        ahrs.reset();
    }
    public void periodic() {
      SmartDashboard.putNumber("Gyro", getHeading());
      SmartDashboard.putNumber("Alt", ahrs.getAltitude());
      SmartDashboard.putNumber("Pressure", ahrs.getPressure());
      SmartDashboard.putNumber("Pitch", ahrs.getPitch());
      SmartDashboard.putNumber("Roll", ahrs.getRoll());
      SmartDashboard.putNumber("DisX", ahrs.getDisplacementX());
      SmartDashboard.putNumber("DisY", ahrs.getDisplacementY());
      SmartDashboard.putNumber("VelX", ahrs.getVelocityX());
      SmartDashboard.putNumber("VelY", ahrs.getVelocityY());
      SmartDashboard.putNumber("VelX", ahrs.getTempC());

      
    }
}

