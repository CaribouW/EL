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

        Button button1 = (Button) findViewById(R.id.buttonStory6);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(storySixthActivity.this, storySixthPlusActivity.class);
                startActivity(intent);

            }
        });
    }

}
