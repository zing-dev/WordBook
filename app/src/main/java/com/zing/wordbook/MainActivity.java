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

import com.zing.wordbook.adapter.WordAdapter;
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
        tv_count_word.setText("总共 " + word.size() + "个单词");
        WordAdapter adapter = new WordAdapter(this,word);
        lv_word.setAdapter(adapter);
    }

    private void init() {
        lv_word = (ListView) findViewById(R.id.lv_words);
        btn_add_word = (Button) findViewById(R.id.btn_add_word);
        tv_count_word = (TextView) findViewById(R.id.word_count);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("TAG", "onResume() load layout");
        getAllWords();
    }
}
