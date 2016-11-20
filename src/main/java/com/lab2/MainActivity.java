package com.lab2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    EditText etInput;
    Button bControl;

    int guess;
    boolean gameFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = (TextView)findViewById(R.id.tvInfo);
        etInput = (EditText)findViewById(R.id.eInput);
        bControl = (Button)findViewById(R.id.bControl);
        guess = (int)(Math.random()*100);
        gameFinished = false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public void onClick(View v) {

        if (!gameFinished){


            try {

                int inp = Integer.parseInt(etInput.getText().toString());
                if (inp<0 || inp>100)
                    Toast.makeText(getBaseContext(), R.string.warning, Toast.LENGTH_LONG).show();
                if (inp > guess)
                    tvInfo.setText(getResources().getString(R.string.ahead));
                if (inp < guess)
                    tvInfo.setText(getResources().getString(R.string.behind));
                if (inp == guess) {
                    tvInfo.setText(getResources().getString(R.string.hit));
                    bControl.setText(getResources().getString(R.string.play_more));
                    gameFinished = true;
                }

            }
            catch (Exception e) {
                Toast.makeText(getBaseContext(), "Неправильный ввод", Toast.LENGTH_LONG).show();
            }
        }

        else
        {
            guess = (int)(Math.random()*100);
            bControl.setText(getResources().getString(R.string.input_value));
            tvInfo.setText(getResources().getString(R.string.try_to_guess));
            gameFinished = false;
        }
        etInput.setText("");
    }

    public void aboutClick(MenuItem item) {
        new AboutDialog().show(getSupportFragmentManager(), "Dialog");

    }

    public void exitClick(MenuItem item) {
        System.exit(0);
    }

    public static class AboutDialog extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.Item1)
                    .setMessage(R.string.About)
                    .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick (DialogInterface dialog,int which){

                        }
                    });
            return builder.create();
        }
    }
}

