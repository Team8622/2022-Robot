package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
//import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.ControlType;
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

    public void init(){}
}
