package org.firstinspires.ftc.teamcode.drive.opmode.Autonomous.Common;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.drive.opmode.Autonomous.Common.VisionBlue;
import org.firstinspires.ftc.teamcode.drive.opmode.Autonomous.Common.VisionRed;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

public class Newton {
    public OpenCvWebcam Webcam1;

    VisionRed Red;
    VisionBlue Blue;

    public void initialize(HardwareMap myHardwareMap, Telemetry telemetry, String position) {

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";

        int cameraMonitorViewId = myHardwareMap.appContext
                .getResources().getIdentifier("cameraMonitorViewId",
                        "id", myHardwareMap.appContext.getPackageName());

        Webcam1 = OpenCvCameraFactory.getInstance().createWebcam(myHardwareMap.get(WebcamName.class, "Webcam1"), cameraMonitorViewId);

        if (position.equals("Blue") || position.equals("blue"))
        {
            Blue = new VisionBlue(telemetry);
            Webcam1.setPipeline(Blue);
        }
        else if (position.equals("Red") || position.equals("red"))
        {
            Red = new VisionRed(telemetry);
            Webcam1.setPipeline(Red);
        }

        Webcam1.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                Webcam1.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });


    }

    public String barcodeDetection(String robotpos) {
        String pos;
        pos = "";
        if (robotpos.equals("Left")) {
            switch (Blue.getLocation()) {
                case LEFT:
                    pos = "L";
                    Webcam1.stopStreaming();
                    break;
                case MIDDLE:
                    pos = "M";
                    Webcam1.stopStreaming();
                    break;
                case RIGHT:
                    pos = "R";
                    Webcam1.stopStreaming();
                    break;
            }
        } else
        {
            switch (Red.getLocation()) {
                case LEFT:
                    pos = "L";
                    Webcam1.stopStreaming();
                    break;
                case MIDDLE:
                    pos = "M";
                    Webcam1.stopStreaming();
                    break;
                case RIGHT:
                    pos = "R";
                    Webcam1.stopStreaming();
                    break;
            }
        }
        return pos;
    }




}