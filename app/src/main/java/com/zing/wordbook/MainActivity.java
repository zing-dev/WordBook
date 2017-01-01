package com.zing.wordbook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zing.wordbook.dao.WordsOpenHelper;
import com.zing.wordbook.domain.Words;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_add_word = null;
    private ListView lv_words = null;
    private List<Words> words = null;
    private WordAdapter adapter = null;
    WordsOpenHelper wordsOpenHelper = new WordsOpenHelper(MainActivity.this,null,null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btn_add_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddWordActivity.class));
            }
        });
        words = new ArrayList<>();

        SQLiteDatabase readableDatabase = wordsOpenHelper.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery("SELECT * FROM words",null);
        int i = 0;
        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("wordsname"));
            String desc = cursor.getString(cursor.getColumnIndex("wordsdesc"));
            words.add(i++, new Words(name ,desc));
        }
        cursor.close();
        adapter = new WordAdapter();
        lv_words.setAdapter(adapter);
        lv_words.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_word_list = (TextView) view.findViewById(R.id.tv_word_list);
                TextView tv_desc_list = (TextView) view.findViewById(R.id.tv_desc_list);
//                Toast.makeText(MainActivity.this,"i:"+i+" l:"+l+tv_word_list.getText(),Toast.LENGTH_LONG).show();
                String word = tv_word_list.getText().toString();
                SQLiteDatabase writableDatabase = wordsOpenHelper.getWritableDatabase();
                writableDatabase.delete("words","wordsname=?",new String[]{word});
                Toast.makeText(MainActivity.this,"单词"+word+"删除",Toast.LENGTH_LONG).show();
                return true;
            }
        });
    }

    private void init() {
        lv_words = (ListView) findViewById(R.id.lv_words);
        btn_add_word = (Button) findViewById(R.id.btn_add_word);
    }
    class  WordAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return words.size();
        }

        @Override
        public Object getItem(int position) {
            return words.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        //返回带数据当前行的Item视图对象
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //1. 如果convertView是null, 加载item的布局文件
            if(convertView==null) {
                Log.e("TAG", "getView() load layout");
                convertView = View.inflate(MainActivity.this, R.layout.words_list, null);
            }
            //2. 得到当前行数据对象
            Words word = words.get(position);
            //3. 得到当前行需要更新的子View对象
            TextView tv_word_list = (TextView) convertView.findViewById(R.id.tv_word_list);
            TextView tv_desc_list = (TextView) convertView.findViewById(R.id.tv_desc_list);
            //4. 给视图设置数据
            tv_word_list.setText(word.getWordName());
            tv_desc_list.setText(word.getWordDesc());
            //返回convertView
            return convertView;
        }

    }
}
