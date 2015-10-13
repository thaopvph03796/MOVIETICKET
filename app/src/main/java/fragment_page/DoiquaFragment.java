package fragment_page;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dotplays.dotcashs.R;

import java.util.ArrayList;

import adapter.AdapterDoiqua;
import model.ModelDoiqua;

public class DoiquaFragment extends Fragment {
    private Activity a;
    private ListView lv;
    private ArrayList<ModelDoiqua> arr;
    private AdapterDoiqua adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_doiqua, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        a = getActivity();
        setListview();
//        addEvent();
    }

    public void setListview() {
        arr = new ArrayList<ModelDoiqua>();
        arr.add(new ModelDoiqua(R.mipmap.viettel_logo, "Thẻ Viettel"));
        arr.add(new ModelDoiqua(R.mipmap.vinaphone_logo, "Thẻ Vinaphone"));
        arr.add(new ModelDoiqua(R.mipmap.mobifone_logo, "Thẻ Mobifone"));
        lv = (ListView) a.findViewById(R.id.lv_doiqua);
        adapter = new AdapterDoiqua(getActivity(), R.layout.activity_doiqua_item, arr);
        lv.setAdapter(adapter);
    }

//    public void addEvent() {
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View v = inflater.inflate(R.layout.activity_giaodien, null);
//                TextView count = (TextView) v.findViewById(R.id.txt_giaodien_money_count);
//                count.setText("OK");
//            }
//        });
//    }
}
