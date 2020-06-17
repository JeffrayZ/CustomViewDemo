package com.example.snaphelper

import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.*
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider


/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.snaphelper
 * @ClassName: GallerySnapHelper
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/6/16 18:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/16 18:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
internal class GallerySnapHelper : SnapHelper() {
    // Orientation helpers are lazily created per LayoutManager.
    private var mVerticalHelper: OrientationHelper? = null
    private var mHorizontalHelper: OrientationHelper? = null
    private var mRecyclerView: RecyclerView? = null
    private val MILLISECONDS_PER_INCH = 40f

    override fun attachToRecyclerView(recyclerView: RecyclerView?) {
        this.mRecyclerView = recyclerView
        super.attachToRecyclerView(recyclerView)
    }

    // 计算SnapView当前位置与目标位置的距离
    // 这个方法会计算第二个参数对应的ItemView当前的坐标与需要对齐的坐标之间的距离。
    // 该方法返回一个大小为2的int数组，分别对应x轴和y轴方向上的距离。
    override fun calculateDistanceToFinalSnap(layoutManager: RecyclerView.LayoutManager, targetView: View): IntArray? {
        val out = IntArray(2)
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToStart(layoutManager, targetView,
                    getHorizontalHelper(layoutManager))
        } else {
            out[0] = 0
        }
        return out
    }

    // 找到当前时刻的SnapView
    // 该方法会找到当前layoutManager上最接近对齐位置的那个view，该view称为SanpView，对应的position称为SnapPosition。
    // 如果返回null，就表示没有需要对齐的View，也就不会做滚动对齐调整。
    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        return if (layoutManager.canScrollHorizontally()) {
            findStartView(layoutManager, getHorizontalHelper(layoutManager))
        } else null
    }

    // 在触发fling时找到targetSnapPosition
    // 该方法会根据触发Fling操作的速率（参数velocityX和参数velocityY）来找到RecyclerView需要滚动到哪个位置，
    // 该位置对应的ItemView就是那个需要进行对齐的列表项。我们把这个位置称为targetSnapPosition，对应的View称为targetSnapView。
    // 如果找不到targetSnapPosition，就返回RecyclerView.NO_POSITION。
    override fun findTargetSnapPosition(layoutManager: RecyclerView.LayoutManager, velocityX: Int, velocityY: Int): Int {
        if (layoutManager !is ScrollVectorProvider) {
            return RecyclerView.NO_POSITION
        }
        val itemCount = layoutManager.itemCount
        if (itemCount == 0) {
            return RecyclerView.NO_POSITION
        }
        val currentView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        val currentPosition = layoutManager.getPosition(currentView)
        if (currentPosition == RecyclerView.NO_POSITION) {
            return RecyclerView.NO_POSITION
        }
        val vectorProvider = layoutManager as ScrollVectorProvider
        val vectorForEnd = vectorProvider.computeScrollVectorForPosition(itemCount - 1)
                ?: // cannot get a vector for the given position.
                return RecyclerView.NO_POSITION

        // 计算一屏的item数
        val deltaThreshold = layoutManager.width / getHorizontalHelper(layoutManager).getDecoratedMeasurement(currentView)

        var hDeltaJump: Int
        if (layoutManager.canScrollHorizontally()) {
            hDeltaJump = estimateNextPositionDiffForFling(layoutManager,
                    getHorizontalHelper(layoutManager), velocityX, 0)
            //对估算出来的位置偏移量进行阈值判断，最多只能滚动一屏的Item个数
            if (hDeltaJump > deltaThreshold) {
                hDeltaJump = deltaThreshold
            }
            if (hDeltaJump < -deltaThreshold) {
                hDeltaJump = -deltaThreshold
            }

            if (vectorForEnd.x < 0) {
                hDeltaJump = -hDeltaJump
            }
        } else {
            hDeltaJump = 0
        }

        val deltaJump = hDeltaJump
        if (deltaJump == 0) {
            return RecyclerView.NO_POSITION
        }
        var targetPos = currentPosition + deltaJump
        if (targetPos < 0) {
            targetPos = 0
        }
        if (targetPos >= itemCount) {
            targetPos = itemCount - 1
        }
        return targetPos
    }

    // targetView的start坐标与RecyclerView的paddingStart之间的差值
    // 就是需要滚动调整的距离
    private fun distanceToStart(layoutManager: RecyclerView.LayoutManager,
                                targetView: View, helper: OrientationHelper): Int {
        return helper.getDecoratedStart(targetView) - helper.startAfterPadding
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (mVerticalHelper == null) {
            mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
        }
        return mVerticalHelper!!
    }

    private fun getHorizontalHelper(
            layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return mHorizontalHelper!!
    }

    private fun findStartView(layoutManager: RecyclerView.LayoutManager,
                              helper: OrientationHelper): View? {
        val childCount = layoutManager.childCount
        if (childCount == 0) {
            return null
        }
        var closestChild: View? = null
        if (layoutManager is LinearLayoutManager) {
            // 找出第一个可见的ItemView的位置
            val linearLayoutManager = layoutManager
            val firstChildPosition = linearLayoutManager.findFirstVisibleItemPosition()
            if (firstChildPosition == RecyclerView.NO_POSITION) {
                return null
            }
            // 找到最后一个完全显示的ItemView，如果该ItemView是列表中的最后一个
            // 就说明列表已经滑动最后了，这时候就不应该根据第一个ItemView来对齐了
            // 要不然由于需要跟第一个ItemView对齐最后一个ItemView可能就一直无法完全显示，
            // 所以这时候直接返回null表示不需要对齐
            val lastChildPosition = linearLayoutManager.findLastVisibleItemPosition()
            if (lastChildPosition == linearLayoutManager.itemCount - 1) {
                return null
            }
            val firstChildView = layoutManager.findViewByPosition(firstChildPosition)
            // 如果第一个ItemView被遮住的长度没有超过一半，就取该ItemView作为snapView
            // 超过一半，就把下一个ItemView作为snapView
            closestChild = if (helper.getDecoratedEnd(firstChildView) >= helper.getDecoratedMeasurement(firstChildView) / 2
                    && helper.getDecoratedEnd(firstChildView) > 0) {
                firstChildView
            } else {
                linearLayoutManager.findViewByPosition(firstChildPosition + 1)
            }
        }
        return closestChild
    }

    private fun estimateNextPositionDiffForFling(layoutManager: RecyclerView.LayoutManager,
                                                 helper: OrientationHelper, velocityX: Int, velocityY: Int): Int {
        val distances = calculateScrollDistance(velocityX, velocityY)
        val distancePerChild = computeDistancePerChild(layoutManager, helper)
        if (distancePerChild <= 0) {
            return 0
        }
        val distance = if (Math.abs(distances[0]) > Math.abs(distances[1])) distances[0] else distances[1]
        return Math.round(distance / distancePerChild)
    }

    private fun computeDistancePerChild(layoutManager: RecyclerView.LayoutManager,
                                        helper: OrientationHelper): Float {
        var minPosView: View? = null
        var maxPosView: View? = null
        var minPos = Int.MAX_VALUE
        var maxPos = Int.MIN_VALUE
        val childCount = layoutManager.childCount
        if (childCount == 0) {
            return INVALID_DISTANCE
        }
        for (i in 0 until childCount) {
            val child = layoutManager.getChildAt(i)
            val pos = layoutManager.getPosition(child!!)
            if (pos == RecyclerView.NO_POSITION) {
                continue
            }
            if (pos < minPos) {
                minPos = pos
                minPosView = child
            }
            if (pos > maxPos) {
                maxPos = pos
                maxPosView = child
            }
        }
        if (minPosView == null || maxPosView == null) {
            return INVALID_DISTANCE
        }
        val start = Math.min(helper.getDecoratedStart(minPosView),
                helper.getDecoratedStart(maxPosView))
        val end = Math.max(helper.getDecoratedEnd(minPosView),
                helper.getDecoratedEnd(maxPosView))
        val distance = end - start
        return if (distance == 0) {
            INVALID_DISTANCE
        } else 1f * distance / (maxPos - minPos + 1)
    }

    companion object {
        private const val INVALID_DISTANCE = 1f
    }

    // 调整滑动速度
    override fun createSnapScroller(layoutManager: RecyclerView.LayoutManager?): LinearSmoothScroller? {
        return if (layoutManager !is ScrollVectorProvider) {
            null
        } else object : LinearSmoothScroller(mRecyclerView?.context) {
            // 滚动过程中，targetView即将要进入到视野时，将匀速滚动变换为减速滚动，然后一直滚动目的坐标位置，使滚动效果更真实，
            override fun onTargetFound(targetView: View, state: RecyclerView.State, action: Action) {
                if (mRecyclerView == null) {
                    // The associated RecyclerView has been removed so there is no action to take.
                    return
                }
                val snapDistances = calculateDistanceToFinalSnap(mRecyclerView?.layoutManager!!,
                        targetView)
                val dx = snapDistances!![0]
                val dy = snapDistances[1]
                val time = calculateTimeForDeceleration(Math.max(Math.abs(dx), Math.abs(dy)))
                if (time > 0) {
                    action.update(dx, dy, time, mDecelerateInterpolator)
                }
            }

            // 滚动速率
            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                // 返回值越小，滚动越快
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
            }
        }
    }
}