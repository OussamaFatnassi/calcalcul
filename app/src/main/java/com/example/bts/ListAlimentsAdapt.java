package com.example.bts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class ListAlimentsAdapt extends BaseAdapter {
    private List<Aliment> aliments;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListAlimentsAdapt(Context cont, List<Aliment> listAlim){
        context = cont;
        aliments = listAlim;
        layoutInflater = layoutInflater.from(context);
    }

    @Override
    public int getCount() {return aliments.size();}

    @Override
    public Object getItem(int i) { return aliments.get(i);}

    @Override
    public long getItemId(int i) {return i;}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.second_list, null);
            holder = new ViewHolder();
            holder.nom = (TextView) convertView.findViewById(R.id.nom);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.cal = (TextView) convertView.findViewById(R.id.cal);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Aliment aliment = this.aliments.get(position);
        holder.nom.setText(aliment.getNom());
        holder.description.setText(aliment.getDescription());
        holder.cal.setText(String.valueOf(aliment.getCalories()));
        return convertView;
    }
    static class ViewHolder{
        TextView nom;
        TextView description;
        TextView cal;
    }
}
