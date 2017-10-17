package iapay.pannee.com.data.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import iapay.pannee.com.data.MainActivity;
import iapay.pannee.com.data.R;
import iapay.pannee.com.data.mpactivity.LineChartActivity2;
import iapay.pannee.com.data.mpactivity.PieChartActivity1;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * WechatFragment
 * Created by Alex on 2016/5/19.
 */
public class WechatFragment extends GradientTabStripFragment implements View.OnClickListener {
    private String TAG = "WechatFragment";
    private TextView textView, textView2, textView3, textView4;
    private Intent intent;

    private RollPagerView mLoopViewPager;
    private TestLoopAdapter mLoopAdapter;
    int mPage = 2;//轮播的页码
    OkHttpClient client = new OkHttpClient();

    public static WechatFragment newInstance(String content) {
        WechatFragment fragment = new WechatFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NAME, content);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.e(TAG, "onCreateView: ");
        return inflater.inflate(R.layout.fragment_gradienttabstrip_wechat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated: ");
        textView = view.findViewById(R.id.fgb_tv_content);
        textView2 = view.findViewById(R.id.fgb_tv_content2);
        textView3 = view.findViewById(R.id.fgb_tv_content3);
        textView4 = view.findViewById(R.id.fgb_tv_content4);
        String name = getArguments().getString(EXTRA_NAME);
//        textView.setText(name);
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
        textView3.setOnClickListener(this);
        textView4.setOnClickListener(this);

        view.findViewById(R.id.imageView).setOnClickListener(this);
        view.findViewById(R.id.imageView2).setOnClickListener(this);


        //轮播
        mLoopViewPager = view.findViewById(R.id.loop_view_pager);
        mLoopViewPager.setPlayDelay(3000);
        mLoopViewPager.setAdapter(mLoopAdapter = new TestLoopAdapter(mLoopViewPager));
//        mLoopViewPager.setHintView(new IconHintView(this,R.drawable.point_focus,R.drawable.point_normal));
        mLoopViewPager.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getActivity(), "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });
        getData(mPage);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fgb_tv_content:
            case R.id.imageView:
                intent = new Intent(getActivity(), LineChartActivity2.class);
//                intent.putExtra("data", "mainActivity");
                startActivity(intent);
                break;
            case R.id.fgb_tv_content2:
            case R.id.imageView2:
                intent = new Intent(getActivity(), PieChartActivity1.class);
//                intent.putExtra("data", "mainActivity");
                startActivity(intent);
                break;
            case R.id.fgb_tv_content3:
                intent = new Intent(getActivity(), MainActivity.class);
//                intent.putExtra("data", "mainActivity");
                startActivity(intent);
                break;
            case R.id.fgb_tv_content4:
                intent = new Intent(getActivity(), MainActivity.class);
//                intent.putExtra("data", "mainActivity");
                startActivity(intent);
                break;
            default:
                break;
        }
    }


    public void getData(final int page) {
        Request request = new Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/" + 5 + "/" + page)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.i("NetImageActivity", "error:" + e.getMessage());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络请求失败", Toast.LENGTH_SHORT).show();//e.getMessage()
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String content = response.body().string();
                    Log.i("NetImageActivity", "raw data:" + content);

                    JSONObject jsonObject = new JSONObject(content);
                    JSONArray strArr = jsonObject.getJSONArray("results");
                    final String[] imgs = new String[strArr.length()];
                    for (int i = 0; i < strArr.length(); i++) {
                        JSONObject obj = strArr.getJSONObject(i);
                        imgs[i] = obj.getString("url");
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mLoopAdapter.setImgs(imgs);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class TestLoopAdapter extends LoopPagerAdapter {
        String[] imgs = new String[0];

        public void setImgs(String[] imgs1) {
            this.imgs = imgs1;
            notifyDataSetChanged();
        }


        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            Log.i("RollViewPager", "getView:" + imgs[position]);

            ImageView view = new ImageView(container.getContext());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("RollViewPager", "onClick");
                }
            });
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(getActivity())
                    .load(imgs[position])
//                    .placeholder(R.drawable.star)
                    .placeholder(R.color.colorRipple)
                    .into(view);
            return view;
        }

        @Override
        public int getRealCount() {
            return imgs.length;
        }

    }
}
