package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner conversion;
    private EditText number;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        conversion = findViewById(R.id.spinner_conversion);
        number = findViewById(R.id.editText_input);
        result = findViewById(R.id.textView_result);
        if(message()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("score", result.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result = findViewById(R.id.textView_result); // Initialize the result TextView
        String score = savedInstanceState.getString("score", "");
        result.setText(score);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.converter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.activity_settings:
                Intent in=new Intent(this, settingsActivity.class);
                startActivity(in);
                return true;
            case R.id.activity_info:
                Intent i =new Intent(this, InfoActivity.class);
                startActivity(i);
                 return true;
            case R.id.activity_exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public void onClickConvertButton(View view) {
        int method = (int) conversion.getSelectedItemId();
        String input = number.getText().toString();
        double value = Double.parseDouble(input);
        String resultText = "";

        switch (method) {
            case 0:
            default:
                double kilometers = value / 1000;
                resultText = String.format("%.2f km", kilometers);

                break;
            case 1:
                double meters = value * 1000;
                resultText = String.format("%.2f m", meters);

                break;
            case 2:
                double metersFromCm = value / 100;
                resultText = String.format("%.2f m", metersFromCm);


                break;
            case 3:
                double centimeters = value * 100;
                resultText = String.format("%.2f cm", centimeters);
                break;
            case 4:
                double centimetersFromKm = value * 100000;
                resultText = String.format("%.2f cm", centimetersFromKm);

                break;
            case 5:
                double kilometersFromCm = value / 100000;
                resultText = String.format("%.2f km", kilometersFromCm);

                break;
        }
        if (TextUtils.isEmpty(resultText)) {
            showStatement();
        } else {
            result.setText(resultText);
        }

    }

    private String convert(String number, int inBase, int outBase) {
        try {
            return Integer.toString(Integer.parseInt(number, inBase), outBase);
        } catch (NumberFormatException e) {
            return "";
        }
    }

    private void showStatement() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(500);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.statementTitle);
        builder.setMessage(R.string.statementMessage);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean message(){
        Boolean switchPref = getSharedPreferences("Set", MODE_PRIVATE).getBoolean("theme", false);
        return switchPref;
    }
}