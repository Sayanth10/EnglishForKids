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

public class AnimalsActivity extends AppCompatActivity {


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

        words.add(new Word("Dog","நாய்", R.drawable.dog, R.raw.dog));
        words.add(new Word("Cat","பூனை", R.drawable.cat, R.raw.cat));
        words.add(new Word("Cow","பசு", R.drawable.cow, R.raw.cow));
        words.add(new Word("Horse","குதிரை", R.drawable.horse, R.raw.horse));
        words.add(new Word("Rabbit","முயல்", R.drawable.rabbit, R.raw.rabbit));
        words.add(new Word("Lion","சிங்கம்", R.drawable.lion, R.raw.lion));
        words.add(new Word("Tiger","புலி", R.drawable.tiger, R.raw.tiger));
        words.add(new Word("Bear","கரடி", R.drawable.bear, R.raw.bear));
        words.add(new Word("Deer","மான்", R.drawable.deer, R.raw.deer));
        words.add(new Word("Elephant","யானை", R.drawable.elephant, R.raw.elephant));
        words.add(new Word("Monkey","குரங்கு", R.drawable.monkey, R.raw.monkey));
        words.add(new Word("Donkey","கழுதை", R.drawable.donkey, R.raw.donkey));
        words.add(new Word("Buffalo","எருமை", R.drawable.buffalo, R.raw.buffalo));
        words.add(new Word("Goat","ஆடு", R.drawable.goat, R.raw.goat));
        words.add(new Word("Zebra","வரிக்குதிரை", R.drawable.zebra, R.raw.zebra));
        words.add(new Word("Fox","நரி", R.drawable.fox, R.raw.fox));
        words.add(new Word("Cheetah","சிறுத்தை", R.drawable.cheetah, R.raw.cheetah));
        words.add(new Word("Squirrel","அணில்", R.drawable.squirrel, R.raw.squirrel));
        words.add(new Word("Camel","ஒட்டகம்", R.drawable.camel, R.raw.camel));
        words.add(new Word("Giraffe","ஒட்டகச்சிவிங்கி", R.drawable.giraffe, R.raw.giraffe));





        WordAdapter adapter = new WordAdapter(this, words, R.color.animalsbg);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView <?> parent, View view, int position, long id) {
                Word word = words.get(position);
                releaseMediaPlayer();

                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {



                    mediaPlayer = MediaPlayer.create(AnimalsActivity.this, word.getAudioId());
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
