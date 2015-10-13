package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotplays.dotcashs.R;

import java.util.ArrayList;
import java.util.List;

import model.ModelDoiqua;

/**
 * Created by Le on 10/13/2015.
 */
public class AdapterDoiqua extends ArrayAdapter<ModelDoiqua> {
    private Context c;
    private int layout;
    private ArrayList<ModelDoiqua> arr;
    private LayoutInflater inflater;
    private ImageView image;
    private TextView name, information;

    public AdapterDoiqua(Context context, int resource, ArrayList<ModelDoiqua> objects) {
        super(context, resource, objects);
        this.c = context;
        this.layout = resource;
        this.arr = objects;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            view = inflater.from(c).inflate(layout, null);
        }
        image = (ImageView) view.findViewById(R.id.img_doiqua_image);
        name = (TextView) view.findViewById(R.id.txt_doiqua_name);
        information = (TextView) view.findViewById(R.id.txt_doiqua_information);
        image.setImageResource(arr.get(position).image);
        name.setText(arr.get(position).name);
        information.setText(arr.get(position).name);
        return view;
    }
}
