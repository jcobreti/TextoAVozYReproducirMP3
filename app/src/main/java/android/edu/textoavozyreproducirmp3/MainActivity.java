package android.edu.textoavozyreproducirmp3;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE=200;
private MediaPlayer mp1;
private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView);

    }
    public void mp3(View view) {
        //se crea un directorio RAW para poner los MP3
        mp1 = MediaPlayer.create(this, R.raw.heyjude);
        mp1.setLooping(false);
        mp1.setVolume(100, 100);
        mp1.start();
    }
    public void txt(View view) {
        String textoAPreguntar;
        textoAPreguntar=tv.getText().toString();
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1000);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"es");
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,textoAPreguntar);
        //startActivity(intent);
        startActivityForResult(intent,REQUEST_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String resultado;
        ArrayList<String> texto;

        switch (requestCode) {
            case REQUEST_CODE: {
                if (resultCode == RESULT_OK && null != data) {

                    texto = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                   //En resultado esta lo que hemos grabado
                    resultado= texto.get(0);
                 Toast.makeText(this,"Resultado"+resultado,Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
    }
 }
