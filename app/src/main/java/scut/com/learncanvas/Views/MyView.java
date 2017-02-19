package scut.com.learncanvas.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by yany on 2017/1/18.
 */

public class MyView extends View {
    private Paint paint1,paint2,paintSide;
    private int screenWidth = -1, screenHeight = -1;
    private int sbHeight = -1;
    public int sexSide = 70;
    private float sexHeight = (float) (sexSide*Math.sin(Math.PI*60/180));
    private Path sexPath;
    private  float moveX = 0,moveY = 0,downX = 0,downY = 0;
    //六边形的在x方向和y方向的最大个数
    private int xCount,yCount;

    public MyView(Context context) {
        super(context);
        initAttributes(context);
        init();

    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context);
        init();
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(context);
        init();
    }

    private void init() {
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setStyle(Paint.Style.FILL);
        paint1.setStrokeWidth(2);
        paint1.setColor(Color.RED);

        paint2 = new Paint();
        paint2.setStrokeWidth(2);
        paint2.setTextSize(18);//设置字体大小
        paint2.setTypeface(Typeface.DEFAULT);//设置字体类型

        paintSide = new Paint();
        paintSide.setStyle(Paint.Style.STROKE);
        paintSide.setStrokeWidth(4);
        paintSide.setColor(Color.rgb(160,250,250));

        sexPath = new Path();
        xCount = screenWidth/(sexSide*2)+1;
        yCount = (int) (screenHeight/(sexHeight*2)*2)+1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //在这里获取Canvas对象
//        drawAxis(canvas);
//        drawScale(canvas);
        canvas.drawColor(Color.DKGRAY);
        canvas.translate(moveX,moveY);
        //从y方向画
        for (int n = -1; n <= yCount;n++){
            canvas.save();
            if (n % 2 == 1 || n % 2 == -1) {
                canvas.translate((float) (1.5 * sexSide), sexHeight * n);
            }else {
                canvas.translate(0, sexHeight * n);
            }
            //从x方向画
            for (int i = -1;i < xCount;i++) {
                addSexAnglePath(sexSide+sexSide * i *3, (int) sexHeight);
            }
            canvas.drawPath(sexPath,paintSide);
            canvas.restore();
        }

    }

    //画坐标轴
    private void drawAxis(Canvas canvas){
        canvas.drawLine(20,20,screenWidth-20,20, paint1);
        canvas.drawLine(20,20,20,screenHeight-20, paint1);
        Path pathX = new Path();
        pathX.moveTo(screenWidth,20);
        pathX.lineTo(screenWidth-20,30);
        pathX.lineTo(screenWidth-20,10);
        pathX.close();
        canvas.drawPath(pathX, paint1);
        Path pathY = new Path();
        pathY.moveTo(20,screenHeight );
        pathY.lineTo(30,screenHeight - 20 );
        pathY.lineTo(10,screenHeight - 20 );
        pathY.close();
        canvas.drawPath(pathY, paint1);

    }

    //画刻度
    private void drawScale(Canvas canvas){
        int lengthX = screenWidth - 40;
        int lengthY = screenHeight - 40 ;
        canvas.drawText("0",5,20, paint2);
        for (int i = 20; i < lengthX; i += 20){
            if (i % 100 == 0){
                canvas.drawText(Integer.valueOf(i).toString(),i+5,20, paint2);
                if (i % 200 == 0){
                    canvas.drawLine(20+i,20,20+i,50, paint1);
                }else {
                    canvas.drawLine(20+i,20,20+i,40, paint1);
                }
            }else {
                canvas.drawLine(20+i,20,20+i,30, paint1);
            }
        }
        for (int i = 20; i < lengthY; i += 20){
            if (i % 100 == 0){
                canvas.drawText(Integer.valueOf(i).toString(),5,i+8+20, paint2);
                if (i % 200 == 0){
                    canvas.drawLine(20,20+i,50,20+i, paint1);
                }else {
                    canvas.drawLine(20,20+i,40,20+i, paint1);
                }
            }else {
                canvas.drawLine(20,20+i,30,20+i, paint1);
            }
        }
    }

    //画六角形
    private void addSexAnglePath(int x,int y){
        sexPath.moveTo(-sexSide + x,y);
        sexPath.lineTo(-sexSide/2 + x, -sexHeight + y);
        sexPath.lineTo(sexSide/2 + x,-sexHeight+ y);
        sexPath.lineTo(sexSide + x,y);
        sexPath.lineTo(sexSide/2 + x,sexHeight+ y);
        sexPath.lineTo(-sexSide/2 + x, sexHeight+ y);
        sexPath.close();

    }

    //触摸事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:
                moveX = (event.getX() - downX) % sexSide;
                moveY = (event.getY() - downY) % sexHeight;
                invalidate();
                break;
        }
        return true;
    }

    //获取屏幕宽高分辨率方法1
    private void getScreenPixel1(Context context){
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWidth = windowManager.getDefaultDisplay().getWidth();
        screenHeight = windowManager.getDefaultDisplay().getHeight();
        System.out.println("screen1:"+screenWidth+","+ screenHeight);
    }


    //获取屏幕宽高分辨率方法2
    private void getScreenPixel2(Context context){
        DisplayMetrics dm ;
        dm = getResources().getDisplayMetrics();
        float density = dm.density; // 屏幕密度（像素比例：0.75/1.0/1.5/2.0）
        int densityDPI = dm.densityDpi; // 屏幕密度（每寸像素：120/160/240/320）
        float xdpi = dm.xdpi;
        float ydpi = dm.ydpi;
        System.out.println("density:"+density+",densityDPI:"+densityDPI);
        System.out.println("screen2dp:"+xdpi+","+ ydpi);
        screenWidth = dm.widthPixels; // 屏幕宽，单位为像素
        screenHeight = dm.heightPixels; // 屏幕高,单位为像素
        System.out.println("screen2:"+screenWidth+","+ screenHeight);
    }

    //初始化获取屏幕像素
    private void initAttributes(Context context){
        getScreenPixel2(context);
        getStatusBarHeight(context);
    }


    // 获取状态栏高度
    private void getStatusBarHeight(Context context){
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            sbHeight = getResources().getDimensionPixelSize(resourceId);
        }
    }
}
