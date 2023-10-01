package org.firstinspires.ftc.teamcode.drive.opmode.Autonomous.Common;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class VisionBlue extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();
    public enum Location {
        LEFT,
        MIDDLE,
        RIGHT,
    }
    private Location location;

    static final Rect MIDDLE_ROI = new Rect(
            new Point(10, 40),
            new Point(30, 60));
    static final Rect RIGHT_ROI = new Rect(
            new Point(140, 15),
            new Point(300, 100));
    static double PERCENT_COLOR_THRESHOLD = 0.1;

    public VisionBlue(Telemetry t) { telemetry = t; }

    @Override
    public Mat processFrame(Mat input) {
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = new Scalar(100,70,100);
        Scalar highHSV = new Scalar(150,255,255);

        Core.inRange(mat, lowHSV, highHSV, mat);

        Mat middle = mat.submat(MIDDLE_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        double middleValue = Core.sumElems(middle).val[0] / MIDDLE_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;

        middle.release();
        right.release();

        //telemetry.addData("Left raw value", (int) Core.sumElems(left).val[0]);
        //telemetry.addData("Right raw value", (int) Core.sumElems(middle).val[0]);
        // telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        // telemetry.addData("Right percentage", Math.round(middleValue * 100) + "%");

        boolean objectMiddle = middleValue > PERCENT_COLOR_THRESHOLD;
        boolean objectRight = rightValue > PERCENT_COLOR_THRESHOLD;

        if (objectRight) {
            location = Location.RIGHT;
            telemetry.addData("Object Location", "right");
        }
        else if (objectMiddle) {
            location = Location.MIDDLE;
            telemetry.addData("Object Location", "middle");
        }
        else {
            location = Location.LEFT;
            telemetry.addData("Object Location", "left");
        }
        telemetry.update();

        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        Scalar colorobject = new Scalar(255, 0, 0);
        Scalar colorblank = new Scalar(0, 255, 0);

        Imgproc.rectangle(mat, MIDDLE_ROI, location == Location.MIDDLE? colorblank:colorobject);
        Imgproc.rectangle(mat, RIGHT_ROI, location == Location.RIGHT? colorblank:colorobject);

        return mat;
    }

    public Location getLocation() {
        return location;
    }
}