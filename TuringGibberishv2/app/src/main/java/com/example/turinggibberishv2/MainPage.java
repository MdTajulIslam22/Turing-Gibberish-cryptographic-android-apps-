package com.example.turinggibberishv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class MainPage extends AppCompatActivity {
    EditText inputView;
    EditText keyView;
    TextView outputView;
    ImageButton button;
    Button copyButton;
    Button saveTxt;
    SwitchCompat switche;
    String answer;
    String fileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        inputView = (EditText) findViewById(R.id.original_text);
        keyView = (EditText) findViewById(R.id.cipher_key);
        outputView = (TextView) findViewById(R.id.cipher_text);
        button = (ImageButton) findViewById(R.id.process_button);
        switche = (SwitchCompat) findViewById(R.id.swits);
        copyButton = (Button) findViewById(R.id.copied);
        saveTxt = (Button) findViewById(R.id.save_as_txt);


        switche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switche.isChecked()){
                    answer = originalMaker();
                }else{
                    answer  = cipherMaker();
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputView.setText(answer);
            }
        });

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileContent = outputView.getText().toString();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData copyData = ClipData.newPlainText("textView", fileContent);
                clipboard.setPrimaryClip(copyData);

                Toast.makeText(MainPage.this, "Copied", Toast.LENGTH_SHORT).show();
            }
        });

        saveTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAndSave();
            }
        });
    }

    public String originalMaker(){
        char ch;
        String decryptedMassage = "";
        String massage = inputView.getText().toString();
        int key = Integer.parseInt(keyView.getText().toString());

        for (int i = 0; i<massage.length(); i++){
            ch = massage.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                ch = (char) (ch - key);
                if(ch < 'a'){
                    ch = (char)(ch + 'z' - 'a' + 1);
                }
                decryptedMassage += ch;
            }else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch - key);

                if(ch < 'A'){
                    ch = (char)(ch + 'Z' - 'A' + 1);
                }

                decryptedMassage += ch;
            }else{
                decryptedMassage += ch;
            }
        }

        return decryptedMassage;
    }

    public String cipherMaker(){
        char ch;
        String encryptedMassage = "";
        String massage = inputView.getText().toString();
        int key = Integer.parseInt(keyView.getText().toString());

        for (int i = 0; i< massage.length(); i++){
            ch = massage.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch + key);

                if(ch > 'z'){
                    ch = (char)(ch - 'z' + 'a' - 1);
                }

                encryptedMassage += ch;
            }else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch + key);

                if(ch > 'Z'){
                    ch = (char)(ch - 'Z' + 'A' - 1);
                }

                encryptedMassage += ch;
            }else{
                encryptedMassage += ch;
            }
        }

        return encryptedMassage;
    }

    public void createAndSave(){
        fileContent = outputView.getText().toString();

        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TITLE, "encrypted");

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){

                OutputStream outputStream = null;
                try {
                    Uri uri = data.getData();
                    outputStream = getContentResolver().openOutputStream(uri);
                    outputStream.write(fileContent.getBytes());
                    outputStream.close();

                    Toast.makeText(this, "file saved" + getFilesDir() , Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "faield to save file", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "File not saved", Toast.LENGTH_SHORT).show();
            }
        }
    }
}