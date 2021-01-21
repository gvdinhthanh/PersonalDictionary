package vn.com.dinhthanh.personaldictionary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityEditWord extends AppCompatActivity {

    private EditText edtUpdateWord;
    private EditText edtUpdateDescription;
    private Button btnUpdate;
    private WordsDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("SỬA TỪ VỰNG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_edit_word);

        db = new WordsDBHelper(this);

        edtUpdateWord = findViewById(R.id.editTextUpdateWord);
        edtUpdateDescription = findViewById(R.id.editTextUpdateDescription);
        btnUpdate = findViewById(R.id.btUpdateWord);

        Intent myIntent = getIntent();
        int id = myIntent.getIntExtra("id", -1);
        String word = myIntent.getStringExtra("word");
        String description = myIntent.getStringExtra("description");
        edtUpdateWord.setText(word);
        edtUpdateDescription.setText(description);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newWord = edtUpdateWord.getText().toString().trim();
                String newDescription = edtUpdateDescription.getText().toString().trim();
                Boolean result = db.updateWord(new WordsClass(id, newWord, newDescription));
                if (result == true) {
                    Toast.makeText(ActivityEditWord.this, "Đã cập nhật thành công", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ActivityEditWord.this, "Thất bại, vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}