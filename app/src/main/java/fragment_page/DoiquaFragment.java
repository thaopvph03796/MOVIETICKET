package fragment_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dotplays.dotcashs.R;

import java.util.ArrayList;

import adapter.AdapterDoiqua;
import model.ModelDoiqua;

public class DoiquaFragment extends Fragment {
    private Activity a;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_doiqua, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        a = getActivity();
        ArrayList<ModelDoiqua> arr = new ArrayList<ModelDoiqua>();
        arr.add(new ModelDoiqua(R.mipmap.viettel_logo, "Thẻ Viettel"));
        arr.add(new ModelDoiqua(R.mipmap.vinaphone_logo, "Thẻ Vinaphone"));
        arr.add(new ModelDoiqua(R.mipmap.mobifone_logo, "Thẻ Mobifone"));
        ListView lv = (ListView) a.findViewById(R.id.lv_doiqua);
        AdapterDoiqua adapter = new AdapterDoiqua(getActivity(), R.layout.activity_doiqua_item, arr);
        lv.setAdapter(adapter);
    }
}
