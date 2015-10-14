package fragment_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.dotplays.dotcashs.R;

import java.util.ArrayList;

import adapter.AdapterIteamApp;
import model.ModelIteamApp;

public class KiemxuFragment extends Fragment {
    private Activity activity;
    private ArrayList<ModelIteamApp> arrayList;
    private AdapterIteamApp adapterIteamApp;
    private ListView lv;
    private Button btnDown;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_thuchi, container, false);
    }

    Button btn;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        btnDown = (Button) activity.findViewById(R.id.btnCaidat);
        setListview();
        // Không khai báo sự kiện onclick ở đây. Vũ
        // OK thằng onclick cho từng image rồi. check thử xem. chú ý cái số đằng sau Toast
    }

    public void setListview() {
        lv = (ListView) activity.findViewById(R.id.lv);
        arrayList = new ArrayList<ModelIteamApp>();
        arrayList.add(new ModelIteamApp("Facebook", 2000));
        arrayList.add(new ModelIteamApp("Zalo", 3000));
        adapterIteamApp = new AdapterIteamApp(getActivity(), R.layout.activity_item_app, arrayList);
        lv.setAdapter(adapterIteamApp);
    }
}
