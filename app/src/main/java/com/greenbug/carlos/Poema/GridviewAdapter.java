package com.greenbug.carlos.Poema;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.greenbug.carlos.centralapp.R;
import com.greenbug.carlos.data.Poema;
import com.greenbug.carlos.red.UtilRed;
import com.squareup.picasso.Picasso;

public class GridviewAdapter extends BaseAdapter
{
    private ArrayList<Poema> poemas;
    private Activity activity;

    public GridviewAdapter(Activity activity,ArrayList<Poema> poemas) {
        super();
        this.poemas = poemas;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return poemas.size();
    }

    @Override
    public String getItem(int position) {
        return poemas.get(position).getTitulo();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder
    {
        public ImageView imgViewFlag;
        public TextView txtViewTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();

        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.gridview_row, null);

            view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView1);
            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView1);

            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }

        view.txtViewTitle.setText(poemas.get(position).getTitulo());
        int p = poemas.get(position).getIdPoema();
        String url = UtilRed.dirIp+"centralApp/public/imagenes/poemas/"+p+".jpeg";

        Picasso.with(activity).load(url)//.resize(50, 50)
                .centerCrop().fit().placeholder(R.drawable.sample_0).into(view.imgViewFlag);

        return convertView;
    }
}