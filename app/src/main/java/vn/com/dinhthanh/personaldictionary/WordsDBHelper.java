package vn.com.dinhthanh.personaldictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WordsDBHelper extends SQLiteOpenHelper {
    public WordsDBHelper(Context context) {
        super(context, "dictionary.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE dictionary(id INTEGER PRIMARY KEY AUTOINCREMENT, word TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE dictionary");
    }

    public Boolean insertWord(WordsClass newWord) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("word", newWord.getWord());
        contentValues.put("description", newWord.getDescription());
        long result = db.insert("dictionary",null, contentValues);
        return result != -1;
    }

    public Boolean updateWord(WordsClass newWord) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM dictionary WHERE id=?", new String[] {String.valueOf(newWord.getId())});
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("word", newWord.getWord());
            contentValues.put("description", newWord.getDescription());
            long result = db.update("dictionary", contentValues, "id=?", new String[] {String.valueOf(newWord.getId())});
            return result != -1;
        }
        return false;
    }

    public Boolean deleteWord(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM dictionary WHERE id=?", new String[] {String.valueOf(id)});
        if (cursor.getCount() > 0) {
            long result = db.delete("dictionary", "id=?", new String[] {String.valueOf(id)});
            return result != -1;
        }
        return false;
    }

    public Cursor getListWords() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM dictionary ORDER BY word", null);
    }
}
