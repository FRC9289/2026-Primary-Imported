package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Elevator;

public class ElevatorCommands extends Command {
    //Define Motor Attributes
    Elevator elevatorMotors;
    double speed;
    String motor;

   public ElevatorCommands(Elevator _elevatorMotor, double power)
   {
        //Construct Attributes
        elevatorMotors = _elevatorMotor;
        this.speed = power;
        addRequirements(elevatorMotors);
   }

    @Override
    public void execute() {
        //Move Elevator
        elevatorMotors.move(this.speed);
    }

    @Override
    public void end(boolean interrupted) {
        //Stop Elevator
        elevatorMotors.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

//Created by Aditya-2204