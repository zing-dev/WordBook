package com.zing.wordbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.zing.wordbook.domain.Word;
import com.zing.wordbook.service.WordService;

import java.util.List;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    private TextView tv_count_word = null;
    private Button btn_add_word = null;
    private ListView lv_word = null;
    private List<Word> word = null;

    private AlertDialog.Builder builder = null;
    private AlertDialog alert = null;

    WordService wordService = new WordService(this);


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

        lv_word.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Word mWord = word.get(i);
                builder = new AlertDialog.Builder(MainActivity.this);
                alert = builder
                        .setTitle("系统提示")
                        .setMessage("确定删除单词: " + mWord.getWordName() + "?")
                        .setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(MainActivity.this, "你点击了取消按钮~", Toast.LENGTH_SHORT).show();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                wordService.deleteWord(mWord);
                                Toast.makeText(MainActivity.this, "单词" + mWord.getWordName() + "删除", Toast.LENGTH_LONG).show();
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
        word = wordService.getAllWords();
//        int count = wordService.count();
//        tv_count_word.setText("总共 " + count + "个单词");
        tv_count_word.setText("总共 " + word.size() + "个单词");
        WordAdapter adapter = new WordAdapter();
        lv_word.setAdapter(adapter);
    }

    private void init() {
        lv_word = (ListView) findViewById(R.id.lv_words);
        btn_add_word = (Button) findViewById(R.id.btn_add_word);
        tv_count_word = (TextView) findViewById(R.id.word_count);
    }

    private class WordAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return word.size();
        }

        @Override
        public Object getItem(int position) {
            return word.get(position);
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
            Word mWord = word.get(position);
            //3. 得到当前行需要更新的子View对象
            TextView tv_word_list = (TextView) convertView.findViewById(R.id.tv_word_list);
            TextView tv_desc_list = (TextView) convertView.findViewById(R.id.tv_desc_list);
            //4. 给视图设置数据
            tv_word_list.setText((position + 1) + " - " + mWord.getWordName());
            tv_desc_list.setText(mWord.getWordDesc());
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
