package com.postjma.postjma.inha_englishdictionary;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;

public class InhaDictionaryActivity extends AppCompatActivity {
    private EditText mWord = null, mResults = null;
    private boolean mFirst = true;
    private HashMap<String, String> mInha2Results = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInha2Results = new HashMap<String, String>();
        // Stone is both the Standard and Glinda's variant.
        mInha2Results.put("moavian", "Inha: Moavian\nEnglish: Mistress\nVariant: Stone\n");
        mInha2Results.put("iivurat", "Inha: Iivurat\nEnglish: North\nVariant: Stone\n");
        mInha2Results.put("gernis", "Inha: Gernis\nEnglish: Sleeping\nVariant: Stone\n");
        mInha2Results.put("poelnun", "Inha: Poelnun\nEnglish: Girls\nVariant: Stone\n");
        mInha2Results.put("geraias", "Inha: Geraias\nEnglish: Sleeping\nVariant: Water\n");
        mInha2Results.put("poelua", "Inha: Poelua\nEnglish: Girls\nVariant: Water\n");
        mInha2Results.put("gerhis", "Inha: Gerhis\nEnglish: Sleeping\nVariant: Wind\n");
        mInha2Results.put("poelhu", "Inha: Poelhu\nEnglish: Girls\nVariant: Wind\n");
        mInha2Results.put("geriis", "Inha: Geriis\nEnglish: Sleeping\nVariant: Fire\n");
        mInha2Results.put("poeliu", "Inha: Poeliu\nEnglish: Girls\nVariant: Fire\n");

        EditText et = (EditText)findViewById(R.id.editText);
        EditText etR = (EditText)findViewById(R.id.editText3);

        mWord = et;
        mResults = etR;

        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                if (hasFocus && mFirst)
                {
                    mFirst = false;
                    mWord.setText("");
                }
            }
        });

        et.setOnKeyListener(new TextView.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                /*KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
                if ((keyEvent.getAction() == KeyEvent.ACTION_UP) &&
                        keyEvent.getKeyData(keyData))
                {
                    if (keyData.number == '\n')
                    {
                        String text = mWord.getText();
                    }
                }*/

                /*if (mFirst) {
                    mFirst = false;
                    mWord.setText("");
                    return false;
                }*/

                String text = mWord.getText().toString().trim().toLowerCase();

                if (mInha2Results.containsKey(text)) {
                    mResults.setText(mInha2Results.get(text));
                }
                else {
                    mResults.setText("Not found: " + text);
                }

                return false;
            }
        });

        etR.setOnKeyListener(new TextView.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                return true;
            }
        });

        etR.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus)
            {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(mResults.getWindowToken(), 0);
            }
        });
    }
}
