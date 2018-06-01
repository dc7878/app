package com.cc.dc.kotlindemo.widgets

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

import com.cc.dc.kotlindemo.R

/**
 * Created by yxm on 2017.02.15.
 * https://github.com/yixiaoming/PullRefreshLayout
 */

class PullRefreshLayout : ViewGroup {

    private var mHeader: View? = null
    private var mFooter: View? = null
    private var mHeaderText: TextView? = null
    private var mHeaderArrow: ImageView? = null
    private var mHeaderProgressBar: ImageView? = null
    private var animationDrawableHeader: AnimationDrawable? = null
    private var mFooterText: TextView? = null
    private var mFooterProgressBar: ImageView? = null
    private var animationDrawableFooter: AnimationDrawable? = null

    private var mLayoutContentHeight: Int = 0
    private var mEffectiveHeaderHeight: Int = 0
    private var mEffectiveFooterHeight: Int = 0
    private var mLastMoveY: Int = 0
    private var mLastYIntercept: Int = 0
    private var lastChildIndex: Int = 0

    private var refreshable = true

    private var loadMoreAble = true

    private var hasNoMoreData = false

    private var mStatus = Status.NORMAL

    private var mRefreshListener: OnRefreshListener? = null

    private fun updateStatus(status: Status) {
        mStatus = status
    }

    private enum class Status {
        NORMAL, TRY_REFRESH, REFRESHING, TRY_LOAD_MORE, LOADING
    }

    interface OnRefreshListener {
        fun refreshFinished()

        fun loadMoreFinished()
    }

    fun setRefreshListener(mRefreshListener: OnRefreshListener) {
        this.mRefreshListener = mRefreshListener
    }

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    // 当view的所有child从xml中被初始化后调用
    override fun onFinishInflate() {
        super.onFinishInflate()

        lastChildIndex = childCount - 1

        addHeader()
        addFooter()
    }

    private fun addHeader() {
        mHeader = LayoutInflater.from(context).inflate(R.layout.pull_header, null, false)
        val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        addView(mHeader, params)

        mHeaderText = findViewById(R.id.header_text)
        mHeaderProgressBar = findViewById(R.id.header_progressbar)
        animationDrawableHeader = mHeaderProgressBar!!.background as AnimationDrawable
        mHeaderArrow = findViewById(R.id.header_arrow)
    }

    private fun addFooter() {
        mFooter = LayoutInflater.from(context).inflate(R.layout.pull_footer, null, false)
        val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        addView(mFooter, params)
        mFooterText = findViewById(R.id.footer_text)
        mFooterProgressBar = findViewById(R.id.footer_progressbar)
        animationDrawableFooter = mFooterProgressBar!!.background as AnimationDrawable
        animationDrawableFooter!!.start()
        // 默认隐藏footer
        mFooter!!.visibility = View.GONE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mLayoutContentHeight = 0

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child === mHeader) {
                child.layout(0, 0 - child.measuredHeight, child.measuredWidth, 0)
                if (mEffectiveHeaderHeight == 0) {
                    mEffectiveHeaderHeight = child.height
                }
            } else if (child === mFooter) {
                child.layout(0, mLayoutContentHeight, child.measuredWidth, mLayoutContentHeight + child.measuredHeight)
                mEffectiveFooterHeight = child.height
            } else {
                child.layout(0, mLayoutContentHeight, child.measuredWidth, mLayoutContentHeight + child.measuredHeight)
                if (i < childCount) {
                    if (child is ScrollView) {
                        mLayoutContentHeight += measuredHeight
                        continue
                    }
                    mLayoutContentHeight += child.measuredHeight
                }
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val y = event.y.toInt()

        // 正在刷新或加载更多，避免重复
        if (mStatus == Status.REFRESHING || mStatus == Status.LOADING) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> mLastMoveY = y
            MotionEvent.ACTION_MOVE -> {
                val dy = mLastMoveY - y
                // 一直在上拉
                if (scrollY <= 0 && dy <= 0) {
                    if (mStatus == Status.TRY_LOAD_MORE) {
                        scrollBy(0, dy / 30)
                    } else {
                        scrollBy(0, dy / 3)
                    }
                } else if (scrollY >= 0 && dy >= 0) {
                    if (mStatus == Status.TRY_REFRESH) {
                        scrollBy(0, dy / 30)
                    } else {
                        scrollBy(0, dy / 3)
                    }
                } else {
                    scrollBy(0, dy / 3)
                }// 一直在下拉
                beforeRefreshing(dy.toFloat())
                beforeLoadMore()
            }
            MotionEvent.ACTION_UP ->
                // 下拉刷新，并且到达有效长度
                if (scrollY <= -mEffectiveHeaderHeight) {
                    Log.e("NewsListFragmentInfo", "NewsListFragmentInfo>1>>" + scrollY)
                    releaseWithStatusRefresh()
                    if (mRefreshListener != null) {
                        mRefreshListener!!.refreshFinished()
                    }
                } else if (scrollY >= mEffectiveFooterHeight) {
                    Log.e("NewsListFragmentInfo", "NewsListFragmentInfo>2>>" + scrollY)
                    releaseWithStatusLoadMore()
                    if (mRefreshListener != null) {
                        mRefreshListener!!.loadMoreFinished()
                    }
                } else {
                    Log.e("NewsListFragmentInfo", "NewsListFragmentInfo>3>>" + scrollY)
                    releaseWithStatusTryRefresh()
                    releaseWithStatusTryLoadMore()
                }// 上拉加载更多，达到有效长度
        }
        mLastMoveY = y
        return super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        var intercept = false
        val y = event.y.toInt()
        if (mStatus == Status.REFRESHING || mStatus == Status.LOADING) {
            // 加载更多、下拉刷新时不能滑动View, 拦截但是本身不做处理
            return true
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                // 拦截时需要记录点击位置，不然下一次滑动会出错
                mLastMoveY = y
                intercept = false
            }
            MotionEvent.ACTION_MOVE -> {
                if (y > mLastYIntercept) {
                    val child = getChildAt(0)
                    intercept = getRefreshIntercept(child) && refreshable
                    if (intercept) {
                        updateStatus(Status.TRY_REFRESH)
                    }
                } else if (y < mLastYIntercept) {
                    val child = getChildAt(lastChildIndex)
                    intercept = getLoadMoreIntercept(child) && loadMoreAble && !hasNoMoreData
                    if (intercept) {
                        updateStatus(Status.TRY_LOAD_MORE)
                    }
                } else {
                    intercept = false
                }
            }
            MotionEvent.ACTION_UP -> {
                intercept = false
            }
        }
        mLastYIntercept = y
        return intercept
    }

    /*汇总判断 刷新和加载是否拦截*/
    private fun getRefreshIntercept(child: View): Boolean {
        var intercept = false
        if (child is AdapterView<*>) {
            intercept = adapterViewRefreshIntercept(child)
        } else if (child is ScrollView) {
            intercept = scrollViewRefreshIntercept(child)
        } else if (child is RecyclerView) {
            intercept = recyclerViewRefreshIntercept(child)
        }
        return intercept
    }

    private fun getLoadMoreIntercept(child: View): Boolean {
        var intercept = false
        if (child is AdapterView<*>) {
            intercept = adapterViewLoadMoreIntercept(child)
        } else if (child is ScrollView) {
            intercept = scrollViewLoadMoreIntercept(child)
        } else if (child is RecyclerView) {
            intercept = recyclerViewLoadMoreIntercept(child)
        }
        return intercept
    }
    /*汇总判断 刷新和加载是否拦截*/

    /*具体判断各种View是否应该拦截*/
    // 判断AdapterView下拉刷新是否拦截
    private fun adapterViewRefreshIntercept(child: View): Boolean {
        var intercept = true
        val adapterChild = child as AdapterView<*>
        if (adapterChild.firstVisiblePosition != 0 || adapterChild.getChildAt(0).top != 0) {
            intercept = false
        }
        return intercept
    }

    // 判断AdapterView加载更多是否拦截
    private fun adapterViewLoadMoreIntercept(child: View): Boolean {
        var intercept = false
        val adapterChild = child as AdapterView<*>
        if (adapterChild.lastVisiblePosition == adapterChild.count - 1 && adapterChild.getChildAt(adapterChild.childCount - 1).bottom >= measuredHeight) {
            intercept = true
        }
        return intercept
    }

    // 判断ScrollView刷新是否拦截
    private fun scrollViewRefreshIntercept(child: View): Boolean {
        var intercept = false
        if (child.scrollY <= 0) {
            intercept = true
        }
        return intercept
    }

    // 判断ScrollView加载更多是否拦截
    private fun scrollViewLoadMoreIntercept(child: View): Boolean {
        var intercept = false
        val scrollView = child as ScrollView
        val scrollChild = scrollView.getChildAt(0)
        if (scrollView.scrollY >= scrollChild.height - scrollView.height) {
            intercept = true
        }
        return intercept
    }

    // 判断RecyclerView刷新是否拦截
    private fun recyclerViewRefreshIntercept(child: View): Boolean {
        var intercept = false
        val recyclerView = child as RecyclerView
        if (recyclerView.computeVerticalScrollOffset() <= 0) {
            intercept = true
        }
        return intercept
    }

    // 判断RecyclerView加载更多是否拦截
    private fun recyclerViewLoadMoreIntercept(child: View): Boolean {
        var intercept = false
        val recyclerView = child as RecyclerView
        if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange()) {
            intercept = true
        }
        return intercept
    }

    /*修改header和footer的状态*/
    fun beforeRefreshing(dy: Float) {
        //计算旋转角度
        var scrollY = Math.abs(scrollY)
        scrollY = if (scrollY > mEffectiveHeaderHeight) mEffectiveHeaderHeight else scrollY
        val angle = (scrollY * 1.0 / mEffectiveHeaderHeight * 180).toFloat()
        mHeaderArrow!!.rotation = angle
        if (getScrollY() <= -mEffectiveHeaderHeight) {
            mHeaderText!!.text = "松开刷新"
        } else {
            mHeaderText!!.text = "下拉刷新"
        }
    }

    fun beforeLoadMore() {
        if (scrollY >= mEffectiveHeaderHeight) {
            if (mFooter!!.visibility == View.GONE && loadMoreAble) {
                mFooter!!.visibility = View.VISIBLE
            }
            mFooterText!!.text = "松开加载更多"
        } else {
            mFooterText!!.text = "上拉加载更多"
        }
    }

    fun refreshFinished() {
        hasNoMoreData = false
        scrollTo(0, 0)
        mHeaderText!!.text = "下拉刷新"
        mHeaderProgressBar!!.visibility = View.GONE
        animationDrawableHeader!!.stop()
        mHeaderArrow!!.visibility = View.VISIBLE
        updateStatus(Status.NORMAL)
    }

    fun loadMoreFinished() {
        mFooterText!!.text = "上拉加载"
        mFooterProgressBar!!.visibility = View.GONE
        //        animationDrawableFooter.stop();
        scrollTo(0, 0)
        updateStatus(Status.NORMAL)
    }

    private fun releaseWithStatusTryRefresh() {
        scrollBy(0, -scrollY)
        mHeaderText!!.text = "下拉刷新"
        updateStatus(Status.NORMAL)
    }

    private fun releaseWithStatusTryLoadMore() {
        scrollBy(0, -scrollY)
        mFooterText!!.text = "上拉加载更多"
        updateStatus(Status.NORMAL)
    }

    private fun releaseWithStatusRefresh() {
        scrollTo(0, -mEffectiveHeaderHeight)
        mHeaderProgressBar!!.visibility = View.VISIBLE
        animationDrawableHeader!!.start()
        mHeaderArrow!!.visibility = View.GONE
        mHeaderText!!.text = "正在刷新"
        updateStatus(Status.REFRESHING)
    }

    private fun releaseWithStatusLoadMore() {
        scrollTo(0, mEffectiveFooterHeight)
        mFooterText!!.text = "正在加载"
        mFooterProgressBar!!.visibility = View.VISIBLE
        //        animationDrawableFooter.start();
        updateStatus(Status.LOADING)
    }

    fun startRefresh() {
        scrollTo(0, -mEffectiveHeaderHeight)
        mHeaderProgressBar!!.visibility = View.VISIBLE
        animationDrawableHeader!!.start()
        mHeaderArrow!!.visibility = View.GONE
        mHeaderText!!.text = "正在刷新"
        updateStatus(Status.REFRESHING)
        if (mRefreshListener != null) {
            mRefreshListener!!.refreshFinished()
        }
    }

    fun setRefreshable(refreshable: Boolean) {
        this.refreshable = refreshable
    }

    fun setLoadMoreAble(loadMoreAble: Boolean) {
        this.loadMoreAble = loadMoreAble
    }

    fun setHasNoMoreData(hasNoMoreData: Boolean) {
        this.hasNoMoreData = hasNoMoreData
        if (hasNoMoreData) {
            mFooter!!.visibility = View.GONE
        }
    }
}
