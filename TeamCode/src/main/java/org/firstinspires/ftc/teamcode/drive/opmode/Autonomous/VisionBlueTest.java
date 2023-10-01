package org.firstinspires.ftc.teamcode.drive.opmode.Autonomous;


import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.opmode.Autonomous.Common.Newton;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;




@Autonomous
public class VisionBlueTest extends LinearOpMode {
    Newton newton;


    public void runOpMode() {
//Initialization

//        Pose2d startPose = new Pose2d(-30, -65, Math.toRadians(90));
//
//        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//        drive.setPoseEstimate(startPose);


        newton = new Newton();
        newton.initialize(hardwareMap, telemetry, "Blue");

        telemetry.addData("note", "there has been an update");
        telemetry.update();
        waitForStart();

        String position;
        position = newton.barcodeDetection("Blue");

// Vision

        switch (position) {
//if case = L then third location code will be run
            case "L":
                Location3();
                telemetry.addData("Block Location", "Low");
                telemetry.update();
                break;
            case "M":
// if case = M then first location code will be run
                Location1();
                telemetry.addData("Block Location", "Middle");
                telemetry.update();
                break;
            case "R":
// if case = R then second location code will be run
                LocationSecond();
                telemetry.addData("Block Location", "High");
                telemetry.update();
                break;
        }





    }
    //Location 1 function
    public void Location1() {

    }
    public void LocationSecond(){

    }
    public void Location3(){

    }

}