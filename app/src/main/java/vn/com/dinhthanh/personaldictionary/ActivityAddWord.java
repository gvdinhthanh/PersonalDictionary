package vn.com.dinhthanh.personaldictionary;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityAddWord extends AppCompatActivity {

    private Button bt_AddWord;
    private ArrayList<WordsClass> wordsClass;
    private WordsDBHelper db;
    private WordAdapter adapter;

    private EditText edt_add_newWords;
    private EditText edt_add_newDecription;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("THÊM TỪ MỚI");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_word);

        bt_AddWord = findViewById(R.id.btAddWord);
        edt_add_newWords = findViewById(R.id.editTextAddWord);
        edt_add_newDecription = findViewById(R.id.editTextAddDescription);

        wordsClass = new ArrayList<WordsClass>();
        adapter = new WordAdapter(ActivityAddWord.this, wordsClass);

        db = new WordsDBHelper(this);

        bt_AddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newWord = edt_add_newWords.getText().toString().trim();
                String newDescription = edt_add_newDecription.getText().toString().trim();

                if (newWord.isEmpty()) {
                    Toast.makeText(ActivityAddWord.this, "Vui lòng nhập từ mới!", Toast.LENGTH_SHORT).show();
                } else if (newDescription.isEmpty()) {
                    Toast.makeText(ActivityAddWord.this, "Vui lòng nhập nghĩa của từ mới!", Toast.LENGTH_SHORT).show();
                } else {
                    WordsClass newWordClass = new WordsClass(newWord, newDescription);
                    if (db.insertWord(newWordClass)) {
                        wordsClass.add(newWordClass);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(ActivityAddWord.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        edt_add_newWords.setText("");
                        edt_add_newDecription.setText("");
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }


}
