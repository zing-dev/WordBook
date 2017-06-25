package com.zing.wordbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.zing.wordbook.service.WordsService;

import java.util.List;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private Button btn_add_word = null;
    private ListView lv_words = null;
    private List<Words> words = null;
    WordsOpenHelper wordsOpenHelper = new WordsOpenHelper(MainActivity.this, null, null);

    private AlertDialog.Builder builder = null;
    private AlertDialog alert = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getAllWords();
        btn_add_word.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddWordActivity.class));
            }
        });

        lv_words.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tv_word_list = (TextView) view.findViewById(R.id.tv_word_list);
//                TextView tv_desc_list = (TextView) view.findViewById(R.id.tv_desc_list);
                final String word = tv_word_list.getText().toString();
                builder = new AlertDialog.Builder(MainActivity.this);
                alert = builder
                        .setTitle("系统提示")
                        .setMessage("确定删除单词: " + word + "?")
                        .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        }).
                                setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        SQLiteDatabase writableDatabase = wordsOpenHelper.getWritableDatabase();
                                        writableDatabase.delete("words", "wordsname=?", new String[]{word});
                                        Toast.makeText(MainActivity.this, "单词" + word + "删除", Toast.LENGTH_LONG).show();
                                        getAllWords();
                                    }
                                })
                        .show();
                alert.create();
                return true;
            }
        });
    }

    private void getAllWords() {
        WordsService wordsService = new WordsService(this);
        words = wordsService.getAllWords();
        WordAdapter adapter = new WordAdapter();
        lv_words.setAdapter(adapter);
    }

    private void init() {
        lv_words = (ListView) findViewById(R.id.lv_words);
        btn_add_word = (Button) findViewById(R.id.btn_add_word);
    }

    private class WordAdapter extends BaseAdapter {

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
            return position;
        }

        //返回带数据当前行的Item视图对象
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //1. 如果convertView是null, 加载item的布局文件
            if (convertView == null) {
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

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "onResume() load layout");
        getAllWords();
    }
}
