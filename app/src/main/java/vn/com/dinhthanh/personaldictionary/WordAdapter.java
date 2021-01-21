package vn.com.dinhthanh.personaldictionary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<WordsClass> wordsClass;
    public WordAdapter(Context context, ArrayList<WordsClass> wordsClass) {
        this.context = context;
        this.wordsClass = wordsClass;
    }

    @Override
    public int getCount() {
        return wordsClass.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_list_view, null);
        }

        TextView txtWord = convertView.findViewById(R.id.textViewWord);
        TextView txtDescription = convertView.findViewById(R.id.textViewDescription);
        Button btEdit = convertView.findViewById(R.id.btEdit);
        Button btDelete = convertView.findViewById(R.id.btDelete);
        txtWord.setText(wordsClass.get(position).getWord());
        txtDescription.setText(wordsClass.get(position).getDescription());

        WordsClass selectWord = wordsClass.get(position);

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ActivityEditWord.class);
                i.putExtra("id", selectWord.getId());
                i.putExtra("word", selectWord.getWord());
                i.putExtra("description", selectWord.getDescription());
                ((Activity) context).startActivityForResult(i, 2409);
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordsDBHelper db = new WordsDBHelper(context);
                Boolean result = db.deleteWord(selectWord.getId());
                if (result == true) {
                    Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    delete(position);
                } else {
                    Toast.makeText(context, "Thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }

    public void delete(int position){
        wordsClass.remove(position);
        this.notifyDataSetChanged();
    }
}
