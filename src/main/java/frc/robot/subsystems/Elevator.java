package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.*;
import frc.robot.utils.Constants.ModuleIDs;

public class Elevator extends SubsystemBase {

    SparkMax motor_1;
    SparkMax motor_2;
    public static double currentHeight;
    
    public Elevator(){
        motor_1 = new SparkMax(ModuleIDs.e1, MotorType.kBrushless);
        motor_2 = new SparkMax(ModuleIDs.e2, MotorType.kBrushless);

        SparkMaxConfig config = new SparkMaxConfig();
        config
            .inverted(true)
            .idleMode(IdleMode.kBrake);
        config.encoder
            .positionConversionFactor(1000)
            .velocityConversionFactor(1000);
        config.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .pid(1.0, 0.0, 0.0);
            
        motor_1.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motor_2.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void move(double speed) {
        // Set motor outputs
        motor_1.set(-speed);
        motor_2.set(speed);
    }
    public  double getPostion(){
        return motor_1.getAbsoluteEncoder().getPosition();
    }

    public void stop(){
        motor_1.set(0);
        motor_2.set(0);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Elevator Height", currentHeight);
        SmartDashboard.putNumber("Elevator Motor 1 Power", motor_1.get());
        SmartDashboard.putNumber("Elevator Motor 2 Power", motor_2.get());
        SmartDashboard.putNumber("Elevator Motor 1 Position", motor_1.getEncoder().getPosition());
        SmartDashboard.putNumber("Elevator Motor 2 Position", motor_2.getEncoder().getPosition());
    }
}
//Created by Aditya-2204 {
