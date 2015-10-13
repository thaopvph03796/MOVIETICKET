package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotplays.dotcashs.R;

import java.util.ArrayList;

import model.ModelIteamApp;

/**
 * Created by m8 on 10/13/2015.
 */
public class AdapterIteamApp extends ArrayAdapter<ModelIteamApp> {
    private Activity activity;
    private int id;
    private ArrayList<ModelIteamApp> arrItem;
    private LayoutInflater layoutInflater;
    private TextView name, sotien;
    private Button btnCaidat;
    private ImageView imgItem;

    public AdapterIteamApp(Activity activity, int resource, ArrayList<ModelIteamApp> objects) {
        super(activity, resource, objects);
        this.activity = activity;
        this.id = resource;
        this.arrItem = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(id, null);
        name = (TextView) convertView.findViewById(R.id.txtNameApp);
        sotien = (TextView) convertView.findViewById(R.id.txtSotien);
        btnCaidat = (Button) convertView.findViewById(R.id.btnCaidat);
        imgItem = (ImageView) convertView.findViewById(R.id.imageItem);
        name.setText(arrItem.get(position).getNameapp());
        sotien.setText("+2000");
        imgItem.setBackgroundResource(R.mipmap.item_app);
        return convertView;
    }
}
