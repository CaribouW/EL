package BackUps;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import com.example.floatwindow.FloatWindow;
import com.example.floatwindow.MoveType;
import com.example.floatwindow.PermissionListener;
import com.example.floatwindow.Screen;
import com.example.floatwindow.ViewStateListener;
import com.example.lenovo.elapp.NewTaskActivity;
import com.example.lenovo.elapp.R;

import Activitys.MainActivity;
import Managers.MusicManager;
import Story.storyMainActivity;

import static android.content.ContentValues.TAG;

public class FloatingPlayer {
    private boolean IsPause = true;
    private boolean IsBegin = false;
    private MusicManager musicManager = MusicManager.getMusicManager();
    private MediaPlayer mediaPlayer = musicManager.GetMediaPlayer();
    private Context context;

    public void floatStart(Context context, ImageView imageView) {
        this.context = context;
        imageView.setOnClickListener(v -> {
            if (!IsBegin) {
                mediaPlayer = musicManager.play(context, mediaPlayer, R.raw.bgm12);
                IsBegin = true;
            }
            if (IsPause) {
                mediaPlayer = musicManager.ContinueToPlay(mediaPlayer);
                IsPause = false;
            } else {
                mediaPlayer = musicManager.pause(mediaPlayer);
                IsPause = true;
            }
        });

        FloatWindow
                .with(context.getApplicationContext())
                .setView(imageView)
                .setWidth(Screen.width, 0.2f) //设置悬浮控件宽高
                .setHeight(Screen.width, 0.2f)
                .setX(Screen.width, 0.8f)
                .setY(Screen.height, 0.3f)
                .setMoveType(MoveType.slide, 100, -100)
                .setMoveStyle(500, new BounceInterpolator())
                .setFilter(true, MainActivity.class, NewTaskActivity.class, storyMainActivity.class)
                .setViewStateListener(mViewStateListener)
                .setPermissionListener(mPermissionListener)
                .setDesktopShow(true)
                .build();

    }

    private PermissionListener mPermissionListener = new PermissionListener() {
        @Override
        public void onSuccess() {
            Log.d(TAG, "onSuccess");
        }

        @Override
        public void onFail() {
            Log.d(TAG, "onFail");
        }
    };

    private ViewStateListener mViewStateListener = new ViewStateListener() {
        MediaPlayer mediaPlayer = new MediaPlayer();

        @Override
        public void onPositionUpdate(int x, int y) {
            MusicManager.getMusicManager().play(context, mediaPlayer, R.raw.water);
            Log.d(TAG, "onPositionUpdate: x=" + x + " y=" + y);
        }

        @Override
        public void onShow() {
            Log.d(TAG, "onShow");
        }

        @Override
        public void onHide() {
            Log.d(TAG, "onHide");
        }

        @Override
        public void onDismiss() {
            Log.d(TAG, "onDismiss");
        }

        @Override
        public void onMoveAnimStart() {
            Log.d(TAG, "onMoveAnimStart");
        }

        @Override
        public void onMoveAnimEnd() {
            Log.d(TAG, "onMoveAnimEnd");
            mediaPlayer = musicManager.pause(mediaPlayer);
            MusicManager.getMusicManager().play(context, mediaPlayer, R.raw.eff_1);
        }

        @Override
        public void onBackToDesktop() {
            Log.d(TAG, "onBackToDesktop");
        }
    };
}
