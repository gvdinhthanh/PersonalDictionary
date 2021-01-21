package vn.com.dinhthanh.personaldictionary;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ActivityViewWords extends AppCompatActivity {

    private ListView listViewWords;
    private Button updateButton;
    private Button deleteButton;
    private int idKhoa;

    private ArrayList<WordsClass> wordsClass;
    private WordsDBHelper db;
    private WordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setTitle("DANH SÁCH TỪ VỰNG");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_view_list_words);

        listViewWords = findViewById(R.id.listViewWords);
        wordsClass = new ArrayList<WordsClass>();
        loadWords();
        adapter = new WordAdapter(ActivityViewWords.this, wordsClass);
        listViewWords.setAdapter(adapter);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2409 && resultCode == RESULT_OK) {
            loadWords();
            adapter.notifyDataSetChanged();
        }
    }

    private void loadWords() {
        wordsClass.clear();
        db = new WordsDBHelper(this);
        Cursor cursor = db.getListWords();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                wordsClass.add(new WordsClass(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)));
            }
        }
    }
}