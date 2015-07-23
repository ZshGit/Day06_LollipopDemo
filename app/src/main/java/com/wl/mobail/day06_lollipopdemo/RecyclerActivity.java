package com.wl.mobail.day06_lollipopdemo;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements MyAdapter.OnChildClickListener {

    private RecyclerView recycler;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recycler = ((RecyclerView) findViewById(R.id.recycler));
            //创建默认的线性LayoutManager
//        LinearLayoutManager manager = new LinearLayoutManager(this);//获得条状Listview的管理器
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能recycler.setHasFixedSize(true);
//        manager.setReverseLayout(true);
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);//逆向滚动
//        recycler.setLayoutManager(manager);//把设置好的manager加入到recycler中
        //表格样式的Listview  。参数一：是表格的列数，参数二：是数据的排列是方式
        StaggeredGridLayoutManager staggered = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);//水平滚动。一般做图片水平滚动（也常用scrollView做）
        recycler.setLayoutManager(manager);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.format("Item %03d", i));
        }
        //创建并设置Adapter
        adapter = new MyAdapter(this, list);
        adapter.setOnChildClickListener(this);//RecyclerView的点击事件，用到了接口回调
        recycler.setAdapter(adapter);
        recycler.addItemDecoration(new RecyclerView.ItemDecoration() {
            /**
             *
             * @param outRect 外边框
             * @param view itemView
             * @param parent RecyclerView
             * @param state 状态
             */
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(1, 1, 1, 1);//为外边框添加空隙
            }
        });
        DefaultItemAnimator animator = new DefaultItemAnimator();
       // RecyclerView.ItemAnimator animator = new MyItemAnimator();
        animator.setRemoveDuration(1000);
        recycler.setItemAnimator(animator);
    }
//接口回调  获取点击item的值，做一些操作。如添加数据 删除数据等。。。
    @Override
    public void onChildClick(View view, int position, String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
        adapter.removeItem(position);//调用适配器中removeItem方法，删除数据
        //adapter.addItem("nihao",position);
        //RecyclerView的添加删除都是有默认的动画效果的，如果没有效果可以添加此句代码
        recycler.setItemAnimator(new DefaultItemAnimator());
    }
}
