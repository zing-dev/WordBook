package com.zing.wordbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zing.wordbook.domain.Words;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_words = null;
    private List<Words> words = null;
    private WordAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        words = new ArrayList<>();
        for (int i = 0; i < 50; i++){
            words.add(i, new Words("int" +i ,"INT"+ i + i));
        }
        adapter = new WordAdapter();
        lv_words.setAdapter(adapter);
    }

    private void init() {
        lv_words = (ListView) findViewById(R.id.lv_words);
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
