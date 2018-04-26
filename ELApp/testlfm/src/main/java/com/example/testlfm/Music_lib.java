package com.example.testlfm;

/*
*1. To get the source
* a. 用户在应用中事先自带的resource资源
例如：MediaPlayer.create(this, R.raw.test);
b. 存储在SD卡或其他文件路径下的媒体文件
例如：mp.setDataSource("/sdcard/test.mp3");
c. 网络上的媒体文件
例如：mp.setDataSource("http://www.citynorth.cn/music/confucius.mp3");
* */

//尚未实现某一个序列音乐播放
//暂时未考虑service  ——2018.4.18

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class Music_lib {
    /**
     * Get the mediaPlayer,and you have to choose the source of the music.
     */
    public static MediaPlayer GetMediaPlayer() {
        return new MediaPlayer();
    }

    public static MediaPlayer play(Context context, MediaPlayer mediaPlayer, int rawFile) {
        mediaPlayer = playRes(context, mediaPlayer, rawFile);
        return mediaPlayer;
    }

    public static MediaPlayer play(Context context, MediaPlayer mediaPlayer, String source) {
        try {
            mediaPlayer = playAssets(context, mediaPlayer, source);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mediaPlayer;

    }

    /**
     * @param source :The external source(like in the sdcards)
     */
    public static MediaPlayer playExternal(MediaPlayer mediaPlayer, String source) {

        try {
            if (mediaPlayer == null) {
                mediaPlayer = Music_lib.GetMediaPlayer();
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.start();
                return mediaPlayer;
            }
            File file = new File(Environment.getExternalStorageDirectory()
                    , "/music/" + source);
            mediaPlayer.reset();
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepareAsync();
            MediaPlayer finalMediaPlayer = mediaPlayer;
            mediaPlayer.setOnPreparedListener(mp -> finalMediaPlayer.start());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediaPlayer;
    }

    public static MediaPlayer ContinueToPlay(MediaPlayer mediaPlayer) {
        if (mediaPlayer == null) {
            mediaPlayer = Music_lib.GetMediaPlayer();
        }
        mediaPlayer.start();
        return mediaPlayer;
    }

    public static MediaPlayer pause(MediaPlayer mediaPlayer) {
        try {
            if (mediaPlayer == null)
                return null;
            mediaPlayer.pause();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mediaPlayer;
    }

    public static boolean isPlay(MediaPlayer mediaPlayer) {
        return mediaPlayer.isPlaying();
    }

    public static MediaPlayer stop(MediaPlayer mediaPlayer) {
        if (mediaPlayer == null)
            mediaPlayer = new MediaPlayer();
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
//            mediaPlayer.release();
            mediaPlayer.prepareAsync();
        }
        return mediaPlayer;
    }

    /**
     * @param pro :True if you want to play the music loop.
     */
    public static MediaPlayer LoopPlay(Context context, MediaPlayer mediaPlayer,
                                       String source, boolean pro) {
        mediaPlayer.setLooping(pro);
        mediaPlayer.setOnCompletionListener(mp -> {
            if (pro) {
                play(context, mediaPlayer, source);
            } else stop(mediaPlayer);
        });
        return mediaPlayer;
    }

    public static MediaPlayer LoopPlay(Context context, MediaPlayer mediaPlayer,
                                       int rawFile, boolean pro) {
        mediaPlayer.setLooping(pro);
        mediaPlayer.setOnCompletionListener(mp -> {
            if (pro) {
                play(context, mediaPlayer, rawFile);
            } else stop(mediaPlayer);
        });
        return mediaPlayer;
    }

    public static MediaPlayer LoopPlayExternal(MediaPlayer mediaPlayer, String source, boolean pro) {
        mediaPlayer.setLooping(pro);
        mediaPlayer.setOnCompletionListener(mp -> {
            if (pro) playExternal(mediaPlayer, source);
            else stop(mediaPlayer);
        });
        return mediaPlayer;
    }

    public static MediaPlayer ChangeToPlayAnotherExternal(MediaPlayer mediaPlayer, String source) {
        mediaPlayer = Music_lib.stop(mediaPlayer);
        mediaPlayer = Music_lib.playExternal(mediaPlayer, source);
        return mediaPlayer;
    }

    public static MediaPlayer ChangeToPlayAnother(Context context, MediaPlayer mediaPlayer
            , String string) {
        mediaPlayer = Music_lib.stop(mediaPlayer);
        mediaPlayer = Music_lib.play(context, mediaPlayer, string);
        return mediaPlayer;
    }

    public static MediaPlayer ChangeToPlayAnother(Context context, MediaPlayer mediaPlayer
            , int rawFile) {
        mediaPlayer = Music_lib.stop(mediaPlayer);
        mediaPlayer = Music_lib.play(context, mediaPlayer, rawFile);
        return mediaPlayer;
    }

    /**
     * Get the mediaPlayer from the assets/music.
     *
     * @param context     :The Context to play the music.
     * @param mediaPlayer :The Target mediaPlayer.
     * @param string      :The file name.
     */
    private static MediaPlayer playAssets(Context context, MediaPlayer mediaPlayer, String string) throws IOException {
        if (mediaPlayer == null) {
            mediaPlayer = Music_lib.GetMediaPlayer();
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            return mediaPlayer;
        }
        mediaPlayer.reset();
        AssetManager am = context.getAssets();
        AssetFileDescriptor afd = am.openFd("music/" + string);
        mediaPlayer.setDataSource(afd.getFileDescriptor());
        mediaPlayer.prepareAsync();
        MediaPlayer finalMediaPlayer = mediaPlayer;
        mediaPlayer.setOnPreparedListener(mp -> finalMediaPlayer.start());
        return mediaPlayer;
    }

    private static MediaPlayer playRes(Context context, MediaPlayer mediaPlayer, int rawFile) {
        if (mediaPlayer == null) {
            mediaPlayer = Music_lib.GetMediaPlayer();
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            return mediaPlayer;
        }
        mediaPlayer.reset();
        try {
            Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + rawFile);
            mediaPlayer.setDataSource(context, uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        MediaPlayer finalMediaPlayer = mediaPlayer;
        mediaPlayer.setOnPreparedListener(mp -> finalMediaPlayer.start());
        return mediaPlayer;
    }
}


/*
*一个button同时实现暂停播放和继续播放，需要在activity里加一个private boolean字段IsPause
* 此外，方法play不能够放在“实现暂停、继续”的回调函数或者事件监听器中（不然的话还是会从头开始播放）
* play方法属于确定了mediaplayer的来源，并且播放，请放在有关于音乐选择的地方
* if (IsPause) {
                mediaPlayer = Music_lib.ContinueToPlay(mediaPlayer);
                IsPause = false;
            } else {
                mediaPlayer = Music_lib.pause(mediaPlayer);
                IsPause = true;
            }
*——4.21
* 注：
* */


/*
* 实现了一个函数Play直接同时播放assets和res内的文件
* 同时让mediaPlayer的创建完全由一个函数完成
* ———4.26
* */
