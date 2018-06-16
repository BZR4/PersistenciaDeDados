package com.esdras.databasecesario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SQLiteAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> id;
    private ArrayList<String> word;
    private ArrayList<String> definition;

    public SQLiteAdapter(Context context, ArrayList<String> id, ArrayList<String> word, ArrayList<String> definition) {
        this.mContext = context;
        this.id = id;
        this.word = word;
        this.definition = definition;
    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int pos, View child, final ViewGroup parent) {
        Holder mHolder;
        LayoutInflater layoutInflater;
        if (child == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.cell_layout, null);
            mHolder = new Holder();
            mHolder.txt_id = (TextView) child.findViewById(R.id.textViewId);
            mHolder.txt_word = (TextView) child.findViewById(R.id.textViewWord);
            mHolder.txt_definition = (TextView) child.findViewById(R.id.textViewDefinition);
            child.setTag(mHolder);
        } else {
            mHolder = (Holder) child.getTag();
        }
        mHolder.txt_id.setText(id.get(pos));
        mHolder.txt_word.setText(word.get(pos));
        mHolder.txt_definition.setText(definition.get(pos));
        return child;
    }

    public class Holder {
        TextView txt_id;
        TextView txt_word;
        TextView txt_definition;
    }
}


