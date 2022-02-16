package algonquin.cst2335.findtheanimal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech mTTs;
    private Button mButtonSpeak;
    private EditText mEditText;
    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          ImageView blkgot =  findViewById(R.id.imageView);
         ImageView goat =  findViewById(R.id.imageView2);
          blkgot.setOnClickListener(clk->{
              goat.setVisibility(View.INVISIBLE);

          });
        mButtonSpeak = findViewById(R.id.button);
        mButtonSpeak.setOnClickListener(click->{
            speak();
        });
        mEditText = findViewById(R.id.edtTxt);
        mTextView = findViewById(R.id.txtvi);
       mTTs = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
          @Override
          public void onInit(int status) {
              if (status == TextToSpeech.SUCCESS){
                  int result = mTTs.setLanguage(Locale.CANADA);
                  if (result == TextToSpeech.LANG_MISSING_DATA || result  == TextToSpeech.LANG_NOT_SUPPORTED){
                      Log.e("VV","Language not supported");

                  }else{
                      mButtonSpeak.setEnabled(true);

                  }}else{
                      Log.e("VV","Initialization failed");
                  }




              }
          });


      }
    private void speak(){
        String text = mTextView.getText().toString();
        mTTs.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }
    @Override
    protected void onDestroy(){
        if(mTTs != null){
            mTTs.stop();
            mTTs.shutdown();
        }
        super.onDestroy();
    }
   }
