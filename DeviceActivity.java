package speechtotext.androidhive.info.texttospeech;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class DeviceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_devices);
        int colonAt;
        Intent myIntent = getIntent();
        ArrayList<String> applianceList = myIntent.getStringArrayListExtra("applianceList");
        ArrayList<String> sensorList = myIntent.getStringArrayListExtra("sensorList");

        TableLayout deviceTable = (TableLayout) findViewById(R.id.device_table);
        deviceTable.setPadding(0,100,0,0);
        TableRow row = new TableRow(this);
        row.setBackgroundResource(R.color.tableRow);
        TextView text1 = new TextView(this);
        text1.setText("Devices");
        text1.setTextSize(18);
        text1.setTypeface(text1.getTypeface(),Typeface.BOLD);
        text1.setMinWidth(400);
        text1.setGravity(Gravity.CENTER);
        row.addView(text1);
        TextView text2 = new TextView(this);
        text2.setText("Status");
        text2.setTextSize(18);
        text2.setTypeface(text1.getTypeface(),Typeface.BOLD);
        text2.setMinWidth(400);
        text2.setGravity(Gravity.CENTER);
        row.addView(text2);
        deviceTable.addView(row);

        TableLayout sensorTable = (TableLayout) findViewById(R.id.sensor_table);
        sensorTable.setPadding(0,0,0,50);
        TableRow rowS = new TableRow(this);
        rowS.setBackgroundResource(R.color.tableRow);
        TextView textS1 = new TextView(this);
        textS1.setText("Sensor");
        textS1.setTextSize(18);
        textS1.setTypeface(text1.getTypeface(),Typeface.BOLD);
        textS1.setMinWidth(400);
        textS1.setGravity(Gravity.CENTER);
        rowS.addView(textS1);
        TextView textS2 = new TextView(this);
        textS2.setText("Value");
        textS2.setTextSize(18);
        textS2.setTypeface(text1.getTypeface(),Typeface.BOLD);
        textS2.setMinWidth(400);
        textS2.setGravity(Gravity.CENTER);
        rowS.addView(textS2);
        sensorTable.addView(rowS);

        Iterator applianceIterator = applianceList.iterator();
        while(applianceIterator.hasNext()){

            String temp = applianceIterator.next().toString();
            colonAt = temp.indexOf(":");

            TableRow tempRow = new TableRow(this);
            TextView tempText1 = new TextView(this);
            tempText1.setText(temp.substring(0,colonAt));
            tempText1.setTextSize(18);
            //tempText1.setGravity(Gravity.CENTER);
            tempRow.addView(tempText1);
            TextView tempText2 = new TextView(this);
            tempText2.setText(temp.substring(colonAt+1));
            tempText2.setTextSize(18);
            tempText2.setGravity(Gravity.RIGHT);
            tempRow.addView(tempText2);
            deviceTable.addView(tempRow);
        }

        Iterator sensorIterator = sensorList.iterator();
        while(sensorIterator.hasNext()){
            String temp = sensorIterator.next().toString();
            colonAt = temp.indexOf(":");
            TableRow tempRow = new TableRow(this);
            TextView tempText1 = new TextView(this);
            tempText1.setText(temp.substring(0,colonAt));
            tempText1.setTextSize(18);
            //tempText1.setGravity(Gravity.CENTER);
            tempRow.addView(tempText1);
            TextView tempText2 = new TextView(this);
            tempText2.setText(temp.substring(colonAt+1));
            tempText2.setTextSize(18);
            tempText2.setGravity(Gravity.RIGHT);
            tempRow.addView(tempText2);
            sensorTable.addView(tempRow);
        }
    }
}