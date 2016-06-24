package cn.hsd.student.message;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import cn.hsd.student.R;
import cn.hsd.student.message.Qxx_Constant.ConValue;

public class Qxx_MessageMain extends TabActivity {

    private TabHost tabHost;


    private RadioGroup radioGroup;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qxx__message_main);

        initView();

        initData();
    }

    /**

     */
    private void initView() {

        tabHost = getTabHost();


        int count = ConValue.mTabClassArray.length;

        for (int i = 0; i < count; i++) {

            TabSpec tabSpec = tabHost.newTabSpec(ConValue.mTextviewArray[i]).setIndicator(ConValue.mTextviewArray[i]).setContent(getTabItemIntent(i));

            tabHost.addTab(tabSpec);
        }


        radioGroup = (RadioGroup) findViewById(R.id.qxx_radiogroup);
    }

    /**

     */
    private void initData() {

        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.RadioButton1:
                        tabHost.setCurrentTabByTag(ConValue.mTextviewArray[0]);
                        break;
                    case R.id.RadioButton2:
                        tabHost.setCurrentTabByTag(ConValue.mTextviewArray[1]);
                        break;
                }
            }
        });
        ((RadioButton) radioGroup.getChildAt(0)).toggle();
    }

    /**

     */
    private Intent getTabItemIntent(int index) {
        Intent intent = new Intent(this, ConValue.mTabClassArray[index]);
        return intent;
    }
}
