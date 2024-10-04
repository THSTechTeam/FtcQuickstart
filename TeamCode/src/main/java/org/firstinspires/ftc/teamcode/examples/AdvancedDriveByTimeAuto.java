package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
// AdvancedDriveByTime auto drives around the center of the field and does a
// dance at the end.
public class AdvancedDriveByTimeAuto extends LinearOpMode {
    // We need to define the motors as instance variables on the class so
    // that they can be used in the methods such as turnLeft() and turnRight().
    DcMotorSimple leftFront;
    DcMotorSimple leftBack;
    DcMotorSimple rightFront;
    DcMotorSimple rightBack;

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotorSimple.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorSimple.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorSimple.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorSimple.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        long straightLengthFt = 8;
        // Wheel circumference is about 1 ft, and it takes about 0.6-0.8s to
        // get a full rotation at 0.5 speed.
        long durationPerFt = 685;
        long moveDuration = straightLengthFt * durationPerFt;

        waitForStart();

        // we start against the wall, so increase how long we move
        moveForDuration(0.5, moveDuration + 1400);

        // Do three right turns so we get back to where we started
        for (int i = 0; i < 3; i++) {
            turnRight(0.5, 1250);
            moveForDuration(0.5, moveDuration);
        }

        // Do a dance
        for (int i = 0; i < 4; i++) {
            turnLeft(1.0, 1000);
            turnRight(1.0, 1000);
        }

        requestOpModeStop();
    }

    private void turnLeft(double power, long durationMs) {
        // Ensure power is always positive
        power = Math.abs(power);
        leftFront.setPower(-power);
        leftBack.setPower(-power);
        rightFront.setPower(power);
        rightBack.setPower(power);
        sleep(durationMs);
        resetPower();
    }

    private void turnRight(double power, long durationMs) {
        // Ensure power is always positive
        power = Math.abs(power);
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(-power);
        rightBack.setPower(-power);
        sleep(durationMs);
        resetPower();
    }

    private void moveForDuration(double power, long durationMs) {
        // Don't Math.abs power so that the caller can set it negative to go reverse.
        leftFront.setPower(power);
        leftBack.setPower(power);
        rightFront.setPower(power);
        rightBack.setPower(power);
        sleep(durationMs);
        resetPower();
    }

    private void resetPower() {
        leftFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        rightBack.setPower(0);
    }
}