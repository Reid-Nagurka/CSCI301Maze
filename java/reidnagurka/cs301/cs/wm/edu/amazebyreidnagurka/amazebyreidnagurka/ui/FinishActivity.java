package reidnagurka.cs301.cs.wm.edu.amazebyreidnagurka.amazebyreidnagurka.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import reidnagurka.cs301.cs.wm.edu.amazebyreidnagurka.R;

public class FinishActivity extends Activity {
    private RelativeLayout layout;
    private TextView congratsText;
    private TextView energyConsumptionText;
    private TextView pathLengthText;
    private Intent intentFromPlay;
    private ImageView victoryImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        setUpComponents();
        displayCongrats();
    }

    /**
     * Used to handle when user wants to play again.
     * @param view
     */
    public void playAgainHandler(View view)
    {
        Intent intent = new Intent(this, AMazeActivity.class);
        //Once maze element is incorporated, pass the maze that was just played.
        startActivity(intent);
    }

    /**
     * initalizes all components. Used for clearer code. Checks whether the driver is manual and displays the correct gui elements
     * accordingly.
     */
    private void setUpComponents()
    {
        layout = (RelativeLayout) findViewById(R.id.layout);
        congratsText = (TextView) findViewById(R.id.congrats);
        congratsText.setTextSize(20);
        intentFromPlay = getIntent();
        energyConsumptionText = (TextView) findViewById(R.id.energyConsumptionText);
        pathLengthText = (TextView) findViewById(R.id.pathLengthText);
        victoryImage = (ImageView) findViewById(R.id.victoryImage);
    }

    /**
     * Sets the text and Image depending on whether the user successfully navigated the maze.
     */
    private void displayCongrats()
    {
        boolean victory = intentFromPlay.getBooleanExtra("victory", true);
        if(victory)
        {
            congratsText.setText("Congratulations! You won!");
        }
        else
        {
            congratsText.setText("Sorry, you lost! The Minotaur has bested you.");
            victoryImage.setImageResource(R.mipmap.lost_image_foreground);

        }
    }

    /**
     * will be used to find and display the total path length and energy consumed once Maze functionality is added.
     */
    private void displayEnergyAndPath()
    {

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
