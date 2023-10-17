package org.firstinspires.ftc.teamcode.examples;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp(name = "Basic Tank Drive")
public class TankDrive extends LinearOpMode {
    @Override
    public void runOpMode() {
        DcMotorSimple leftFront  = hardwareMap.get(DcMotorSimple.class, "leftFront");
        DcMotorSimple leftBack   = hardwareMap.get(DcMotorSimple.class, "leftBack");
        DcMotorSimple rightFront = hardwareMap.get(DcMotorSimple.class, "rightFront");
        DcMotorSimple rightBack  = hardwareMap.get(DcMotorSimple.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            double ly = gamepad1.left_stick_y;
            double ry = gamepad1.right_stick_y;

            leftFront.setPower(ly);
            leftBack.setPower(ly);
            rightFront.setPower(ry);            
            rightBack.setPower(ry);
        }
    }
}
