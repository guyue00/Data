package iapay.pannee.com.data.mpactivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanco.titlebar.TitleBar;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.util.ArrayList;

import iapay.pannee.com.data.R;

/**
 * Created by Admin on 2017/9/13.
 */

public class PieChartActivity1  extends FragmentActivity implements
        OnChartValueSelectedListener {

    private final static String TAG = "PieChartActivity";

    protected String[] mParties = new String[] {
            "支付宝", "微信", "银联支付", "其他",
    };
    protected float[] mPartiesfloat = new float[] {
            (float) 35.0, (float) 25.0, (float) 20.0, (float) 20.0,
    };

    private PieChart mChart;
    private SeekBar mSeekBarX, mSeekBarY;
    private TextView tvX, tvY;

    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mppiechart);

//        tvX = (TextView) findViewById(R.id.tvXMax);
//        tvY = (TextView) findViewById(R.id.tvYMax);
//        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
//        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);
//        mSeekBarX.setProgress(4);
//        mSeekBarY.setProgress(10);
//        mSeekBarX.setVisibility(View.GONE);
//        mSeekBarY.setVisibility(View.GONE);
//        tvX.setVisibility(View.GONE);
//        tvY.setVisibility(View.GONE);
        
        chartinit();
        titlebar();
    }

    private void titlebar() {
        titleBar = findViewById(R.id.title_bar);
        titleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "left click!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        titleBar.setOnRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "right click!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void chartinit() {

        mChart = findViewById(R.id.chart1);
        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);
        mChart.setExtraOffsets(5, 10, 5, 5);

        mChart.setDragDecelerationFrictionCoef(0.95f);

//        mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

//        mChart.setDrawCenterText(true);
        mChart.setDrawCenterText(false);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        setData(4, 100);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);
        // mChart.spin(2000, 0, 360);

//        mSeekBarX.setOnSeekBarChangeListener(this);
//        mSeekBarY.setOnSeekBarChangeListener(this);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
//        mChart.setEntryLabelColor(Color.WHITE);
        mChart.setEntryLabelColor(Color.BLACK);
//        mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.pie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
//            case R.id.actionToggleValues: {
//                for (IDataSet<?> set : mChart.getData().getDataSets())
//                    set.setDrawValues(!set.isDrawValuesEnabled());
//
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionToggleIcons: {
//                for (IDataSet<?> set : mChart.getData().getDataSets())
//                    set.setDrawIcons(!set.isDrawIconsEnabled());
//
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionToggleHole: {
//                if (mChart.isDrawHoleEnabled())
//                    mChart.setDrawHoleEnabled(false);
//                else
//                    mChart.setDrawHoleEnabled(true);
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionDrawCenter: {
//                if (mChart.isDrawCenterTextEnabled())
//                    mChart.setDrawCenterText(false);
//                else
//                    mChart.setDrawCenterText(true);
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionToggleXVals: {
//
//                mChart.setDrawEntryLabels(!mChart.isDrawEntryLabelsEnabled());
//                mChart.invalidate();
//                break;
//            }
//            case R.id.actionSave: {
//                // mChart.saveToGallery("title"+System.currentTimeMillis());
//                mChart.saveToPath("title" + System.currentTimeMillis(), "");
//                break;
//            }
//            case R.id.actionTogglePercent:
//                mChart.setUsePercentValues(!mChart.isUsePercentValuesEnabled());
//                mChart.invalidate();
//                break;
//            case R.id.animateX: {
//                mChart.animateX(1400);
//                break;
//            }
//            case R.id.animateY: {
//                mChart.animateY(1400);
//                break;
//            }
//            case R.id.animateXY: {
//                mChart.animateXY(1400, 1400);
//                break;
//            }
//            case R.id.actionToggleSpin: {
//                mChart.spin(1000, mChart.getRotationAngle(), mChart.getRotationAngle() + 360, Easing.EasingOption
//                        .EaseInCubic);
//                break;
//            }
        }
        return true;
    }

//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//        tvX.setText("" + (mSeekBarX.getProgress()));
//        tvY.setText("" + (mSeekBarY.getProgress()));
//
//        setData(mSeekBarX.getProgress(), mSeekBarY.getProgress());
//    }

    private void setData(int count, float range) {

        Log.e(TAG,"count = "+count);
        Log.e(TAG,"range = "+range);


        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < count ; i++) {
            Log.e(TAG,"fori = "+i);
            Log.e(TAG,"formult = "+mult);
            Log.e(TAG,"mParties[i % mParties.length] = "+mParties[i % mParties.length]);
            Log.e(TAG,"mPartiesfloat[i % mPartiesfloat.length] = "+mPartiesfloat[i % mPartiesfloat.length]);
//            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
//                    mParties[i % mParties.length],
//                    getResources().getDrawable(R.drawable.star)));

            //
            entries.add(new PieEntry(mPartiesfloat[i % mPartiesfloat.length],
                    mParties[i % mParties.length],
                    getResources().getDrawable(R.drawable.star)));



        }

//        PieDataSet dataSet = new PieDataSet(entries, "Election Results");
        PieDataSet dataSet = new PieDataSet(entries, "");

        dataSet.setDrawIcons(false);

        dataSet.setSliceSpace(3f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(5f);

        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
//        data.setValueTextColor(Color.WHITE);
        data.setValueTextColor(Color.BLACK);
//        data.setValueTypeface(mTfLight);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.move_left_in_activity, R.anim.move_right_out_activity);
    }

}