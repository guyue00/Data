package iapay.pannee.com.data.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import iapay.pannee.com.data.R;
import iapay.pannee.com.data.adapters.Animal;
import iapay.pannee.com.data.adapters.AnimalAdapter;

/**
 * ContactsFragment
 * Created by Alex on 2016/5/19.
 */
public class  ContactsFragment extends GradientTabStripFragment {

    private List<Animal> mData = null;
    private Context mContext;
    private AnimalAdapter mAdapter = null;
    private ListView list_animal;
    private String yuan = "元";

    public static ContactsFragment newInstance(String content) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NAME, content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        return inflater.inflate(R.layout.fragment_gradienttabstrip_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView = view.findViewById(R.id.fgb_tv_content);
        String name = getArguments().getString(EXTRA_NAME);
//        textView.setText(name);

        list_animal = view.findViewById(R.id.list_animal);

        //动态加载顶部View和底部View
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        /*View headView = inflater.inflate(R.layout.view_header, null, false);
        View footView = inflater.inflate(R.layout.view_footer, null, false);*/

        mData = new LinkedList<Animal>();
        mData.add(new Animal("H5", "10000"+yuan, R.drawable.h5));
        mData.add(new Animal("Android", "21545412"+yuan, R.drawable.android));
        mData.add(new Animal("iPhone", "214454361"+yuan, R.drawable.iphone));
        mData.add(new Animal("PC", "245454"+yuan, R.drawable.pc));


        mAdapter = new AnimalAdapter((LinkedList<Animal>) mData, mContext);

        //添加表头和表尾需要写在setAdapter方法调用之前！！！
        /*list_animal.addHeaderView(headView);
        list_animal.addFooterView(footView);*/

        list_animal.setAdapter(mAdapter);
        list_animal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext, "你点击了第" + i + "项", Toast.LENGTH_SHORT).show();
            }
        });
    }

}