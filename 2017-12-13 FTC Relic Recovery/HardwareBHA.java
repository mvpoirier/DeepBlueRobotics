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
public class HardwareBHA
{
    /* Public OpMode members. */
    public DcMotor  leftMotor   = null;
    public DcMotor  rightMotor  = null;

    public DcMotor  ballCollector = null;
    public DcMotor  ballLift = null;

    public DcMotor  ballShooter = null;

    public ColorSensor colorSensor1 = null; // Beacon color detection (0x3a, Port 0)
    public ColorSensor colorSensor2 = null; // White line detection (0x3c, Port 2)

    public Servo beaconServo = null; // Servo to touch the correct beacon
    public Servo ballServo = null; // Servo to control balls reaching the shooter

    public GyroSensor gyro = null;

    // Static variables for Servos from Hardware Pushbot
    // (may need to change for each individual servo)
    public static final double MID_SERVO       =  0.5 ;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareBHA(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors: Phone Configuration Variables
        leftMotor = hwMap.dcMotor.get("left_motor");
        rightMotor = hwMap.dcMotor.get("right_motor");

        ballCollector = hwMap.dcMotor.get("ball_collector");
        ballLift = hwMap.dcMotor.get("ball_lift");

        ballShooter = hwMap.dcMotor.get("ball_shooter");

        colorSensor1 = hwMap.colorSensor.get("color_sensor1");
        colorSensor2 = hwMap.colorSensor.get("color_sensor2");

        beaconServo = hwMap.servo.get("beacon_servo");
        ballServo = hwMap.servo.get("ball_servo");

        gyro = (ModernRoboticsI2cGyro)hwMap.gyroSensor.get("gyro");

        // MAY HAVE TO EDIT OTHER MOTORS IF THEY BEHAVE WEIRD...
        leftMotor.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        ballCollector.setDirection(DcMotor.Direction.REVERSE);
        ballLift.setDirection(DcMotor.Direction.REVERSE);

        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        ballCollector.setPower(0);
        ballLift.setPower(0);
        ballShooter.setPower(0);

        // Intialize ALL installed servos
        beaconServo.scaleRange(0.0, 1.0);
        beaconServo.setPosition(0.5); //Set position to top/middle

        ballServo.scaleRange(0.0, 1.0);
        ballServo.setPosition(1.0); //Set position to low-state inside the lift

        // Set hardware values for both ColorSensors
        colorSensor1.setI2cAddress(I2cAddr.create8bit(0x3a));
        colorSensor1.setI2cAddress(I2cAddr.create8bit(0x3c));

        // LED COMMENTED OUT DUE TO FIRMWARE ISSUES: Use Core Device Discovery to Change LEDs
        // Turn ON LED lights as default (may change later)
        // colorSensor1.enableLed(true);
        // colorSensor2.enableLed(true);

        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // RUN WITH ENCODER
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // RUN WITH ENCODER

        ballShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER); // MAY NEED TO RUN WITH ENCODER

        ballCollector.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ballLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
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