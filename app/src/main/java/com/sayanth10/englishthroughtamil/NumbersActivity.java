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

public class NumbersActivity extends AppCompatActivity {

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


        words.add(new Word("One","ஒன்று", R.drawable.one, R.raw.one));
        words.add(new Word("Two","இரண்டு", R.drawable.two, R.raw.two));
        words.add(new Word("Three","மூன்று", R.drawable.three, R.raw.three));
        words.add(new Word("Four","நான்கு", R.drawable.four, R.raw.four));
        words.add(new Word("Five","ஐந்து", R.drawable.five, R.raw.five));
        words.add(new Word("Six","ஆறு", R.drawable.six, R.raw.six));
        words.add(new Word("Seven","ஏழு", R.drawable.seven, R.raw.seven));
        words.add(new Word("Eight","எட்டு", R.drawable.eight, R.raw.eight));
        words.add(new Word("Nine","ஒன்பது", R.drawable.nine, R.raw.nine));
        words.add(new Word("Ten","பத்து", R.drawable.ten, R.raw.ten));
        words.add(new Word("Twenty","இருபது", R.drawable.twenty, R.raw.twenty));
        words.add(new Word("Thirty","முப்பது", R.drawable.thirty, R.raw.thirty));
        words.add(new Word("Forty","நாற்பது", R.drawable.forty, R.raw.forty));
        words.add(new Word("Fifty","ஐம்பது", R.drawable.fifty, R.raw.fifty));
        words.add(new Word("Sixty","அறுபது", R.drawable.sixty, R.raw.sixty));
        words.add(new Word("Seventy","எழுபது", R.drawable.seventy, R.raw.seventy));
        words.add(new Word("Eighty","எண்பது", R.drawable.eighty, R.raw.eighty));
        words.add(new Word("Ninety","தொண்ணூறு", R.drawable.ninety, R.raw.ninety));
        words.add(new Word("Hundred","நூறு", R.drawable.hundred, R.raw.hundred));




        WordAdapter adapter = new WordAdapter(this, words, R.color.numberbg);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {



                    mediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioId());
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
