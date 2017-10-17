
package iapay.pannee.com.data.mpactivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ethanco.titlebar.TitleBar;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import iapay.pannee.com.data.R;
import iapay.pannee.com.data.mpactivity.notimportant.DemoBase;


public class LineChartActivity2 extends DemoBase implements OnSeekBarChangeListener,
        OnChartValueSelectedListener {

    private LineChart mChart,mChart2;
    /*private SeekBar mSeekBarX, mSeekBarY;*/
    private TextView tvX, tvY;

    protected float[][] points = new float[][]{{0,10}, {3,47}, {6,11}, {9,38}, {12,9},{15,52}, {18,14}, {21,37}, {24,29}, {10,31}};
    protected float[][] points2 = new float[][]{{0,101}, {3,13}, {6,51}, {9,20}, {12,19},{15,20}, {18,54}, {21,87}, {24,19}, {10,81}};

    private TitleBar titleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mplinechart);

        /*tvX = (TextView) findViewById(R.id.tvXMax);
        tvY = (TextView) findViewById(R.id.tvYMax);
        mSeekBarX = (SeekBar) findViewById(R.id.seekBar1);
        mSeekBarY = (SeekBar) findViewById(R.id.seekBar2);

        mSeekBarX.setProgress(8);
        mSeekBarY.setProgress(100);

        mSeekBarY.setOnSeekBarChangeListener(this);
        mSeekBarX.setOnSeekBarChangeListener(this);

        mSeekBarX.setVisibility(View.INVISIBLE);
        mSeekBarY.setVisibility(View.INVISIBLE);*/
        tvX = findViewById(R.id.textView2);

        mChart = findViewById(R.id.chart1);
        mChart2 = findViewById(R.id.chart2);

//        setmChart(mChart);
//        setmChart(mChart2);
        mChart.setOnChartValueSelectedListener(this);
        mChart2.setOnChartValueSelectedListener(this);

        // no description text
        mChart.getDescription().setEnabled(false);
        mChart2.getDescription().setEnabled(false);
//        mChart.set


        Description description =new Description();
        description.setText("时间(/小时)");
        description.setTextColor(Color.BLACK);
        description.setTextSize(14);
        mChart.setDescription(description);//设置图表描述信息
        mChart.setNoDataText("没有数据熬");//没有数据时显示的文字
        mChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色

        mChart2.setDescription(description);//设置图表描述信息
        mChart2.setNoDataText("没有数据熬");//没有数据时显示的文字
        mChart2.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色



        // enable touch gestures
        mChart.setTouchEnabled(true);
        mChart.setDragDecelerationFrictionCoef(0.5f);
        mChart2.setTouchEnabled(true);
        mChart2.setDragDecelerationFrictionCoef(1f);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.setHighlightPerDragEnabled(false);


        mChart2.setDragEnabled(true);
        mChart2.setScaleEnabled(true);
        mChart2.setDrawGridBackground(false);
        mChart2.setHighlightPerDragEnabled(false);


        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);
        mChart2.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);
        mChart2.setBackgroundColor(Color.WHITE);

        // Chart fling / deceleration
        mChart.setDragDecelerationEnabled(false);//拖拽滚动时，手放开是否会持续滚动，默认是true（false是拖到哪是哪，true拖拽之后还会有缓冲）
        mChart2.setDragDecelerationEnabled(false);

        // add data
        setData(9, 30);

        mChart.animateX(2500);
        mChart2.animateX(2500);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();
        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextSize(10f);
        l.setTextColor(Color.BLACK);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setYOffset(11f);



        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置X轴的位置
        xAxis.setTypeface(mTfLight);
        xAxis.setTextSize(16f);
        xAxis.setTextColor(Color.BLACK);

        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
//        xAxis.setGranularity();
       /* YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(400f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawLabels(true);*/
//        leftAxis.setGranularityEnabled(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setTypeface(mTfLight);
        rightAxis.setTextColor(Color.RED);
//        rightAxis.setAxisMaximum(600f);
//        rightAxis.setAxisMinimum(0f);
        rightAxis.setEnabled(false);
        /*rightAxis.setDrawLabels(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawZeroLine(false);
        rightAxis.setGranularityEnabled(false);*/

        // 设置x轴的LimitLine，index是从0开始的
        LimitLine xLimitLine = new LimitLine(0f,"金额(/元)");
        xLimitLine.setTextSize(14);
        xLimitLine.setLineColor(Color.BLACK);
        xAxis.addLimitLine(xLimitLine);



        // get the legend (only possible after setting data)
        Legend l2 = mChart2.getLegend();
        // modify the legend ...
        l2.setForm(Legend.LegendForm.LINE);
        l2.setTypeface(mTfLight);
        l2.setTextSize(10f);
        l2.setTextColor(Color.BLACK);
        l2.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l2.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l2.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l2.setDrawInside(false);
//        l.setYOffset(11f);



        XAxis xAxis2 = mChart2.getXAxis();
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM); // 设置X轴的位置
        xAxis2.setTypeface(mTfLight);
        xAxis2.setTextSize(16f);
        xAxis2.setTextColor(Color.BLACK);

        xAxis2.setDrawGridLines(false);
        xAxis2.setDrawAxisLine(true);

        YAxis rightAxis2 = mChart2.getAxisRight();
        rightAxis2.setTypeface(mTfLight);
        rightAxis2.setTextColor(Color.RED);

        rightAxis2.setEnabled(false);


        // 设置x轴的LimitLine，index是从0开始的
        LimitLine xLimitLine2 = new LimitLine(0f,"金额(/元)");
        xLimitLine2.setTextSize(14);
        xLimitLine2.setLineColor(Color.TRANSPARENT);
        xAxis2.addLimitLine(xLimitLine2);

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

    private void setmChart(LineChart mChart) {



    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.line, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionToggleValues: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setDrawValues(!set.isDrawValuesEnabled());
                }

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHighlight: {
                if (mChart.getData() != null) {
                    mChart.getData().setHighlightEnabled(!mChart.getData().isHighlightEnabled());
                    mChart.invalidate();
                }
                break;
            }
            case R.id.actionToggleFilled: {

                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawFilledEnabled())
                        set.setDrawFilled(false);
                    else
                        set.setDrawFilled(true);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleCircles: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    if (set.isDrawCirclesEnabled())
                        set.setDrawCircles(false);
                    else
                        set.setDrawCircles(true);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleCubic: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.CUBIC_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            : LineDataSet.Mode.CUBIC_BEZIER);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleStepped: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.STEPPED
                            ? LineDataSet.Mode.LINEAR
                            : LineDataSet.Mode.STEPPED);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionToggleHorizontalCubic: {
                List<ILineDataSet> sets = mChart.getData()
                        .getDataSets();

                for (ILineDataSet iSet : sets) {

                    LineDataSet set = (LineDataSet) iSet;
                    set.setMode(set.getMode() == LineDataSet.Mode.HORIZONTAL_BEZIER
                            ? LineDataSet.Mode.LINEAR
                            : LineDataSet.Mode.HORIZONTAL_BEZIER);
                }
                mChart.invalidate();
                break;
            }
            case R.id.actionTogglePinch: {
                if (mChart.isPinchZoomEnabled())
                    mChart.setPinchZoom(false);
                else
                    mChart.setPinchZoom(true);

                mChart.invalidate();
                break;
            }
            case R.id.actionToggleAutoScaleMinMax: {
                mChart.setAutoScaleMinMaxEnabled(!mChart.isAutoScaleMinMaxEnabled());
                mChart.notifyDataSetChanged();
                break;
            }
            case R.id.animateX: {
                mChart.animateX(3000);
                //mChart.highlightValue(9.7f, 1, false);
                break;
            }
            case R.id.animateY: {
                mChart.animateY(3000);
                break;
            }
            case R.id.animateXY: {
                mChart.animateXY(3000, 3000);
                break;
            }

            case R.id.actionSave: {
                if (mChart.saveToPath("title" + System.currentTimeMillis(), "")) {
                    Toast.makeText(getApplicationContext(), "Saving SUCCESSFUL!",
                            Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getApplicationContext(), "Saving FAILED!", Toast.LENGTH_SHORT)
                            .show();

                // mChart.saveToGallery("title"+System.currentTimeMillis())
                break;
            }
        }
        return true;
    }*/

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        /*tvX.setText("" + (mSeekBarX.getProgress() + 1));
        tvY.setText("" + (mSeekBarY.getProgress()));*/
        setData(10, 30);
//        setData(mSeekBarX.getProgress() + 1, mSeekBarY.getProgress());

        // redraw
        mChart.invalidate();
        mChart2.invalidate();
    }

    private void setData(int count, float range) {

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        ArrayList<Entry> yVals2 = new ArrayList<Entry>();

        ArrayList<Entry> yVals3 = new ArrayList<Entry>();


        for (int i = 0; i < count; i++) {
            yVals3.add(new Entry(points[i][0], points[i][1]));
            yVals2.add(new Entry(points2[i][0], points2[i][1]));
            yVals1.add(new Entry(points[i][0], points[i][1]+10));
        }

        LineDataSet set1, set2, set3;

        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(2);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(1);
            set3 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
//            set1.setValues(yVals1);
            set2.setValues(yVals2);
            set3.setValues(yVals3);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(yVals1, "年");

            set1.setAxisDependency(AxisDependency.LEFT);
            set1.setColor(ColorTemplate.getHoloBlue());
            set1.setCircleColor(Color.WHITE);
            set1.setLineWidth(2.5f);
            set1.setCircleRadius(1f);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setFillAlpha(65);
            set1.setFillColor(ColorTemplate.getHoloBlue());
            set1.setDrawHighlightIndicators(false);
            set1.setDrawCircleHole(false);
            //set1.setFillFormatter(new MyFillFormatter(0f));
            //set1.setDrawHorizontalHighlightIndicator(false);
            //set1.setVisible(false);
            //set1.setCircleHoleColor(Color.WHITE);

            // create a dataset and give it a type
            set2 = new LineDataSet(yVals2, "测试数据1");
            set2.setAxisDependency(AxisDependency.RIGHT);
            set2.setColor(Color.RED);
            set2.setCircleColor(Color.GRAY);
            set2.setLineWidth(2.5f);
            set2.setCircleRadius(1f);
            set2.setHighLightColor(Color.rgb(244, 117, 117));
            set2.setFillAlpha(65);
            set2.setFillColor(Color.RED);
            set2.setDrawCircleHole(false);
            set2.setDrawHighlightIndicators(false);
            //set2.setFillFormatter(new MyFillFormatter(900f));

            set3 = new LineDataSet(yVals3, "测试数据2");
            set3.setAxisDependency(AxisDependency.RIGHT);
            set3.setColor(Color.YELLOW);
            set3.setCircleColor(Color.GRAY);
            set3.setLineWidth(2.5f);
            set3.setCircleRadius(1f);
            set3.setHighLightColor(Color.rgb(244, 117, 117));
            set3.setFillAlpha(65);
            set3.setFillColor(ColorTemplate.colorWithAlpha(Color.YELLOW, 200));
            set3.setDrawCircleHole(false);
            set3.setDrawHighlightIndicators(false);

            // create a data object with the datasets
            LineData data = new LineData(set2, set3);
            data.setValueTextColor(Color.BLACK);
            data.setValueTextSize(10f);


            LineData data2 = new LineData( set3);
            data.setValueTextColor(Color.GRAY);
            data.setValueTextSize(10f);

            // set data
            mChart.setData(data);
            mChart2.setData(data2);
        }
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("Entry selected", e.toString());

        mChart.centerViewToAnimated(e.getX(), e.getY(), mChart.getData().getDataSetByIndex(h.getDataSetIndex())
                .getAxisDependency(), 10000000);
        mChart2.centerViewToAnimated(e.getX(), e.getY(), mChart.getData().getDataSetByIndex(h.getDataSetIndex())
                .getAxisDependency(), 10000000);
        //mChart.zoomAndCenterAnimated(2.5f, 2.5f, e.getX(), e.getY(), mChart.getData().getDataSetByIndex(dataSetIndex)
        // .getAxisDependency(), 1000);
        //mChart.zoomAndCenterAnimated(1.8f, 1.8f, e.getX(), e.getY(), mChart.getData().getDataSetByIndex(dataSetIndex)
        // .getAxisDependency(), 1000);
    }

    @Override
    public void onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        // TODO Auto-generated method stub

    }
}
