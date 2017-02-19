package scut.com.learncanvas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btn_base,btn_text,btn_path,btn_opc,btn_myview,btn_bezier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_base = (Button) findViewById(R.id.btn_base);
        btn_text = (Button) findViewById(R.id.btn_text);
        btn_path = (Button) findViewById(R.id.btn_path);
        btn_opc = (Button) findViewById(R.id.btn_opc);
        btn_myview = (Button) findViewById(R.id.btn_myview);
        btn_bezier = (Button) findViewById(R.id.btn_bezier);

        btn_base.setOnClickListener(this);
        btn_text.setOnClickListener(this);
        btn_path.setOnClickListener(this);
        btn_opc.setOnClickListener(this);
        btn_myview.setOnClickListener(this);
        btn_bezier.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_base:
                startActivity(new Intent(this,LearnBaseActivity.class));
                break;
            case R.id.btn_text:
                startActivity(new Intent(this,LearnTextActivity.class));
                break;
            case R.id.btn_path:
                startActivity(new Intent(this,LearnPathActivity.class));
                break;
            case R.id.btn_bezier:
                startActivity(new Intent(this,LearnBezierActivity.class));
                break;
            case R.id.btn_opc:
                startActivity(new Intent(this,LearnOPCActivity.class));
                break;
            case R.id.btn_myview:
                startActivity(new Intent(this,MyViewActivity.class));
                break;
        }
    }
}
