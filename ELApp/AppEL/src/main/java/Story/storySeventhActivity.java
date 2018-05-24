package Story;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.elapp.NewTaskActivity;
import com.example.lenovo.elapp.R;

import Activitys.MainActivity;

public class storySeventhActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity_seventh);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
//        Button button = (Button)findViewById(R.id.buttonStory7);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =  new Intent(storySeventhActivity.this, NewTaskActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    /**Called when the user clicks the Send button*/
    public void sendMessage(View view){
        Intent intent =  new Intent(storySeventhActivity.this, NewTaskActivity.class);
        startActivity(intent);
    }
}
