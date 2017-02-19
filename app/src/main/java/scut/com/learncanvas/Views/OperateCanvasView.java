package scut.com.learncanvas.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yany on 2017/1/23.
 */

public class OperateCanvasView extends View {
    private Paint paintF,paintS,paintG,paintTest;
    //模式选择的枚举类型
    public enum MODE{TRANSLATE,SCALE,ROTATE,SKEW,CLIP,SR}
    public MODE mode = MODE.SR;
    public OperateCanvasView(Context context) {
        super(context);
        init();
    }

    public OperateCanvasView(Context context, MODE mode) {
        super(context);
        this.mode = mode;
        init();
    }

    public OperateCanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public OperateCanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paintF = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintF.setStyle(Paint.Style.FILL);
        paintF.setStrokeWidth(3);
        paintF.setAntiAlias(true);
        paintF.setColor(Color.RED);


        paintS = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintS.setStyle(Paint.Style.STROKE);
        paintS.setAntiAlias(true);
        paintS.setStrokeWidth(3);
        paintS.setColor(Color.RED);

        paintG = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintG.setStyle(Paint.Style.STROKE);
        paintG.setStrokeWidth(3);
        paintG.setAntiAlias(true);
        paintG.setColor(Color.GREEN);

        paintTest = new Paint(paintF);
        paintTest.setStrokeWidth(3);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mode){
            case TRANSLATE:
                canvas.translate(150,500);
                canvas.drawCircle(0,0,100, paintS);
                canvas.translate(105,105);
                canvas.drawCircle(0,0,100, paintS);
                canvas.translate(105,-105);
                canvas.drawCircle(0,0,100, paintS);
                canvas.translate(105,105);
                canvas.drawCircle(0,0,100, paintS);
                canvas.translate(105,-105);
                canvas.drawCircle(0,0,100, paintS);
                break;
            case SCALE:
                canvas.drawCircle(400,780,20, paintF);
                for (int i = 0; i < 11;i++){
                    canvas.drawArc(new RectF(350,750,450,850),-120,60,false,paintS);
                    canvas.scale(1.3f,1.3f,400,800);
                }
                break;
            case ROTATE:
                canvas.drawCircle(380,500,300,paintS);
                canvas.drawCircle(380,500,250,paintS);
                int rTimes = 6;
                for (int i = 0; i < rTimes;i++){
                    canvas.rotate(360/rTimes,380,500);
                    canvas.drawLine(380,500,380,200,paintF);
                }
                break;
            case SKEW:
                canvas.drawRect(20,20,200,100,paintS);
                canvas.skew(0,1);
//                canvas.drawCircle(150,300,150,paintG);
                canvas.drawRect(20,20,200,100,paintG);
                break;
            case CLIP:
//                canvas.drawColor(Color.BLACK);
                canvas.translate(200,200);
                //裁剪
                canvas.clipRect(0,0,300,300);
                //一次裁剪后的区域（红色）
                canvas.drawColor(Color.RED);
                //画图
                Path path = new Path();
                path.addCircle(300,150,150, Path.Direction.CW);
                canvas.clipPath(path, Region.Op.REVERSE_DIFFERENCE);
                //二次裁剪后获得的区域（绿色）
                canvas.drawColor(Color.GREEN);

//                canvas.drawCircle(300,150,150,paintF);
//                canvas.clipRect(150,150,400,400, Region.Op.DIFFERENCE);
//                canvas.drawCircle(300,300,100,paintF);
                break;
            case SR:




                //保存
                canvas.save();
                canvas.translate(300,300);
                canvas.drawCircle(0,0,100,paintF);
                //恢复
                canvas.restore();
                canvas.drawRect(0,0,200,200,paintF);
//                paintTest.setStrokeWidth(50);
//                paintTest.setColor(Color.RED);
//                paintTest.setStrokeCap(Paint.Cap.BUTT);
//                canvas.drawLine(100,100,400,100,paintTest);
//
//                paintTest.setStrokeCap(Paint.Cap.ROUND);
//                canvas.drawLine(100,250,400,250,paintTest);
//
//                paintTest.setStrokeCap(Paint.Cap.SQUARE);
//                canvas.drawLine(100,400,400,400,paintTest);
//
//                paintTest.setColor(Color.BLACK);
////                paintTest.setStrokeWidth(1);
//                paintTest.setStrokeCap(Paint.Cap.BUTT);
//                canvas.drawLine(100,100,400,100,paintTest);
//                canvas.drawLine(100,250,400,250,paintTest);
//                canvas.drawLine(100,400,400,400,paintTest);

//                paintTest.setStyle(Paint.Style.STROKE);
//                paintTest.setStrokeJoin(Paint.Join.MITER);
//                canvas.drawRect(100,100,400,250,paintTest);
//
//                paintTest.setStrokeJoin(Paint.Join.ROUND);
//                canvas.drawRect(100,350,400,500,paintTest);
//
//                paintTest.setStrokeJoin(Paint.Join.BEVEL);
//                canvas.drawRect(100,600,400,750,paintTest);
//
//                paintTest.setTextAlign(Paint.Align.CENTER);

                break;
        }
    }
}
