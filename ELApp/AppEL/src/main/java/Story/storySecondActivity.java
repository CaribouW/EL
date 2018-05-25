package Story;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.bean.DateType;
import com.example.lenovo.elapp.CircleView;
import com.example.lenovo.elapp.ClockActivity;
import com.example.lenovo.elapp.R;
import com.moos.library.CircleProgressView;

import Activitys.MainActivity;
import Managers.CountingDown;
import Tmp_lib.myDatePickDialog;
import cn.iwgang.countdownview.CountdownView;

/**
 * Do task(time Countdown)
 * button(Next!)-->The second shot
 * */
public class storySecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity_second);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        Button button1 = (Button) findViewById(R.id.buttonStory2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(storySecondActivity.this, ClockActivity.class);
//                startActivity(intent);
                TextView textView = (TextView)findViewById(R.id.textViewStory2);
                textView.setText("I' m running!");

//                CircleView circleView = (CircleView)findViewById(R.id.progressView_circleStory2);
//                circleView.setVisibility(View.VISIBLE);
            }
        });

        Button button2 = (Button) findViewById(R.id.buttonStory2next);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(storySecondActivity.this, storyThirdActivity.class);
                startActivity(intent);

            }
        });
    }

}
