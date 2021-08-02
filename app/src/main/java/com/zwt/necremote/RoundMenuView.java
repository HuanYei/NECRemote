package com.zwt.necremote;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class RoundMenuView extends View {
    public RoundMenuView(Context context) {
        super(context);
        initView();
    }

    public RoundMenuView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public RoundMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RoundMenuView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    private Paint mPaint;
    private Paint mClickPaint;
    private RectF mRectFBig;
    private RectF mRectFLittle;

    private Path mPathLeft;
    private Path mPathRight;
    private Path mPathTop;
    private Path mPathBottom;
    private Path mPathCenter;

    private float mInitSweepAngle = 0;
    private float mBigSweepAngle = 84;
    private float mLittleSweepAngle = 82;

    private float mBigMarginAngle;
    private float mLittleMarginAngle;

    private List<Region> mList;

    private Region mAllRegion;

    private Region mRegionTop;
    private Region mRegionRight;
    private Region mRegionLeft;
    private Region mRegionBottom;
    private Region mRegionCenter;

    private int mRadius;

    private static final int LEFT = 0;
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 3;
    private static final int CENTER = 4;

    private int mClickFlag = -1;

    private int mWidth;

    private int mCurX, mCurY;

    private RegionViewClickListener mListener;

    //设置监听器
    public void setListener(RegionViewClickListener mListener) {
        this.mListener = mListener;
    }
    //初始化
    // <color name="purple_500">#FF6200EE</color>浅
    // <color name="purple_700">#FF3700B3</color>
    private void initView(){
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#FF018786"));

        mClickPaint = new Paint(mPaint);
        mClickPaint.setColor(Color.parseColor("#FF018786"));

        mPathLeft = new Path();
        mPathTop = new Path();
        mPathRight = new Path();
        mPathBottom = new Path();
        mPathCenter = new Path();

        mList = new ArrayList<>();

        mRegionLeft = new Region();
        mRegionTop = new Region();
        mRegionRight = new Region();
        mRegionBottom = new Region();
        mRegionCenter = new Region();

        mBigMarginAngle = 90 - mBigSweepAngle;
        mLittleMarginAngle = 90 - mLittleSweepAngle;
    }

    private void initPath() {
        mList.clear();
        // 初始化right路径
        mPathRight.addArc(mRectFBig, mInitSweepAngle - mBigSweepAngle / 2,
                mBigSweepAngle);
        mPathRight.arcTo(mRectFLittle, mInitSweepAngle + mLittleSweepAngle / 2,
                -mLittleSweepAngle);
        mPathRight.close();

        // 计算right的区域
        mRegionRight.setPath(mPathRight, mAllRegion);
        mList.add(mRegionRight);

        // 初始化bottom路径
        mPathBottom.addArc(mRectFBig, mInitSweepAngle - mBigSweepAngle / 2
                + mBigMarginAngle + mBigSweepAngle, mBigSweepAngle);
        mPathBottom.arcTo(mRectFLittle, mInitSweepAngle + mLittleSweepAngle / 2
                + mLittleMarginAngle + mLittleSweepAngle, -mLittleSweepAngle);
        mPathBottom.close();

        // 计算bottom的区域
        mRegionBottom.setPath(mPathBottom, mAllRegion);
        mList.add(mRegionBottom);

        // 初始化left路径
        mPathLeft.addArc(mRectFBig, mInitSweepAngle - mBigSweepAngle / 2 + 2
                * (mBigMarginAngle + mBigSweepAngle), mBigSweepAngle);
        mPathLeft.arcTo(mRectFLittle, mInitSweepAngle + mLittleSweepAngle / 2
                        + 2 * (mLittleMarginAngle + mLittleSweepAngle),
                -mLittleSweepAngle);
        mPathLeft.close();

        // 计算left的区域
        mRegionLeft.setPath(mPathLeft, mAllRegion);
        mList.add(mRegionLeft);

        // 初始化top路径
        mPathTop.addArc(mRectFBig, mInitSweepAngle - mBigSweepAngle / 2 + 3
                * (mBigMarginAngle + mBigSweepAngle), mBigSweepAngle);
        mPathTop.arcTo(mRectFLittle, mInitSweepAngle + mLittleSweepAngle / 2
                        + 3 * (mLittleMarginAngle + mLittleSweepAngle),
                -mLittleSweepAngle);
        mPathTop.close();

        // 计算top的区域
        mRegionTop.setPath(mPathTop, mAllRegion);
        mList.add(mRegionTop);

        // 初始化center路径
        mPathCenter.addCircle(0, 0, mRadius, Path.Direction.CW);
        mPathCenter.close();

        // 计算center的区域
        mRegionCenter.setPath(mPathCenter, mAllRegion);
        mList.add(mRegionCenter);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);

        canvas.drawPath(mPathRight, mPaint);
        canvas.drawPath(mPathBottom, mPaint);
        canvas.drawPath(mPathLeft, mPaint);
        canvas.drawPath(mPathTop, mPaint);
        canvas.drawPath(mPathCenter, mPaint);

        switch (mClickFlag) {
            case RIGHT:
                canvas.drawPath(mPathRight, mClickPaint);
                break;
            case BOTTOM:
                canvas.drawPath(mPathBottom, mClickPaint);
                break;
            case LEFT:
                canvas.drawPath(mPathLeft, mClickPaint);
                break;
            case TOP:
                canvas.drawPath(mPathTop, mClickPaint);
                break;
            case CENTER:
                canvas.drawPath(mPathCenter, mClickPaint);
                break;
        }

        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mAllRegion = new Region(-mWidth, -mWidth, mWidth, mWidth);

        mRectFBig = new RectF(-mWidth / 2, -mWidth / 2, mWidth / 2, mWidth / 2);

        mRectFLittle = new RectF(-mWidth / 3, -mWidth / 3, mWidth / 3,
                mWidth / 3);

        mRadius = mWidth / 4;

        initPath();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 减去移除 的位置
        mCurX = (int) event.getX() - getMeasuredWidth() / 2;
        mCurY = (int) event.getY() - getMeasuredHeight() / 2;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                containRect(mCurX, mCurY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                if (mClickFlag != -1) {
                    containRect(mCurX, mCurY);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if (mClickFlag != -1) {
                    switch (mClickFlag) {
                        case RIGHT:
                            if (mListener != null) {
                                mListener.clickRight();
                            }
                            break;
                        case BOTTOM:
                            if (mListener != null) {
                                mListener.clickBottom();
                            }
                            break;
                        case LEFT:
                            if (mListener != null) {
                                mListener.clickLeft();
                            }
                            break;
                        case TOP:
                            if (mListener != null) {
                                mListener.clickTop();
                            }
                            break;
                        case CENTER:
                            if (mListener != null) {
                                mListener.clickCenter();
                            }
                            break;
                    }
                    mClickFlag = -1;
                }
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    public void containRect(int x, int y) {
        int index = -1;
        for (int i = 0; i < mList.size(); i++) {
            if (mList.get(i).contains(x, y)) {
                mClickFlag = switchRect(i);
                index = i;
                break;
            }
        }

        if (index == -1) {
            mClickFlag = -1;
        }
    }

    public int switchRect(int i) {
        switch (i) {
            case 0:
                Log.i("aaa", "RIGHT ");
                return RIGHT;
            case 1:
                Log.i("aaa", "BOTTOM ");
                return BOTTOM;
            case 2:
                Log.i("aaa", "LEFT ");
                return LEFT;
            case 3:
                Log.i("aaa", "TOP");
                return TOP;
            case 4:
                Log.i("aaa", "CENTER");
                return CENTER;
            default:
                return -1;
        }
    }

    public interface RegionViewClickListener {
        // 左边按钮被点击了
        public void clickLeft();

        // 上边按钮被点击了
        public void clickTop();

        // 右边按钮被点击了
        public void clickRight();

        // 下边按钮被点击了
        public void clickBottom();

        // 中间按钮被点击了
        public void clickCenter();
    }
    private Bitmap getBitmap(Context context, int drawableId){
        Bitmap bitmap;
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            Drawable vectorDrawable = context.getDrawable(drawableId);
            bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }else {
            bitmap = BitmapFactory.decodeResource(context.getResources() , drawableId);
        }
        return bitmap;
    }
}
