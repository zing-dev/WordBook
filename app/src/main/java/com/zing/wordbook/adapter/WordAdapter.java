package com.zing.wordbook.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zing.wordbook.R;
import com.zing.wordbook.domain.Word;

import java.util.List;

/**
 * Created by zhang on 2017/6/25.
 *
 */

public class WordAdapter extends BaseAdapter {

    private List<Word> word= null;
    private Context context= null;

    public WordAdapter(Context context,List<Word> word){
        this.word = word;
        this.context = context;
    }
    @Override
    public int getCount() {
        return word.size();
    }

    @Override
    public Object getItem(int i) {
        return word.get(i);
    }

    @Override
    public long getItemId(int i) {
        return word.get(i).getWordId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        //1. 如果convertView是null, 加载item的布局文件
        if (view == null) {
            Log.e("TAG", "getView() load layout");
            view = View.inflate(this.context, R.layout.words_list, null);
        }
        //2. 得到当前行数据对象
        Word mWord = word.get(i);
        //3. 得到当前行需要更新的子View对象
        TextView tv_word_list = (TextView) view.findViewById(R.id.tv_word_list);
        TextView tv_desc_list = (TextView) view.findViewById(R.id.tv_desc_list);
        //4. 给视图设置数据
        tv_word_list.setText((i + 1) + " - " + mWord.getWordName());
        tv_desc_list.setText(mWord.getWordDesc());
        //返回convertView
        return view;
    }
}
