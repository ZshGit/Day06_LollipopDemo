package com.wl.mobail.day06_lollipopdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jash
 * Date: 15-7-20
 * Time: 下午3:56
 * Adapter是添加点击事件一个很好的地方，里面是构造布局等View的主要场所，
 * 也是数据和布局进行绑定的地方。
 * =========添加点击事件
 * 1.首先我们在Adapter中创建一个实现点击接口interface OnChildClickListener，其中view是点击的Item，
 * position是点击的位置记录，data是我们的数据，因为我们想知道我点击的区域部分的数据是什么，以便我下一步进行操作：
 * 2.定义完接口，添加接口和设置Adapter接口的方法:setOnChildClickListener
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<String> list;
    private OnChildClickListener listener;//声明该接口
    private RecyclerView recyclerView;
    private String TAG="MyAdapter";

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        Log.v(TAG,"==构造方法");
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        Log.v(TAG,"==onAttachedToRecyclerView");
        this.recyclerView = recyclerView;
    }
      //给声明的接口赋值，把结果值赋给接口，以便回调
    public void setOnChildClickListener(OnChildClickListener listener) {
        Log.v(TAG,"==setOnChildClickListener");
        this.listener = listener;
    }

    /**1.创建新View，被LayoutManager所调用
     * 创建ViewHolder
     * @param parent
     * @param viewType 如果有多个布局，可通过viewType来区分
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.v(TAG,"==onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        //3.点击====将创建的View注册点击事件
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    /**2.将数据与界面进行绑定的操作
     * 把数据绑定到ViewHolder上
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.v(TAG,"==onBindViewHolder");
        holder.text.setText(list.get(position));
    }
        //3.获取数据的数量
    @Override
    public int getItemCount() {
        Log.v(TAG,"==getItemCount");
        return list.size();
    }
//删除数据
    public void removeItem(int position){
        Log.v(TAG,"==removeItem");
        list.remove(position);//首先删除数据源中的数据
        notifyItemRemoved(position);//更新删除后的数据 （自带有动画，200ms的淡出动画）
//        notifyDataSetChanged();
// 该句刷新数据无论是添加数据还是删除数据都可以用，但对添加和删除的动画不起作用。
// 想有动画效果，必须指明是什么刷新，即要指明是添加和是删除刷新。
         /*RecyclerView的添加删除都是有默认的动画效果的，*/
    }

    //添加数据
    public void addItem(String date,int position){
        Log.v(TAG,"==addItem");
        list.add(position,date);
        notifyItemInserted(position);
    }
    @Override
    public void onClick(View v) {
        Log.v(TAG,"==onClick");
        if (listener != null) {
            int position = recyclerView.getChildAdapterPosition(v);//整个数据源的item的排序。
         //   recyclerView.getChildLayoutPosition()指当前屏幕所显示的item项的位置排序。
            listener.onChildClick(v, position, list.get(position));//
        }
    }

    //4.自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView text;

        public ViewHolder(View itemView) {

            super(itemView);
            text = ((TextView) itemView.findViewById(R.id.item_text));
        }
    }
    public interface OnChildClickListener{//定义一个接口，用于获得所点击的item的一些数据

        void onChildClick(View view, int position, String data);
    }
}
