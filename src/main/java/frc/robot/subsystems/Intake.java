package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;

//import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    /**
     * create a new Intake
     */
    private CANSparkMax m_intakeMotor;
    private RelativeEncoder m_encoder;
    public double motorspeed;

    public void init(){
        //initialize motor controller
        try{
            m_intakeMotor = new CANSparkMax(Constants.CANBusID.intakeMotor, MotorType.kBrushless);
            m_intakeMotor.restoreFactoryDefaults();
            m_encoder = m_intakeMotor.getEncoder();
        } catch (RuntimeException ex){
            DriverStation.reportError("error loading intake" + ex.getMessage(), true);
        }

        motorspeed = Constants.SpeedConstants.intakemotorspeed;
        
        SmartDashboard.putNumber("Intake Velocity", motorspeed);
    }

    @Override
    public void periodic() {
        //update motorspeed from Shuffleboard
        motorspeed = SmartDashboard.getNumber("Intake Velocity", 0);

        //display encoder value
        double velocity_out;
        try {
            velocity_out = m_encoder.getVelocity();
        } catch (RuntimeException ex){
            DriverStation.reportError("Intake: Not able to get velocity" + ex.getMessage(), true);
            velocity_out = 0;
        }
        SmartDashboard.putNumber("Intake Motor Velocity", velocity_out);
        super.periodic();
    }

    public boolean isintakeon = false;
    public void intakeon(){
        m_intakeMotor.set(motorspeed);
        SmartDashboard.putBoolean("Intake On", true);
        isintakeon = true;
    }

    public void intakeoff(){
        m_intakeMotor.set(0);
        SmartDashboard.putBoolean("Intake On", false);
        isintakeon = false;
    }
}
