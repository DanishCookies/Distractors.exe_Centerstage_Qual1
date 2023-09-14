package org.firstinspires.ftc.teamcode.drive.opmode.Autonomous.Common;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.Autonomous.Common.Newton;


//@Autonomous
@Autonomous

public class VisionTesting extends LinearOpMode {
    Newton newton;


    public void runOpMode() {


        Pose2d startPose = new Pose2d(-30, -65, Math.toRadians(90));

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);


        newton = new Newton();
        newton.initialize(hardwareMap, telemetry, "Left");

        telemetry.addData("note", "there has been an update");
        telemetry.update();
        waitForStart();

        String position;
        position = newton.barcodeDetection("Left");


        switch (position) {
            case "L":
                Location1();
                telemetry.addData("Block Location", "Low");
                telemetry.update();
                break;
            case "M":
                LocationSecond();
                telemetry.addData("Block Location", "Middle");
                telemetry.update();
                break;
            case "R":
                Location3();
                telemetry.addData("Block Location", "High");
                telemetry.update();
                break;
        }





    }
    public void Location1() {
        telemetry.addData("Left", "Left");
    }
    public void LocationSecond() {
        telemetry.addData("Right", "Right");
    }
    public void Location3(){
        telemetry.addData("Middle", "Middle");
    }
}