package com.example.animation.typeevaluator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.animation.typeevaluator
 * @ClassName: PointFEvaluator
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/3/31 18:31
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/3/31 18:31
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class PointFEvaluator implements TypeEvaluator<PointF> {
    PointF newPoint = new PointF();

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float x = startValue.x + (fraction * (endValue.x - startValue.x));
        float y = startValue.y + (fraction * (endValue.y - startValue.y));

        newPoint.set(x, y);

        return newPoint;
    }
}
