package iapay.pannee.com.data.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;

import am.widget.gradienttabstrip.GradientTabStrip;
import iapay.pannee.com.data.R;
import iapay.pannee.com.data.fragments.ContactsFragment;
import iapay.pannee.com.data.fragments.WechatFragment;

/**
 * GradientTabStripAdapter
 * Created by Alex on 2016/5/19.
 */
public class GradientTabStripAdapter extends FragmentPagerAdapter implements
        GradientTabStrip.GradientTabAdapter {

    public GradientTabStripAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        String title = getPageTitle(position).toString();
        switch (position) {
            default:
            case 0:
                return WechatFragment.newInstance(title);
            case 1:
                return ContactsFragment.newInstance(title);
            /*case 2:
                return DiscoveryFragment.newInstance(title);
            case 3:
                return AccountFragment.newInstance(title);*/
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
            case 0:
                return "首页";
            case 1:
                return "我的";
           /* case 2:
                return "我";
            case 3:
                return "我";*/
        }
    }

    @Override
    public Drawable getNormalDrawable(int position, Context context) {
        switch (position) {
            default:
            case 0:
                return ContextCompat.getDrawable(context, R.drawable.ic_gradienttabstrip_chat_normal);
            case 1:
                return ContextCompat.getDrawable(context, R.drawable.ic_gradienttabstrip_account_normal);
           /* case 2:
                return ContextCompat.getDrawable(context, R.drawable.ic_gradienttabstrip_account_normal);
            case 3:
                return ContextCompat.getDrawable(context, R.drawable.ic_gradienttabstrip_account_normal);*/
        }
    }

    @Override
    public Drawable getSelectedDrawable(int position, Context context) {
        switch (position) {
            default:
            case 0:
                return ContextCompat.getDrawable(context, R.drawable.ic_gradienttabstrip_chat_selected);
            case 1:
                return ContextCompat.getDrawable(context, R.drawable.ic_gradienttabstrip_account_selected);
            /*case 2:
                return ContextCompat.getDrawable(context, R.drawable.ic_gradienttabstrip_account_selected);
            case 3:
                return ContextCompat.getDrawable(context, R.drawable.ic_gradienttabstrip_account_selected);*/
        }
    }

    @Override
    public boolean isTagEnable(int position) {
        return position == 3;
    }

    @Override
    public String getTag(int position) {
        switch (position) {
            default:
            case 0:
                return "";
            case 1:
                return "";
           /* case 2:
                return "";
            case 3:
                return "";*/
        }
    }
}
