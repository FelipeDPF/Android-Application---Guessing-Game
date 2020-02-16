package com.algonquincollege.depa0028.hilo;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Just inside your Activity or AppCompatActivity Class declaration
    // CLASS VARIABLES (by convention, class vars in upper-case)
    private static final String ABOUT_DIALOG_TAG = "About Dialog";
    private Button guess;
    private Button reset;
    private EditText userInput;
    int tries = 0;
    int guesses=0;
    int lowGuesses, highGuesses = 0;
    int wins, looses, perfectWin = 0;
    String userGuess;
    boolean resetOn= false;
    int randomNumber = (int)(Math.random() * 999 + 1); // remove this after testing\

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getSupportFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       guess = findViewById(R.id.GuessButton);
       reset = findViewById(R.id.ResetButton);
       userInput = findViewById(R.id.GuessText);
       // Toast.makeText(getApplicationContext(), "Random is:" + randomNumber, Toast.LENGTH_LONG).show();

        if(resetOn == false) {

           guess.setOnClickListener(new OnClickListener() {

               @Override
               public void onClick(View v) {

                   if (!validateUserInput(userInput.getText().toString(), ++tries, randomNumber)) {
                           userInput.requestFocus();
                       } else {
                           if (tries <= 5) {
                               // SUCCESS!! All user input has been validated.

                               if ( tries == 1){
                                   perfectWin++;
                               }
                               Toast.makeText(getApplicationContext(), "Superior win! ", Toast.LENGTH_LONG).show();
                                wins++;



                           } else if (tries >= 6 && tries <= 10) {
                               wins++;
                               Toast.makeText(getApplicationContext(), "Excellent win! ", Toast.LENGTH_LONG).show();

                           }

                       //  Log.i(LOG_TAG, "LEAVE onClick()");
                   }
               }
           });
       }

        reset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                guess = findViewById(R.id.GuessButton);
                reset = findViewById(R.id.ResetButton);
                userInput = findViewById(R.id.GuessText);
                userInput.setText("");
                userInput.setError("Good luck");
                tries = 0;
                resetOn= false;
                randomNumber = (int)(Math.random() * 999 + 1); // remove this after testing\
            }
        });

        reset.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View v) {

                Intent intent = new Intent( getApplicationContext(), ResultsActivity.class );
                intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP );
                intent.putExtra( "userInput",guesses );
                intent.putExtra( "totalGuesses", tries );
                intent.putExtra( "lowGuess", lowGuesses );
                intent.putExtra( "highGuess", highGuesses );
                intent.putExtra( "randomNumber", randomNumber );
                intent.putExtra( "numberOfWins", wins );
                intent.putExtra( "numberOfLosses", looses );
                intent.putExtra( "perfectGuess", perfectWin );

                startActivity( intent );
                return false;
            }

        });
    }

    protected boolean validateUserInput(String userGuess, int tries, int randomNumber) {
        this.userGuess = userGuess;

        if(tries > 10) {
            userInput.setError("Please Reset");
            looses++;
            resetOn = true;
            return false;
        }


            if (userGuess.isEmpty() || userGuess == null || !Character.isDigit(userGuess.charAt(0))) {
              //  Toast.makeText(getApplicationContext(), "Guess number =  " + tries, Toast.LENGTH_LONG).show();
                userInput.setError("Please try a number");
                return false;
            }
        guesses = Integer.parseInt(userGuess);
        if (guesses < 1 || guesses > 1000){
            userInput.setError("Guess between 1-1000");
            return false;
        }
         if (userGuess.equals(randomNumber)) {

            return true;

        } else if(Integer.valueOf(userGuess) < randomNumber){
             Toast.makeText(getApplicationContext(), "Guess too low", Toast.LENGTH_LONG).show();
         //   userInput.setError("Guess too low");
            lowGuesses++;
           // Toast.makeText(getApplicationContext(), "Guess number =  " + tries, Toast.LENGTH_LONG).show();
            return false;

        } else if(Integer.valueOf(userGuess) > randomNumber){
          //  Toast.makeText(getApplicationContext(), "Guess number =  " + tries, Toast.LENGTH_LONG).show();
             Toast.makeText(getApplicationContext(), "Guess too High", Toast.LENGTH_LONG).show();
           // userInput.setError("Guess too High");
            highGuesses++;
            return false;
            
        } else {
            return true;
        }
    }
}
