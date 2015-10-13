package fragment_page;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.dotplays.dotcashs.R;

import java.util.ArrayList;

import adapter.AdapterIteamApp;
import model.ModelIteamApp;

public class ThuchiFragment extends Fragment {
    private Activity activity;
    private ArrayList<ModelIteamApp> arrayList;
    private AdapterIteamApp adapterIteamApp;
    private ListView lv;
    private ImageView imgDown;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_thuchi, container, false);
    }

    Button btn;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        imgDown = (ImageView) activity.findViewById(R.id.imageItem);
        setListview();
        // Chỗ này bị lỗi
//        imgDown.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean installed = appInstalledOrNot("com.google.android.youtube");
//                if(installed) {
//                    //This intent will help you to launch if the package is already installed
//                    MSG("Ứng dụng này đã cài tên máy");
//                    Intent LaunchIntent = getActivity().getPackageManager()
//                            .getLaunchIntentForPackage("com.google.android.youtube");
//                    startActivity(LaunchIntent);
//                } else {
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse("details?id=com.google.android.youtube"));
//                    try{
//                        MSG("Ứng dụng này CHƯA cài tên máy");
//                        startActivity(intent);
//                    }
//                    catch(Exception e){
//                        intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.youtube"));
//                    }
//                }
//            }
//        });
    }
    public void setListview() {
        lv = (ListView) activity.findViewById(R.id.lv);
        arrayList = new ArrayList<ModelIteamApp>();
        arrayList.add(new ModelIteamApp("Facebook", 2000));
        arrayList.add(new ModelIteamApp("Zalo", 3000));
        adapterIteamApp = new AdapterIteamApp(getActivity(), R.layout.activity_item_app, arrayList);
        lv.setAdapter(adapterIteamApp);
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
        Toast.makeText(getActivity(), a, Toast.LENGTH_LONG).show();
    }
}
