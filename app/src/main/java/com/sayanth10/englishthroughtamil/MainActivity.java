package com.sayanth10.englishthroughtamil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Open NumbersActivity when click on numbers text view.
         */
        TextView numbers = findViewById(R.id.numbers);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(i);
            }
        });

        ImageView numbersImage = findViewById(R.id.number_image);
        numbersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(i);
            }
        });

        /**
         * Open Colors Activity when click on colors textview.
         */
        TextView colors = findViewById(R.id.colors);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(i);
            }
        });

        ImageView colorsImage = findViewById(R.id.color_image);
        colorsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(i);
            }
        });

        /**
         * Open family members activity when click on family textview.
         */
        TextView familyMembers = findViewById(R.id.family);
        familyMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(i);
            }
        });

        ImageView familyMembersImage = findViewById(R.id.family_image);
        familyMembersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(i);
            }
        });


        /**
         * Open phrases activity when click on phrases textview.
         */
        TextView phrases = findViewById(R.id.phrases);
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(i);
            }
        });

        ImageView phrasesImage = findViewById(R.id.phrase_image);
        phrasesImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(i);
            }
        });


        /**
         * Open animals activity when click on animal textview.
         */

        TextView animals = findViewById(R.id.animals);
        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AnimalsActivity.class);
                startActivity(i);
            }
        });

        ImageView animalsImage = findViewById(R.id.animals_image);
        animalsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AnimalsActivity.class);
                startActivity(i);
            }
        });


        /**
         * Open birds activity when click on birds textview.
         */
        TextView birds = findViewById(R.id.birds);
        birds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BirdsActivity.class);
                startActivity(i);
            }
        });

        ImageView birdImage = findViewById(R.id.birds_image);
        birdImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BirdsActivity.class);
                startActivity(i);
            }
        });


        /**
         * Open fruits activity when click on fruits textview.
         */
        TextView fruits = findViewById(R.id.fruits);
        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FruitsActivity.class);
                startActivity(i);
            }
        });

        ImageView fruitsImage = findViewById(R.id.fruits_image);
        fruitsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FruitsActivity.class);
                startActivity(i);
            }
        });


        /**
         * Open vegetables activity when click on vegetables textview.
         */
        TextView vegetables = findViewById(R.id.vegetables);
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VegetablesActivity.class);
                startActivity(i);
            }
        });

        ImageView vegImage = findViewById(R.id.vegetables_image);
        vegImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, VegetablesActivity.class);
                startActivity(i);
            }
        });


        /**
         * Open bodyparts activity when click on bodyparts textview.
         */
        TextView bodyParts = findViewById(R.id.body_parts);
        bodyParts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BodyPartsActivity.class);
                startActivity(i);
            }
        });

        ImageView bodyImage = findViewById(R.id.bodyparts_image);
        bodyImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BodyPartsActivity.class);
                startActivity(i);
            }
        });
    }
}
