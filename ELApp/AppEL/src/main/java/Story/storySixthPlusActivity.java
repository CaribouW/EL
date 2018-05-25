package Story;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.elapp.R;

/**
 * Created by sdj on 2018/5/25.
 */


public class storySixthPlusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity_sixthplus);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }

        Button buttonGo = (Button)findViewById(R.id.buttonStory6go);
        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                这是浮动窗口的 继续
                Intent intent =  new Intent(storySixthPlusActivity.this, storySeventhActivity.class);
                startActivity(intent);
            }
        });

        Button buttonBack = (Button)findViewById(R.id.buttonStory6back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                这是浮动窗口的 返回
                Intent intent =  new Intent(storySixthPlusActivity.this, storySixthActivity.class);
                startActivity(intent);
            }
        });
    }

//    /**Called when the user clicks the Send button*/
//    public void sendMessage(View view){
//        Intent intent =  new Intent(this,storyFourthActivity.class);
//        startActivity(intent);
//    }
}