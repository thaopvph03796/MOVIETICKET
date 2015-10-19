package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotplays.dotcashs.R;

import java.util.ArrayList;

import model.ModelDoiquaChild;
import model.ModelDoiquaGroup;

/**
 * Created by Le on 10/13/2015.
 */
public class AdapterDoiqua extends BaseExpandableListAdapter {
    private Context c;
    private String[] group;
    private ArrayList<ModelDoiquaChild> children;
    private ImageView imageView;
    private TextView name, money;
    private Animation animation = new AnimationUtils().loadAnimation(c, android.R.anim.slide_in_left);

    public AdapterDoiqua(Context c, String[] group, ArrayList<ModelDoiquaChild> children) {
        this.c = c;
        this.group = group;
        this.children = children;
    }

    @Override
    public int getGroupCount() {
        return 3;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.activity_doiqua_group_item, null);
        }
        imageView = (ImageView) view.findViewById(R.id.img_doiqua_group_image);
        name = (TextView) view.findViewById(R.id.txt_doiqua_group_name);
        money = (TextView) view.findViewById(R.id.txt_doiqua_group_information);
        if (groupPosition == 0) {
            imageView.setImageResource(R.mipmap.viettel_logo);
        } else if (groupPosition == 1) {
            imageView.setImageResource(R.mipmap.vinaphone_logo);
        } else if (groupPosition == 2) {
            imageView.setImageResource(R.mipmap.mobifone_logo);
        }
        name.setText(group[groupPosition]);
        money.setText(group[groupPosition]);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.activity_doiqua_child_item, null);
        }
        imageView = (ImageView) view.findViewById(R.id.img_doiqua_child_item);
        name = (TextView) view.findViewById(R.id.txt_doiqua_child_name);
        money = (TextView) view.findViewById(R.id.txt_doiqua_child_money);
        if (groupPosition == 0) {
            imageView.setImageResource(R.mipmap.viettel_logo);
        } else if (groupPosition == 1) {
            imageView.setImageResource(R.mipmap.vinaphone_logo);
        } else {
            imageView.setImageResource(R.mipmap.mobifone_logo);
        }
        name.setText(children.get(childPosition).name);
        money.setText(children.get(childPosition).money);
        view.startAnimation(animation);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
