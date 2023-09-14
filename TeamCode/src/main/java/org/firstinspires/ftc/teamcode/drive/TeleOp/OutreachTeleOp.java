package org.firstinspires.ftc.teamcode.drive.TeleOp;

import android.drm.DrmStore;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp(name = "OutreachTeleOp")
public class OutreachTeleOp extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;


    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        int MovementToggle;
        float Vertical;
        float Horizontal;
        float Pivot;
        MovementToggle = 0;
        int toggleOpenClaw;
        toggleOpenClaw = 0;
        int toggleLinearSlide;
        toggleLinearSlide = 0;

        telemetry.addData("Wait:", "Waiting for Start...");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            while (opModeIsActive()) {
                Vertical = -gamepad1.left_stick_y;
                Horizontal = gamepad1.left_stick_x;
                Pivot = gamepad1.right_stick_x;

                if (gamepad1.left_bumper && MovementToggle == 0) {
                    MovementToggle = 1;
                } else if (gamepad1.right_bumper && MovementToggle == 1) {
                    MovementToggle = 0;
                }
                if (MovementToggle == 0) {
                    frontRight.setPower((-Pivot + (Vertical - Horizontal)) * 0.6);
                    backRight.setPower((-Pivot + Vertical + Horizontal) * 0.6);
                    frontLeft.setPower((Pivot + Vertical + Horizontal) * 0.6);
                    backLeft.setPower((Pivot + (Vertical - Horizontal)) * 0.6);


                } else if (MovementToggle == 1) {
                    frontRight.setPower(-Pivot + (Vertical - Horizontal));
                    backRight.setPower(-Pivot + Vertical + Horizontal);
                    frontLeft.setPower(Pivot + Vertical + Horizontal);
                    backLeft.setPower(Pivot + (Vertical - Horizontal));

                }
            }
        }
    }
}