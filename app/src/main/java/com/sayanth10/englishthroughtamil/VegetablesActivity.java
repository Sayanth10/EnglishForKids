package com.sayanth10.englishthroughtamil;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class VegetablesActivity extends AppCompatActivity {


    private MediaPlayer mediaPlayer;

    private AudioManager audioManager;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {


        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.words_list);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Pumpkin","பூசணிக்காய்", R.drawable.pumpkin, R.raw.pumpkin));
        words.add(new Word("Brinjal","கத்தரிக்காய்", R.drawable.brinjal, R.raw.brinjal));
        words.add(new Word("Tomato","தக்காளி", R.drawable.tomato, R.raw.tomato));
        words.add(new Word("Chilli","மிளகாய்", R.drawable.chilli, R.raw.chilli));
        words.add(new Word("Onion","வெங்காயம்", R.drawable.onion, R.raw.onion));
        words.add(new Word("Carrot","கேரட்", R.drawable.carrot, R.raw.carrot));
        words.add(new Word("Beans","அவரை", R.drawable.beans, R.raw.beans));
        words.add(new Word("Cucumber","வெள்ளரிக்காய்", R.drawable.cucumber, R.raw.cucumber));
        words.add(new Word("Drumstick","முருங்கைக்காய்", R.drawable.drumstick, R.raw.drumsticks));
        words.add(new Word("Beetroot","பீட்ரூட்", R.drawable.beetroot, R.raw.beetroot));
        words.add(new Word("Radish","முள்ளங்கி", R.drawable.radish, R.raw.radish));
        words.add(new Word("Cabbage","கோவா", R.drawable.goa, R.raw.cabbage));
        words.add(new Word("Snake-gourd","புடலங்காய்", R.drawable.snake, R.raw.snakegourd));
        words.add(new Word("Bitter-gourd","பாகற்காய்", R.drawable.bitter, R.raw.bittergourd));




        WordAdapter adapter = new WordAdapter(this, words, R.color.vegetablesbg);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {



                    mediaPlayer = MediaPlayer.create(VegetablesActivity.this, word.getAudioId());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            releaseMediaPlayer();
                        }
                    });
                }
            }
        });

    }

    @Override
    protected void onStop(){
        super.onStop();
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
            audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
