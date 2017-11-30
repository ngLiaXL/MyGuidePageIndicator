package ngliaxl.myguidepageindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {

    private int[] indicatorRes;
    private ImageView[] indicatorItem;
    private LinearLayout container;
    private ViewPager viewPager;
    private int itemHeight;
    private int itemWidth;
    private int itemMarginLeft;
    private int itemMarginUp;
    private int itemMarginRight;
    private int itemMarginBottom;

    public MyIndicator(Context context) {
        super(context);
        init(null);
    }

    public MyIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {

        Context context = getContext();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable
                .MyIndicator, 0, 0);
        itemHeight = (int) typedArray.getDimension(R.styleable.MyIndicator_ind_item_height, 1f);
        itemWidth = (int) typedArray.getDimension(R.styleable.MyIndicator_ind_item_width, 1f);
        itemMarginLeft = (int) typedArray.getDimension(R.styleable.ConstraintSet_android_layout_marginLeft, 1f);
        itemMarginUp = (int) typedArray.getDimension(R.styleable.MyIndicator_ind_item_margin_up,
                1f);
        itemMarginRight = (int) typedArray.getDimension(R.styleable.ConstraintSet_android_layout_marginRight,
                1f);
        itemMarginBottom = (int) typedArray.getDimension(R.styleable.MyIndicator_ind_item_margin_bottom,
                1f);
        container = new LinearLayout(getContext());
        addView(container);
    }

    public MyIndicator setIndicatorRes(int[] indicatorRes) {
        this.indicatorRes = indicatorRes;
        return this;
    }


    public void setViewPager(@Nullable ViewPager pager) {
        releaseViewPager();
        if (pager == null) {
            return;
        }
        viewPager = pager;
        viewPager.addOnPageChangeListener(this);
        initIndicatorItem(getIndicatorCount());

    }

    private void initIndicatorItem(int size) {
        Context context = getContext();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(itemWidth, itemHeight);
        params.setMargins(0, itemMarginUp, (itemMarginRight + itemMarginLeft) / 4,
                itemMarginBottom);
        indicatorItem = new ImageView[size];
        for (int i = 0; i < size; i++) {
            indicatorItem[i] = new ImageView(context);
            indicatorItem[i].setImageResource(indicatorRes[i]);
            indicatorItem[i].setSelected(false);
            container.addView(indicatorItem[i], params);
        }
        indicatorItem[0].setSelected(true);
    }

    private void checkIndicatorSize() {
        if (getViewPagerCount() != getIndicatorCount()) {
            throw new IllegalArgumentException("Indicator count can not match fragments");
        }
    }

    private int getIndicatorCount() {
        if (indicatorRes != null) {
            return indicatorRes.length;
        }
        return 0;
    }

    private int getViewPagerCount() {
        if (viewPager != null && viewPager.getAdapter() != null) {
            return viewPager.getAdapter().getCount();
        }
        return 0;
    }

    private void releaseViewPager() {
        if (viewPager != null) {
            viewPager.removeOnPageChangeListener(this);
            viewPager = null;
        }
    }


    private void setIndicatorRes(int position) {
        for (int i = 0; i < indicatorItem.length; i++) {
            indicatorItem[i].setImageResource(indicatorRes[i]);
            indicatorItem[i].setSelected(i == position);
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setIndicatorRes(position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
