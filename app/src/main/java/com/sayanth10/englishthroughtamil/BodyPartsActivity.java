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

public class BodyPartsActivity extends AppCompatActivity {


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

        words.add(new Word("Head","தலை", R.drawable.head, R.raw.head));
        words.add(new Word("Face","முகம்", R.drawable.face, R.raw.face));
        words.add(new Word("Neck","கழுத்து", R.drawable.neck, R.raw.neck));
        words.add(new Word("Eye","கண்", R.drawable.eye, R.raw.eye));
        words.add(new Word("Eyebrow","இமை", R.drawable.eyebrow, R.raw.eyebrow));
        words.add(new Word("Ear","காது", R.drawable.ear, R.raw.ear));
        words.add(new Word("Nose","மூக்கு", R.drawable.nose, R.raw.nose));
        words.add(new Word("Tongue","நாக்கு", R.drawable.tongue, R.raw.tongue));
        words.add(new Word("Tooth","பல்", R.drawable.tooth, R.raw.tooth));
        words.add(new Word("Hand","கை", R.drawable.hand, R.raw.hand));
        words.add(new Word("Shoulder","தோள்", R.drawable.shoulder, R.raw.shoulder));
        words.add(new Word("Finger","விரல்", R.drawable.finger, R.raw.finger));
        words.add(new Word("Nail","நகம்", R.drawable.nail, R.raw.nail));
        words.add(new Word("Leg","கால்", R.drawable.leg, R.raw.leg));
        words.add(new Word("Knee","முழங்கால்", R.drawable.knee, R.raw.knee));




        WordAdapter adapter = new WordAdapter(this, words, R.color.bodyPartsbg);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {



                    mediaPlayer = MediaPlayer.create(BodyPartsActivity.this, word.getAudioId());
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
