package Story;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lenovo.elapp.R;

/**
 * The first Shot
 * enter the Game(Story)
 *
 * Button to the task--> Simply time countdown*/
public class storyMainActivity extends AppCompatActivity {


    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_activity_main);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }

    /**Called when the user clicks the Send button*/
    public void sendMessage(View view){

        Intent intent =  new Intent(this,storySecondActivity.class);
        startActivity(intent);
    }
}
