package com.syh.app.butterknifedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_content)//绑定控件注解
            EditText et_content; //控件的修饰符不能为 private
    @BindView(R.id.bt_show_content)
    Button bt_show_content;
    @BindView(R.id.bt_clear)
    Button bt_clear;
    @BindView(R.id.lv_datas)
    ListView lv_datas;

    private Unbinder mUnBinder;//解绑器
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUnBinder = ButterKnife.bind(this);// 在activity中使用时，一定要在setContentView()之后绑定activity

        initView();
    }

    private void initView() {
        mAdapter = new MyAdapter(this);
        for (int i = 'A'; i <= 'z'; i++) {
            mAdapter.add((char) i + "");
        }
        lv_datas.setAdapter(mAdapter);
    }

    /**
     * item点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @OnItemClick(R.id.lv_datas)
    void onItemClick(AdapterView<?> parent, View view, int position, long id){
        Toast.makeText(this,mAdapter.getItem(position),Toast.LENGTH_SHORT).show();
    }

    /**
     * 点击事件注解 直接在{}中添加需要绑定点击事件的控价ID
     * 当然还有@OnItemClick @OnLongClick 等
     *
     * @param view
     */
    @OnClick({R.id.bt_clear, R.id.bt_show_content})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_clear:
                et_content.setText("");
                break;
            case R.id.bt_show_content:
                String msgContent = et_content.getText().toString();
                Toast.makeText(this, msgContent, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * activity 销毁时要解绑控件   不然activity重建时   可能导致绑定失败  在fragmnet中也一样
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
