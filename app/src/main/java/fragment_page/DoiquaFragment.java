package fragment_page;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.dotplays.dotcashs.R;

import java.util.ArrayList;

import adapter.AdapterDoiqua;
import model.ModelDoiquaChild;
import model.ModelDoiquaGroup;

public class DoiquaFragment extends Fragment {
    private Activity a;
    private ExpandableListView lv;
    private ArrayList<ModelDoiquaChild> children;
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
        addEvent();
    }

    public void setListview() {
        String[] group = getResources().getStringArray(R.array.group_name);
        children = new ArrayList<ModelDoiquaChild>();
        children.add(new ModelDoiquaChild("Thẻ Viettel 10.000", "-1000xu"));
        children.add(new ModelDoiquaChild("Thẻ Viettel 50.000", "-5000xu"));
        children.add(new ModelDoiquaChild("Thẻ Viettel 100.000", "-10000xu"));
        lv = (ExpandableListView) a.findViewById(R.id.lv_doiqua);
        adapter = new AdapterDoiqua(getContext(), group, children);
        lv.setAdapter(adapter);
    }

    public void addEvent() {
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView txt = (TextView) a.findViewById(R.id.txt_giaodien_money_count);
                int so = Integer.parseInt(txt.getText().toString()) + 1;
                txt.setText(String.valueOf(so));
            }
        });
    }
}
