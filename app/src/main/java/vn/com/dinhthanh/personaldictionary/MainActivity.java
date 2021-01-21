package vn.com.dinhthanh.personaldictionary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt_Add;
    Button bt_ViewListWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_Add = findViewById(R.id.bt_add_word_main);
        bt_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityAddWord.class);
                startActivity(intent);
            }
        });

        bt_ViewListWord = findViewById(R.id.bt_view_list_words);
        bt_ViewListWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityViewWords.class);
                startActivity(intent);
            }
        });
    }
}