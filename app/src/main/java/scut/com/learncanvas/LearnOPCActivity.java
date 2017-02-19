package scut.com.learncanvas;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import scut.com.learncanvas.Views.OperateCanvasView;

public class LearnOPCActivity extends Activity implements View.OnClickListener {
    private Button btn_TRANSLATE,btn_SCALE,btn_ROTATE,btn_SKEW,btn_CLIP;
    private OperateCanvasView operateCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_opc);
        btn_TRANSLATE = (Button) findViewById(R.id.btn_TRANSLATE);
        btn_SCALE = (Button) findViewById(R.id.btn_SCALE);
        btn_ROTATE = (Button) findViewById(R.id.btn_ROTATE);
        btn_SKEW = (Button) findViewById(R.id.btn_SKEW);
        btn_CLIP = (Button) findViewById(R.id.btn_CLIP);
        operateCanvasView = (OperateCanvasView) findViewById(R.id.view_opc);

        btn_TRANSLATE.setOnClickListener(this);
        btn_SCALE.setOnClickListener(this);
        btn_ROTATE.setOnClickListener(this);
        btn_SKEW.setOnClickListener(this);
        btn_CLIP.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_TRANSLATE:
                operateCanvasView.mode = OperateCanvasView.MODE.TRANSLATE;
                operateCanvasView.invalidate();
                break;
            case R.id.btn_SCALE:
                operateCanvasView.mode = OperateCanvasView.MODE.SCALE;
                operateCanvasView.invalidate();
                break;
            case R.id.btn_ROTATE:
                operateCanvasView.mode = OperateCanvasView.MODE.ROTATE;
                operateCanvasView.invalidate();
                break;
            case R.id.btn_SKEW:
                operateCanvasView.mode = OperateCanvasView.MODE.SKEW;
                operateCanvasView.invalidate();
                break;
            case R.id.btn_CLIP:
                operateCanvasView.mode = OperateCanvasView.MODE.CLIP;
                operateCanvasView.invalidate();
                break;

        }
    }
}
