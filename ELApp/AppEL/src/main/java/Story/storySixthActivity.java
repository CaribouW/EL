package Story;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lenovo.elapp.NewTaskActivity;
import com.example.lenovo.elapp.R;

import java.time.LocalDate;

import Managers.TimeManager;
import Tmp_lib.timer;

/**
 * Do task(time Countdown && Input own task && music)
 * button(Next!)-->Done
 * */
public class storySixthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity_sixth);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
//        int h1 = Integer.parseInt(TimeManager.getTimeManager().getHour());
//        int m1 = Integer.parseInt(TimeManager.getTimeManager().getMinute());
//        int s1 = Integer.parseInt(TimeManager.getTimeManager().getSecond());

        Button button1 = (Button) findViewById(R.id.buttonStory6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int sum = (Integer.parseInt(TimeManager.getTimeManager().getHour())-h1)*3600 + (Integer.parseInt(TimeManager.getTimeManager().getMinute())-m1)*60 + (Integer.parseInt(TimeManager.getTimeManager().getSecond())-s1);
//                TextView textView = (TextView)findViewById(R.id.St6p_textView3);
//                textView.setText(String.valueOf(sum));
                Intent intent = new Intent(storySixthActivity.this, storySixthPlusActivity.class);
                startActivity(intent);

            }
        });
    }

}
