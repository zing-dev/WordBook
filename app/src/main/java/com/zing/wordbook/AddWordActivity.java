package com.zing.wordbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zing.wordbook.domain.Word;
import com.zing.wordbook.service.WordService;

public class AddWordActivity extends AppCompatActivity {

    private Button btn_add_submit;
    private EditText et_add_word;
    private EditText et_add_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
        init();

        btn_add_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String word = et_add_word.getText().toString();
                String desc = et_add_desc.getText().toString();
                if ("".equals(word) || "".equals(desc)) {
                    Toast.makeText(AddWordActivity.this, "请输入完整的单词信息", Toast.LENGTH_LONG).show();
                } else {
                    WordService wordsService = new WordService(AddWordActivity.this);
                    wordsService.addWord(new Word(word, desc));
                    Toast.makeText(AddWordActivity.this, "添加成功", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void init() {
        btn_add_submit = (Button) findViewById(R.id.btn_add_submit);
        et_add_word = (EditText) findViewById(R.id.et_add_word);
        et_add_desc = (EditText) findViewById(R.id.et_add_desc);
    }
}
