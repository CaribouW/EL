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

////      当时间到时 Done！
//        if(true){
//            TextView textView = (TextView)findViewById(R.id.buttonStory2);
//            textView.setText("Done!");
//        }
    }



    /**Called when the user clicks the Send button*/
    public void sendMessage(View view){
        Intent intent =  new Intent(this,CircleView.class);
        startActivity(intent);
    }


}
