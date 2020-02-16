package com.algonquincollege.depa0028.hilo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsActivity extends AppCompatActivity {

    private TextView RandomNumber;
    private TextView GuessNumber;
    private TextView TotalGuesses;
    private TextView HighGuesses;
    private TextView LowGuesses;
    private TextView PerfectWins;
    private TextView WinsNumber;
    private TextView LossesNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_);

        RandomNumber = findViewById( R.id.RandomNumber);
        GuessNumber = findViewById( R.id.GuessNumber);
        TotalGuesses = findViewById( R.id.TotalGuesses);
        HighGuesses = findViewById( R.id.HighGuesses);
        LowGuesses = findViewById( R.id.LowGuesses);
        PerfectWins = findViewById( R.id.PerfectWins);
        WinsNumber = findViewById( R.id.WinsNumber);
        LossesNumber = findViewById( R.id.LossesNumber);

        // Get the bundle of extras that was sent to this activity
        Bundle bundle = getIntent().getExtras();
        if ( bundle != null ) {
            int randomNumber = bundle.getInt("randomNumber");
            int guessNumber = bundle.getInt("userInput");
            int totalGuesses = bundle.getInt("totalGuesses");
            int highGuess = bundle.getInt("highGuess");
            int lowGuess = bundle.getInt("lowGuess");
            int perfectGuess = bundle.getInt("perfectGuess");
            int numberOfWins = bundle.getInt("numberOfWins");
            int numberOfLosses = bundle.getInt("numberOfLosses");

            RandomNumber.setText("Random number = "+ String.valueOf(randomNumber) );
            GuessNumber.setText("User guess = "+ guessNumber );
            TotalGuesses.setText("Total of guesses = "+ String.valueOf(totalGuesses) );
            HighGuesses.setText("High number of guesses = "+ String.valueOf(highGuess) );
            LowGuesses.setText("Low number of guesses = "+ String.valueOf(lowGuess) );
            PerfectWins.setText("Perfect win = "+ String.valueOf(perfectGuess) );
            WinsNumber.setText("Number of wins = "+ String.valueOf(numberOfWins) );
            LossesNumber.setText("Number of losses = "+ String.valueOf(numberOfLosses) );

        }
    }
}
