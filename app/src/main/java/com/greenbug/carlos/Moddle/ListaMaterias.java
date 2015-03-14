package com.greenbug.carlos.Moddle;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.greenbug.carlos.centralapp.R;

import java.util.ArrayList;

public class ListaMaterias extends BaseExpandableListAdapter {

    private ArrayList<Semestre> semestres;
    public LayoutInflater inflater;
    public Activity activity;

    public ListaMaterias(Activity act, ArrayList<Semestre> groups) {
        activity = act;
        this.semestres = groups;
        inflater = act.getLayoutInflater();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }  

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return semestres.get(groupPosition).materias.size();
    }

    @Override
    public int getGroupCount() {
        return semestres.size();
    }
    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final Materia children = semestres.get(groupPosition).materias.get(childPosition);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.materia, null);
        }
        CheckedTextView text = (CheckedTextView) convertView.findViewById(R.id.laptop);
        text.setText(children.nombre);
        text.setChecked(false);

        return convertView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.semestre, null);
        }
        Semestre group = semestres.get(groupPosition);
        TextView item = (TextView) convertView.findViewById(R.id.laptop);
        item.setText(group.nombre);
        return convertView;
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}