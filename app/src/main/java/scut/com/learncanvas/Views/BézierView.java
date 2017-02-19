package scut.com.learncanvas.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yany on 2017/2/15.
 */

public class BézierView extends View {
    private Paint paintF,paintS,paintT;
    public int circleX = 360;
    public int circleY = 560;
    public int radius = 80;
    private int cx,cy,flagx,flagy;
    private boolean isClick = false;
    private Path bpath;
    public BézierView(Context context) {
        super(context);
        init();
    }

    public BézierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BézierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paintF = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintF.setStyle(Paint.Style.FILL);
        paintF.setStrokeWidth(3);
        paintF.setColor(Color.RED);

        paintT = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintT.setStyle(Paint.Style.FILL);
        paintT.setStrokeWidth(3);
        paintT.setColor(Color.GREEN);
        paintT.setTextSize(30);


        paintS = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintS.setStyle(Paint.Style.STROKE);
        paintS.setAntiAlias(true);
        paintS.setStrokeWidth(6);
        paintS.setColor(Color.BLUE);


        bpath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(circleX,circleY,radius,paintF);
        if (isClick){
            if (cx-circleX > 0){
                flagx = 1;
            }else {
                flagx = -1;
            }
            if (cy-circleY > 0){
                flagy = 1;
            }else {
                flagy = -1;
            }
            //计算切点
            double distance = Math.sqrt((cy-circleY)*(cy-circleY)+(cx-circleX)*(cx-circleX));
            double angle = Math.acos(radius/(distance/2));

            double angle1 = Math.acos(Math.abs(cx-circleX)/distance);
            double angle2 = Math.acos(Math.abs(cy-circleY)/distance);
            //根据处于哪个象限采取不同的计算方法
            float p1x = 0,p1y = 0,p2x = 0,p2y = 0,p3x = 0,p3y = 0,p4x = 0,p4y = 0;
            if (flagx * flagy == 1) {
                //二四象限
                p1x = (float) (circleX + radius * Math.cos(angle - angle1) * flagx);
                p1y = (float) (circleY - radius * Math.sin(angle - angle1) * flagx);
                p2x = (float) (circleX - radius * Math.sin(angle - angle2) * flagx);
                p2y = (float) (circleY + radius * Math.cos(angle - angle2) * flagx);
                p3x = (float) (cx + radius * Math.sin(angle - angle2) * flagx);
                p3y = (float) (cy - radius * Math.cos(angle - angle2) * flagx);
                p4x = (float) (cx - radius * Math.cos(angle - angle1) * flagx);
                p4y = (float) (cy + radius * Math.sin(angle - angle1) * flagx);
            }else {
                //一三象限
                p1x = (float) (circleX + radius * Math.sin(angle - angle2) * flagy);
                p1y = (float) (circleY - radius * Math.cos(angle - angle2) * flagx);
                p2x = (float) (circleX - radius * Math.cos(angle - angle1) * flagy);
                p2y = (float) (circleY + radius * Math.sin(angle - angle1) * flagx);
                p3x = (float) (cx + radius * Math.cos(angle - angle1) * flagy);
                p3y = (float) (cy - radius * Math.sin(angle - angle1) * flagx);
                p4x = (float) (cx - radius * Math.sin(angle - angle2) * flagy);
                p4y = (float) (cy + radius * Math.cos(angle - angle2) * flagx);
            }

//            //切点2
////            double angle2 = 90-angle1;
//            if (flagy == 1) {
//                p2x = (float) (circleX - radius * Math.sin(angle - angle2) * flagx);
//                p2y = (float) (circleY + radius * Math.cos(angle - angle2) * flagx);
//            }else {
//                p2x = (float) (circleX - radius * Math.cos(angle - angle1) * flagy);
//                p2y = (float) (circleY + radius * Math.sin(angle - angle1) * flagx);
//            }
//
//
//            //切点3
//            double angle3 = Math.acos((circleY-cy)/distance);
//            if (flagy == 1) {
//                p3x = (float) (cx + radius * Math.sin(angle - angle2) * flagx);
//                p3y = (float) (cy - radius * Math.cos(angle - angle2) * flagx);
//            }else {
//                p3x = (float) (cx + radius * Math.cos(angle - angle1) * flagy);
//                p3y = (float) (cy - radius * Math.sin(angle - angle1) * flagx);
//            }
//
//
//            //切点4
//            double angle4 = Math.acos((circleX-cx)/distance);
//            if (flagy == 1) {
//                p4x = (float) (cx - radius * Math.cos(angle - angle1) * flagy);
//                p4y = (float) (cy + radius * Math.sin(angle - angle1) * flagx);
//            }else {
//                p4x = (float) (cx - radius * Math.sin(angle - angle2) * flagy);
//                p4y = (float) (cy + radius * Math.cos(angle - angle2) * flagx);
//            }


            canvas.drawCircle(cx,cy,radius,paintF);

            //根据求出的4个切点画贝塞尔曲线
            bpath.moveTo(p1x,p1y);
            bpath.quadTo((cx+circleX)/2,(cy+circleY)/2,p3x,p3y);
            bpath.lineTo(p4x,p4y);
            bpath.quadTo((cx+circleX)/2,(cy+circleY)/2,p2x,p2y);
            bpath.close();
            canvas.drawPath(bpath,paintS);
            bpath.reset();
            canvas.drawText("1",p1x+10,p1y+5,paintT);
            canvas.drawText("2",p2x+10,p2y+5,paintT);
            canvas.drawText("3",p3x+10,p3y+5,paintT);
            canvas.drawText("4",p4x+10,p4y+5,paintT);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                isClick = true;
                cx = (int) event.getX();
                cy = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                isClick = true;
                cx = (int) event.getX();
                cy = (int) event.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                isClick = false;
                bpath.reset();
                invalidate();
                break;
        }
        return true;
    }
}
