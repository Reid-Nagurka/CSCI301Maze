package reidnagurka.cs301.cs.wm.edu.amazebyreidnagurka.amazebyreidnagurka.ui;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import reidnagurka.cs301.cs.wm.edu.amazebyreidnagurka.R;

public class AMazeActivity extends AppCompatActivity {
    private Button startButton;
    private RelativeLayout layout;
    private SeekBar skillSeekBar;
    private TextView selectedSkill;
    private Spinner mazeBuilderSpinner;
    private Spinner driverSpinner;
    private boolean manual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amaze);

        setUpComponents();
        setSkillOnChangeListener();
        setUpSpinners();
    }

    /**
     * handles the Seek Bar for selecting skill level
     */
    public void setSkillOnChangeListener() {
        skillSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                selectedSkill.setText(Integer.toString(progress));
                Log.v("skill change", ""+progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    /**
     * sets up the spinners for selecting driving and maze algorithms
     */
    public void setUpSpinners()
    {
        String[] builderAlgs = getResources().getStringArray(R.array.builder);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, builderAlgs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mazeBuilderSpinner.setAdapter(adapter);

        String[] driverAlgs = getResources().getStringArray(R.array.driver);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, driverAlgs);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driverSpinner.setAdapter(adapter2);
    }

    /**
     * handles when the Explore button is clicked.
     * @param view
     */
    public void startButtonClickHandler(View view) {
        collectInfo();
    }

    /**
     * not yet implemented, will generate the previous maze generated. If there is no previous maze, tell user to make a new maze
     * @param view
     */
    public void revisitButtonHandler(View view)
    {
        //TO DO:
        Log.v("To Do: ", "implement revisit feature");
        Snackbar snackbar = Snackbar
                .make(layout, "Will be implemented in P7", Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /**
     * initalizes all components. Used for clearer code. Checks whether the driver is manual and displays the correct gui elements
     * accordingly.
     */
    private void setUpComponents()
    {
        layout = (RelativeLayout) findViewById(R.id.layout);
        startButton = (Button) findViewById(R.id.startButton);
        skillSeekBar = (SeekBar) findViewById(R.id.skillSeekBar);
        selectedSkill = (TextView) findViewById(R.id.selectedSkill);
        mazeBuilderSpinner = (Spinner) findViewById(R.id.mazeBuilderSpinner);
        driverSpinner = (Spinner) findViewById(R.id.driverSpinner);

        skillSeekBar.setMax(9);
    }

    /**
     * called when the Explore button is clicked. Collects selections for skill, maze and driving algorithms. Sends an
     * intent with these properties and a boolean if Manual driving is selected for easier implementation in GeneratingActivity
     */
    public void collectInfo()
    {
        String builder = mazeBuilderSpinner.getSelectedItem().toString();
        String driver = driverSpinner.getSelectedItem().toString();
        String skillLevel = selectedSkill.getText().toString();

        if(driver.equals("Manual"))
            manual = true;
        else
            manual = false;
        Log.v("bldrAlg: " , ""+builder );
        Log.v("driverAlg: " , ""+driver);
        Log.v("skillLevel: " , ""+skillLevel);
//        Snackbar snackbar = Snackbar
//                .make(layout, "bldrAlg: " + builder + "\n" + "driverAlg: " + driver+ "     skillLevel: " + skillLevel, Snackbar.LENGTH_LONG);
//        snackbar.show();
        Intent intent = new Intent(this, GeneratingActivity.class);
        intent.putExtra("skillLevel", Integer.parseInt(skillLevel));
        intent.putExtra("bldrAlg", builder);
        intent.putExtra("driverAlg", driver);
        intent.putExtra("manualMode", manual);
        startActivity(intent);
    }


}
