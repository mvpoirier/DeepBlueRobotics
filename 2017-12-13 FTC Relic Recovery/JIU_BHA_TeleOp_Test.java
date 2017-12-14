/*
Copyright BHA Beariers Robotics Academy 2016-2017 :-)
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="BHA: TeleOp (with Shooter)", group="BHA")
public class JIU_BHA_TeleOp_Test extends OpMode{

    /* Declare OpMode members. */
    JIU_HardwareBHA_Test robot       = new JIU_HardwareBHA_Test(); //define a robot object from JIU_HardwareBHA_Test class
    double glimpseLServoPosition = 200;
    double glimpseRServoPosition = 100;
    /* Declare Motor Power constants */


    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "JIU_HardwareBHA_Test Initialized.");    //
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

        /* GAMEPAD #1 */

        // The joystick goes negative when pushed forwards, so negate it
        left = -gamepad1.left_stick_y;
        right = -gamepad1.right_stick_y;
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right);

        // Left Bumper: Increase glimpseLServo Position by 10
        if (gamepad1.left_bumper) {
            glimpseLServoPosition = glimpseLServoPosition + 10;
            robot.glimpseLServo.setPosition(glimpseLServoPosition);
        }
        // Right Bumper: Increase glimpseRServo Position by 10
        if (gamepad1.right_bumper) {
            glimpseRServoPosition = glimpseRServoPosition + 10;
            robot.glimpseRServo.setPosition(glimpseRServoPosition);
        }

/*
        // BUTTON A: Start Ball Collector with Power BALL_COLLECTOR_POWER
        if (gamepad1.a) {
            robot.ballCollector.setPower(BALL_COLLECTOR_POWER);
        }
        // BUTTON B: Stop Ball Collector
        if (gamepad1.b){
            robot.ballCollector.setPower(0);
        }
        // DOWN D-PAD: BACKWARDS COLLECTOR
        //if (gamepad1.dpad_down){
        //    robot.ballCollector.setPower(-BALL_COLLECTOR_POWER);
        //}


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
            robot.beaconServo.setPosition(1.0);
        }
        if (gamepad1.dpad_right) {
            robot.beaconServo.setPosition(0.0);

        }
        //if (gamepad1.dpad_up) {
        //    robot.beaconServo.setPosition(0.5);
        //}

*/
        /* GAMEPAD #2 */
/*
        //BUMPER CONTROL OF THE SHOOTER
        if (gamepad2.right_bumper){
            robot.ballShooter.setPower(-BALL_SHOOTER_POWER);
        }
        if (gamepad2.left_bumper){
            robot.ballShooter.setPower(0);
        }


        //D-PAD CONTROL OF THE SHOOTER SERVO
        if (gamepad2.dpad_left) {
            robot.ballServo.setPosition(1.0);
        }
        if (gamepad2.dpad_right) {
            robot.ballServo.setPosition(0.0);

        }
        //if (gamepad2.dpad_up) {
        //    robot.ballServo.setPosition(0.5);
        //}
*/
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        robot.rightMotor.setPower(0);
        robot.leftMotor.setPower(0);
        robot.glimpseLServo.setPosition(200);
        robot.glimpseRServo.setPosition(100);
            /*
        robot.ballCollector.setPower(0);
        robot.ballLift.setPower(0);
        robot.ballShooter.setPower(0);
        robot.beaconServo.setPosition(0.5);
        robot.ballServo.setPosition(0.5);
        */
    }

}