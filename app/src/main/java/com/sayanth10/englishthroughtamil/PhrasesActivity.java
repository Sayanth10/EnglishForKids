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

public class PhrasesActivity extends AppCompatActivity {

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

        words.add(new Word("Good Morning!","காலை வணக்கம்!", R.drawable.phrin, R.raw.goodmorning));
        words.add(new Word("Good Evening!","மாலை வணக்கம்!", R.drawable.phrin, R.raw.evening));
        words.add(new Word("What is your name?","உங்கள் பெயர் என்ன?", R.drawable.phrin, R.raw.your_name));
        words.add(new Word("My name is...","எனது பெயர்...", R.drawable.phrin, R.raw.myname));
        words.add(new Word("Where do you live?","நீங்கள் எங்கே வசிக்கிறீர்கள்?", R.drawable.phrin, R.raw.where_live));
        words.add(new Word("My country is Sri Lanka.","எனது நாடு இலங்கை.", R.drawable.phrin, R.raw.mycountry));
        words.add(new Word("How are you?","நீங்கள் எப்படி இருக்கிறீர்கள்?", R.drawable.phrin, R.raw.how_you));
        words.add(new Word("I am fine.","நான் நலமாக உள்ளேன்.", R.drawable.phrin, R.raw.fine));
        words.add(new Word("What do you do?","நீங்கள் என்ன செய்கிறீர்கள்?", R.drawable.phrin, R.raw.whatdoyou));
        words.add(new Word("I am a student.","நான் ஒரு மாணவன்.", R.drawable.phrin, R.raw.student));
        words.add(new Word("Come here.","இங்கே வா.", R.drawable.phrin, R.raw.come));
        words.add(new Word("Let's go.","போவோம்.", R.drawable.phrin, R.raw.go));

        WordAdapter adapter = new WordAdapter(this, words, R.color.phrasesbg);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {



                    mediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioId());
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
