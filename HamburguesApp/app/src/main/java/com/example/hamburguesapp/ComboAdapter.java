package com.example.hamburguesapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ComboAdapter extends BaseAdapter {

    Context context;
    ArrayList<Combo> listaCombos;

    LayoutInflater inflater;

    public ComboAdapter(Context context, ArrayList<Combo> listaCombos) {
        this.context = context;
        this.listaCombos = listaCombos;
    }

    @Override
    public int getCount() {
        return listaCombos.size();
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

        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.grid_item, null);
        }

        ImageView imageView = convertView.findViewById(R.id.grid_image);
        TextView textView = convertView.findViewById(R.id.grid_txt);

        Combo combo = listaCombos.get(position);
        byte[] image = combo.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);

        imageView.setImageBitmap(bitmap);
        textView.setText(combo.getName());

        return convertView;
    }
}
