package BackUps;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.testlfm.R;

public class GoodEvening extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MusicManager musicManager = MusicManager.getMusicManager();
        musicManager.play(this, mediaPlayer, R.raw.bgm1);
    }
}
