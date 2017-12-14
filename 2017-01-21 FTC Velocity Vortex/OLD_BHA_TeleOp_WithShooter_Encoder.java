/*
Copyright BHA Beariers Robotics Academy 2016-2017 :-)
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;



@Disabled
@TeleOp(name="BHA: TeleOp (ENCODER Shooter)", group="BHA")
public class OLD_BHA_TeleOp_WithShooter_Encoder extends OpMode{

    /* Declare OpMode members. */
    HardwareBHA robot       = new HardwareBHA(); //define a robot object from HardwareBHA class

    // Set power settings for motors
    double BALL_COLLECTOR_POWER = -0.5; // 50% power
    double BALL_LIFT_POWER = 0.6; // 60% power
    double BALL_SHOOTER_POWER = 0.8; // 70% power
    int COUNTS_PER_SHOOTER_REV    = 1680 ;    // am-3103 counts per revolution

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        robot.ballShooter.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.ballShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver");    //
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double left;
        double right;
        int newTarget;

        // The joystick goes negative when pushed forwards, so negate it
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);

        // Get new goal for shooter (should I use negative because of power issue??)
        newTarget = robot.ballShooter.getCurrentPosition() + COUNTS_PER_SHOOTER_REV;


        // BUTTON A: Start Ball Collector with Power BALL_COLLECTOR_POWER
        if (gamepad1.a) {
            robot.ballCollector.setPower(BALL_COLLECTOR_POWER);
        }
        // BUTTON B: Stop Ball Collector
        if (gamepad1.b){
            robot.ballCollector.setPower(0);
        }
        // DOWN D-PAD: BACKWARDS COLLECTOR
        if (gamepad1.dpad_down){
            robot.ballCollector.setPower(-BALL_COLLECTOR_POWER);
        }


        // BUTTON X: Start Ball Lift with Power BALL_LIFT_POWER
        if (gamepad1.x && robot.ballLift.getPower() == 0.0) {
            robot.ballLift.setPower(BALL_LIFT_POWER);
        }
        // BUTTON Y: Stop Ball Lift
        if (gamepad1.y && robot.ballLift.getPower() == BALL_LIFT_POWER){
            robot.ballLift.setPower(0);
        }
        // D-PAD CONTROL OF BEACON SERVO MOTOR [right, left, up (middle)]
        if (gamepad1.dpad_left) {
            //robot.beaconServo.setPosition(1.5);
            //robot.beaconServo.setPosition(Servo.MAX_POSITION);
            robot.beaconServo.setPosition(1.0);
        }
        if (gamepad1.dpad_right) {
            //robot.beaconServo.setPosition(-1.5);
            //robot.beaconServo.setPosition(Servo.MIN_POSITION);
            robot.beaconServo.setPosition(0.0);

        }
        if (gamepad1.dpad_up) {
            //robot.beaconServo.setPosition(robot.MID_SERVO);
            robot.beaconServo.setPosition(0.5);
        }

        //BUMPER CONTROL OF THE SHOOTER
        if (gamepad1.right_bumper){
            robot.ballShooter.setTargetPosition(newTarget);
            robot.ballShooter.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.ballShooter.setPower(-BALL_SHOOTER_POWER);
        }
        if (gamepad1.left_bumper){
            robot.ballShooter.setPower(0);
            robot.ballShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}
