package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class BasicAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotorSimple frontLeft = hardwareMap.get(DcMotorSimple.class, "frontLeft");
        DcMotorSimple backLeft = hardwareMap.get(DcMotorSimple.class, "backLeft");
        DcMotorSimple frontRight = hardwareMap.get(DcMotorSimple.class, "frontRight");
        DcMotorSimple backRight = hardwareMap.get(DcMotorSimple.class, "backRight");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        frontLeft.setPower(0.5);
        backLeft.setPower(0.5);
        frontRight.setPower(0.5);
        backRight.setPower(0.5);

        sleep(500);

        requestOpModeStop();
    }
}
