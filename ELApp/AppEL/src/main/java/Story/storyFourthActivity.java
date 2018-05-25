package Story;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.codbking.widget.bean.DateType;
import com.example.lenovo.elapp.NewTaskActivity;
import com.example.lenovo.elapp.R;

import Tmp_lib.myDatePickDialog;

/**
 * Do task(time Countdown && Input own task)
 * button(Next!)-->The last shot
 * */
public class storyFourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity_fourth);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        //跳转到 任务+计时 界面
//        Button button1 = (Button) findViewById(R.id.buttonStory4);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(storyFourthActivity.this, );
//                startActivity(intent);
//
//            }
//        });

        //跳转到下一界面Story5
        Button button2 = (Button) findViewById(R.id.buttonStory4next);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(storyFourthActivity.this, storyFifthActivity.class);
                startActivity(intent);

            }
        });
    }



}
