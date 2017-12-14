package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This is the BHA Robotics Academy Hardware Profile. THIS IS NOT AN OpMode!
 *
 * This Hardware Profile is derived from HardwarePushbot in FtcRobotController/...
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 */
public class JIU_HardwareBHA_Test
{
    /* Public OpMode members. */
    public DcMotor  leftMotor   = null;
    public DcMotor  rightMotor  = null;

    public ColorSensor colorSensor = null;

    public Servo glimpseLServo = null; // Left Servo for Glimpse
    public Servo glimpseRServo = null; // Right Servo for Glimpse
    public double glimpseLServoInitial = 200;
    public double glimpseRServoInitial = 100;

    // public GyroSensor gyro = null;

    // Static variables for Servos from Hardware Pushbot
    // (may need to change for each individual servo)

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public JIU_HardwareBHA_Test(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors: Phone Configuration Variables
        leftMotor = hwMap.dcMotor.get("left_motor");
        rightMotor = hwMap.dcMotor.get("right_motor");

        colorSensor = hwMap.colorSensor.get("color_sensor");

        glimpseLServo = hwMap.servo.get("glimpse_l_servo");
        glimpseRServo = hwMap.servo.get("glimpse_r_servo");

        // gyro = (ModernRoboticsI2cGyro)hwMap.gyroSensor.get("gyro");

        // MAY HAVE TO EDIT OTHER MOTORS IF THEY BEHAVE WEIRD...
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);

        // Intialize ALL installed servos
        glimpseLServo.scaleRange(1,250);
        glimpseLServo.setPosition(glimpseLServoInitial); //Set position to top/middle

        glimpseRServo.scaleRange(1,250);
        glimpseRServo.setPosition(glimpseRServoInitial); //Set position to low-state inside the lift

        // Set hardware values for both ColorSensors
        colorSensor.setI2cAddress(I2cAddr.create8bit(0x3a));

        // LED COMMENTED OUT DUE TO FIRMWARE ISSUES: Use Core Device Discovery to Change LEDs
        // Turn ON LED lights as default (may change later)
        // colorSensor1.enableLed(true);

        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // RUN WITH ENCODER
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // RUN WITH ENCODER

    }
    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     */
    public void waitForTick(long periodMs) {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0) {
            try {
                Thread.sleep(remaining);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}