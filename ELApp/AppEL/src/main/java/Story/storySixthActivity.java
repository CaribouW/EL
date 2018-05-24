package Story;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.elapp.NewTaskActivity;
import com.example.lenovo.elapp.R;

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


        Button button = (Button)findViewById(R.id.buttonStory6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                此处增加一个浮动窗口
                Intent intent =  new Intent(storySixthActivity.this, storySixthPlusActivity.class);
                startActivity(intent);
            }
        });
    }

//    /**Called when the user clicks the Send button*/
//    public void sendMessage(View view){
//        Intent intent =  new Intent(this,storySeventhActivity.class);
//        startActivity(intent);
//    }
}
