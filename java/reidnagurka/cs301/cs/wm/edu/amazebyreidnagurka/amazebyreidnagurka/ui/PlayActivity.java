package reidnagurka.cs301.cs.wm.edu.amazebyreidnagurka.amazebyreidnagurka.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import reidnagurka.cs301.cs.wm.edu.amazebyreidnagurka.R;

public class PlayActivity extends Activity {

    private RelativeLayout layout;
    private Intent intentFromGenerating;
    private boolean manual;
    private Button winButton;
    private Button loseButton;
    private Button leftButton;
    private Button rightButton;
    private Button upButton;
    private Button downButton;
    private ImageButton playPauseButton;
    private ImageButton pauseButton;
    private int automaticState;
    private Intent newIntent;
    private ProgressBar energyBar;
    private TextView energyLeftText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        receiveIntents();
        setUpComponents();
    }

    /**
     * called when the play or pause button is clicked. They are two separate Image buttons, but call the same method.
     * Keeps track of a counter and displays the correct image accordingly. Will add more functionality once maze is handled.
     * @param view
     */
    public void playPauseButtonHandler(View view) {
        if(automaticState == 0)
        {
            playPauseButton.setVisibility(View.INVISIBLE);
            pauseButton.setVisibility(View.VISIBLE);
            automaticState = 1;
        }
        else
        {
            playPauseButton.setVisibility(View.VISIBLE);
            pauseButton.setVisibility(View.INVISIBLE);
            automaticState = 0;
        }

    }

    /**
     * Not fully implemented, will be handled in Project 7
     * @param view
     */
    public void leftButtonHandler(View view) {
        Log.v("move", "Left Button pressed");
    }

    public void rightButtonHandler(View view) {
        Log.v("move", "Right Button pressed");
    }

    public void downButtonHandler(View view) {
        Log.v("move", "Down Button pressed");
    }

    public void upButtonHandler(View view) {
        Log.v("move", "Up Button pressed");
    }

    public void loseButtonHandler(View view)
    {
        lose();
    }

    public void winButtonHandler(View view) { win(); }

    public void mapButtonHandler(View view)
    {
        Log.v("map", "Toggle Map was pressed.");
    }

    public void solutionButtonHandler(View view)
    {
        Log.v("solution", "Toggle Solution was pressed.");
    }

    public void wallsButtonHandler(View view)
    {
        Log.v("walls", "Toggle Walls was pressed.");
    }


    /**
     * initalizes all components. Used for clearer code. Checks whether the driver is manual and displays the correct gui elements
     * accordingly.
     */
    private void setUpComponents()
    {
        layout = (RelativeLayout) findViewById(R.id.layout);
        winButton = (Button) findViewById(R.id.winButton);
        loseButton = (Button) findViewById(R.id.loseButton);
        leftButton = (Button) findViewById(R.id.leftButton);
        rightButton = (Button) findViewById(R.id.rightButton);
        upButton = (Button) findViewById(R.id.upButton);
        downButton = (Button) findViewById(R.id.downButton);

        playPauseButton = (ImageButton) findViewById(R.id.playPauseButton);
        pauseButton = (ImageButton) findViewById(R.id.pauseButton);

        energyBar = (ProgressBar) findViewById(R.id.energyProgressBar);
        energyBar.setRotation(180);
        energyBar.setMax(3000);

        energyLeftText = (TextView) findViewById(R.id.energyRemainingText);

        if(manual)
        {

            winButton.setVisibility(View.VISIBLE);
            loseButton.setVisibility(View.VISIBLE);
            leftButton.setVisibility(View.VISIBLE);
            rightButton.setVisibility(View.VISIBLE);
            upButton.setVisibility(View.VISIBLE);
            downButton.setVisibility(View.VISIBLE);
        }
        else
        {
            playPauseButton.setVisibility(View.VISIBLE);
            automaticState =  0;
        }

    }

    /**
     * gets Intents from GeneratingActivity
     */
    private void receiveIntents()
    {
        intentFromGenerating = getIntent();
        manual = intentFromGenerating.getBooleanExtra("manualMode", true);
    }
    /**
     * for project 6, will be called when lose button is clicked.
     */
    private void lose()
    {
        //pass intent with a boolean they lost.
        newIntent = new Intent(this, FinishActivity.class);
        newIntent.putExtra("victory", false);
        startActivity(newIntent);
    }

    /**
     * for project 6, will be called when win button is clicked.
     */
    private void win()
    {
        newIntent = new Intent(this, FinishActivity.class);
        newIntent.putExtra("victory", true);
        Log.v("win", "win button is clicked, line above startActivity ran");
        startActivity(newIntent);
    }

    /**
     * overrides back button. Once maze is introduced will need to be updated to save the maze for reisit purpose.
     */
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, AMazeActivity.class);
        startActivity(intent);
    }

}
