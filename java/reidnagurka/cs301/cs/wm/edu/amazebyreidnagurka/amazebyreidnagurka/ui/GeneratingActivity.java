package reidnagurka.cs301.cs.wm.edu.amazebyreidnagurka.amazebyreidnagurka.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import reidnagurka.cs301.cs.wm.edu.amazebyreidnagurka.R;

/**
 * Created by Reid on 11/14/2017.
 */

public class GeneratingActivity extends Activity{
    Intent intentFromStart;
    private RelativeLayout layout;
    private Button testButton;
    private int skillLevel;
    private String mazeGen;
    private String driver;
    private ProgressBar progressBar;
    private TextView generatingText;
    private boolean manual;
    private int progressBarCounter = 0;
    private Handler handler = new Handler();

    private TextView noteToGrader;

    private boolean cancel = false;

    //TO DO:
    //save the maze generated for Revisit function in Project 7.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generating);

        setUpComponents();
        receiveIntents();


        //Following code adapted from: https://github.com/Salyder/progressbarExample/blob/master/app/src/main/java/com/example/brandonmain/progressbarexample2/MainActivity.java
        //Mocks generating a maze, will be changed once Maze functionality is added.
        //After closing Android Studio this code was altered automatically to what it is displayed before by the IDE.
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressBarCounter < 100){
                    progressBarCounter++;
                    android.os.SystemClock.sleep(50);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressBarCounter);
                        }
                    });
                }
                //ensures PlayActivity only starts if loading is never cancelled.
                if(!cancel) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            toPlay();
                        }
                    });
                }
            }
        }).start();

    }

    /**
     * initalizes all components. Used for clearer code. Checks whether the driver is manual and displays the correct gui elements
     * accordingly.
     */
    private void setUpComponents()
    {
        layout = (RelativeLayout) findViewById(R.id.layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        generatingText = (TextView) findViewById(R.id.generatingText);
        generatingText.setText("Now Generating Maze");
        noteToGrader = (TextView) findViewById(R.id.noteToGraderText);
    }

    /**
     * handles intents passed from AMazeActivity
     */
    private void receiveIntents()
    {
        intentFromStart = getIntent();
        skillLevel = intentFromStart.getIntExtra("skillLevel", 0);
//        Snackbar snackbar = Snackbar
//                .make(layout, "skillLevel: " + skillLevel, Snackbar.LENGTH_LONG);
//        snackbar.show();

        mazeGen = intentFromStart.getStringExtra("bldrAlg");
        driver = intentFromStart.getStringExtra("driverAlg");
        manual = intentFromStart.getBooleanExtra("manualMode", true);

    }

    /**
     * switches to playing screen.
     * Passes whether the driver should be manual or not.
     */
    public void toPlay()
    {
        Intent intent = new Intent(this, PlayActivity.class);

//        intent.putExtra("skillLevel", skillLevel);
//        intent.putExtra("bldrAlg", builder);
//        intent.putExtra("driverAlg", driver);
        intent.putExtra("manualMode", manual);
        startActivity(intent);
    }

    /**
     * overrides back button. Once maze is introduced will need to be updated to save the maze for reisit purpose.
     */
    @Override
    public void onBackPressed()
    {
        //tells the background loading thread to not move to PlayActivity since the back button was pressed.
        cancel = true;
        Intent intent = new Intent(this, AMazeActivity.class);
        startActivity(intent);
    }

}
