package com.example.animation;

import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ViewPropertyAnimator
        View myView = findViewById(R.id.myView);
        ViewPropertyAnimator vps = myView.animate();
        vps.setDuration(3000L).translationX(500f);
        myView.setVisibility(View.GONE);

        // 分割线==================================================================================

//        ObjectAnimator
        View sportsView = findViewById(R.id.sportsView);
        // 创建 ObjectAnimator 对象
        ObjectAnimator animator = ObjectAnimator.ofFloat(sportsView, "progress", 0, 65);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(3000L);
        // 执行动画
        animator.start();
        sportsView.setVisibility(View.GONE);

//        Path path = new Path();
            // 先以「动画完成度 : 时间完成度 = 1 : 1」的速度匀速运行 25%
//        interpolatorPath.lineTo(0.25f, 0.25f);
            // 然后瞬间跳跃到 150% 的动画完成度
//        interpolatorPath.moveTo(0.25f, 1.5f);
            // 再匀速倒车，返回到目标点
//        interpolatorPath.lineTo(1, 1);
//        PathInterpolator interpolator = new PathInterpolator(path); // 自定义动画效果

        // 分割线==================================================================================

        ArgbEvaluatorView animationView = findViewById(R.id.animationView);
        animationView.setArgbEvaluator();
        animationView.setVisibility(View.GONE);

        // 分割线==================================================================================

        ObjectValueView objectValueView = findViewById(R.id.objectValueView);
        objectValueView.setPointFEvaluator();
        objectValueView.setVisibility(View.GONE);

        // 分割线==================================================================================

        ImageView imageView1 = findViewById(R.id.imageview1);
        imageView1.animate()
                .scaleX(0.5f)
                .scaleY(0.5f)
                .alpha(0.5f).
                setDuration(5000L);
        imageView1.setVisibility(View.GONE);

        // 分割线==================================================================================

        ImageView imageView2 = findViewById(R.id.imageview2);
        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0.5f);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0.5f);
        PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0.5f);

        ObjectAnimator imageView2animator = ObjectAnimator.ofPropertyValuesHolder(imageView2, holder1, holder2, holder3);
        imageView2animator.setRepeatMode(ValueAnimator.RESTART);
        imageView2animator.setRepeatCount(ValueAnimator.INFINITE);
        imageView2animator.setDuration(5000L);
        imageView2animator.start();
        imageView2.setVisibility(View.GONE);

        // 分割线==================================================================================

        ImageView imageView3 = findViewById(R.id.imageview3);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView3,"scaleY",0f,1f);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.setDuration(3000L);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView3,"translationX",0,500);
        animator2.setInterpolator(new DecelerateInterpolator());
        animator2.setDuration(6000L);
        AnimatorSet animatorSet = new AnimatorSet();
        // 方式1、两个动画依次执行
//        animatorSet.playSequentially(animator1, animator2);

        // 方式2、两个动画同时执行
//        animatorSet.playTogether(animator1, animator2);

        // 方式3、精确配置各个 Animator 之间的关系
//        animatorSet.play(animator1).with(animator2);
//        animatorSet.play(animator1).before(animator2);
        animatorSet.play(animator1).after(animator2);
        animatorSet.start();
        imageView3.setVisibility(View.GONE);

        // 分割线==================================================================================

        View sportsView1 = findViewById(R.id.sportsView1);
        // 由于 svAnim.setDuration(4000L); 所以在 0秒的时候 在 0% 处开始
        Keyframe keyframe1 = Keyframe.ofFloat(0, 0);
        // 时间经过 50% 的时候，也就是2秒的时候，动画完成度 100%
        Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 100);
        // 时间经过 100% 的时候，也就是4秒的时候，动画完成度倒退到 80%，即反弹 20%
        Keyframe keyframe3 = Keyframe.ofFloat(1, 80);
        PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3);

        ObjectAnimator svAnim = ObjectAnimator.ofPropertyValuesHolder(sportsView1, holder);
        svAnim.setDuration(4000L);
        svAnim.setRepeatMode(ValueAnimator.RESTART);
        svAnim.setRepeatCount(ValueAnimator.INFINITE);
        svAnim.start();













    }
}
// 复杂动画实现方式：
//    1、使用 PropertyValuesHolder 来对多个属性同时做动画；
//    2、使用 AnimatorSet 来同时管理调配多个动画；
//    3、PropertyValuesHolder 的进阶使用：使用 PropertyValuesHolder.ofKeyframe() 来把一个属性拆分成多段，执行更加精细的属性动画。
