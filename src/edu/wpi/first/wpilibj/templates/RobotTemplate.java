/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;

public class RobotTemplate extends SimpleRobot {
    int location = 0;
    
    DigitalInput limitSwitch1 = new DigitalInput(1);
    DigitalInput limitSwitch2 = new DigitalInput(2);
    DigitalInput limitSwitch3 = new DigitalInput(3);
    
    RobotDrive chassis = new RobotDrive(1, 2, 3, 4);
    Joystick gamepad = new Joystick(1);
    
    Jaguar wheelJag = new Jaguar(5); 
    Jaguar armJag = new Jaguar(6);
    
   
    public void autonomous() {
        chassis.setSafetyEnabled(false);
        chassis.drive(-.17, 0.0);
        Timer.delay(4.0);
        chassis.drive(0.0, 0.0);
    }

    public void operatorControl() {
        if(gamepad.getRawButton(1)) //Button A
            readyBall();
        if(gamepad.getRawButton(2)) //Button B
            returnBall();
        if(gamepad.getRawButton(3)) //Button X
            pickupBall();
        if(gamepad.getRawButton(4)) //Button Y
            catchBall();
        if(gamepad.getRawButton(6)) //Button RB
            stopArm();            
            
        //chassis.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        while(isOperatorControl() && isEnabled()){
            
            
            //Right Handed
            double axisL = gamepad.getRawAxis(2)*.4;
            double axisR = gamepad.getRawAxis(4)*.3;
            
            //Left Handed
            //double axisL = gamepad.getRawAxis(1)*.3;
            //double axisR = gamepad.getRawAxis(5)*.4;
            
            
            chassis.arcadeDrive(axisR, axisL, true);
            Timer.delay(0.005);
        }
    }
    //Neg values for away from robot, pos for torward
    private void readyBall(){        
        catchBall();
        wheelJag.set(0);
    }   
    
    private void returnBall(){
        while(!limitSwitch1.get())
            armJag.set(.5);
        wheelJag.set(-.5);
        Timer.delay(.5);
        while(!limitSwitch2.get())
            armJag.set(-.5);
        armJag.set(0);
        readyBall();
    }
    
    private void pickupBall(){
        wheelJag.set(.5);
        while(!limitSwitch3.get())
            armJag.set(.5);
        Timer.delay(2);
        while(!limitSwitch2.get())
            armJag.set(-.5);
        armJag.set(0);
        location = 2;
    }
    
    private void catchBall(){
        wheelJag.set(-.5);
        while(!limitSwitch2.get()){
            if(location == 3)            
                armJag.set(.5);
            if(location == 1)
                armJag.set(-.5);
        }
        armJag.set(0);            
        location = 2;
    }
    
    private void stopArm(){
        while(!limitSwitch1.get())
            armJag.set(-.5);
        armJag.set(0);
        wheelJag.set(0);
        location = 1;
    }
    public void test() {
    
    }
}