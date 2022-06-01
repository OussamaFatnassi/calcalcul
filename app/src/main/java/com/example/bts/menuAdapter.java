package com.example.bts;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class menuAdapter extends BaseAdapter {
    private List<menuAlim> aliments;
    private LayoutInflater layoutInflater;
    private Context context;

    public menuAdapter(Context cont, List<menuAlim> listAlim){
        context = cont;
        aliments = listAlim;
        layoutInflater = layoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return aliments.size();}

    @Override
    public Object getItem(int i) { return aliments.get(i);}

    @Override
    public long getItemId(int i) {return i;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.details_menu, null);
            holder = new ViewHolder();
            holder.nom = (TextView) convertView.findViewById(R.id.nom);
            holder.poid = (TextView) convertView.findViewById(R.id.poid);
            holder.cal = (TextView) convertView.findViewById(R.id.cal);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        menuAlim aliment = this.aliments.get(position);

        holder.nom.setText(aliment.getNom());
        holder.poid.setText(aliment.getPoid());
        holder.cal.setText(aliment.getCal());

        return convertView;
    }
    static class ViewHolder{
        TextView nom;
        TextView poid;
        TextView cal;
    }
}