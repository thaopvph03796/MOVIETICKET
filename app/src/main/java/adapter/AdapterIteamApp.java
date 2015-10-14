package adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getContext().getPackageManager();
        boolean app_installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }
        catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }

    public void MSG(String a){
        Toast.makeText(getContext(), a, Toast.LENGTH_LONG).show();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(id, null);
        name = (TextView) convertView.findViewById(R.id.txtNameApp);
        sotien = (TextView) convertView.findViewById(R.id.txtSotien);
        btnCaidat = (Button) convertView.findViewById(R.id.btnCaidat);
        imgItem = (ImageView) convertView.findViewById(R.id.imageItem);
        name.setText(arrItem.get(position).getNameapp());
        sotien.setText("+2000");
        imgItem.setBackgroundResource(R.mipmap.item_app);
        btnCaidat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean installed = appInstalledOrNot("com.google.android.youtube");
                if(installed) {
                    //This intent will help you to launch if the package is already installed
                    MSG("Ứng dụng này đã cài tên máy");
                    Intent LaunchIntent = getContext().getPackageManager()
                            .getLaunchIntentForPackage("com.google.android.youtube");
                    getContext().startActivity(LaunchIntent);
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("details?id=com.google.android.youtube"));
                    try{
                        MSG("Ứng dụng này CHƯA cài tên máy" + position);
                        getContext().startActivity(intent);
                    }
                    catch(Exception e){
                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.youtube"));
                    }
                }
            }
        });
        return convertView;
    }
}
