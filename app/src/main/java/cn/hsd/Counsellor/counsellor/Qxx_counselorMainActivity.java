package cn.hsd.Counsellor.counsellor;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import cn.hsd.student.R;
import cn.hsd.Counsellor.counsellor.Qxx_Constant2.ConValue2;

public class Qxx_counselorMainActivity extends TabActivity {

    //����TabHost����
    private TabHost tabHost;

    //����RadioGroup����
    private RadioGroup radioGroup;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qxx__message_main);

        initView();

        initData();
    }

    /**
     * ��ʼ�����
     */
    private void initView() {
        //ʵ����TabHost���õ�TabHost����
        tabHost = getTabHost();

        int count = ConValue2.mTabClassArray2.length;

        for (int i = 0; i < count; i++) {

            TabSpec tabSpec = tabHost.newTabSpec(ConValue2.mTextviewArray2[i]).setIndicator(ConValue2.mTextviewArray2[i]).setContent(getTabItemIntent(i));

            tabHost.addTab(tabSpec);
        }


        radioGroup = (RadioGroup) findViewById(R.id.qxx_radiogroup);
    }

    /**
     * ��ʼ�����
     */
    private void initData() {
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.RadioButton1:
                        tabHost.setCurrentTabByTag(ConValue2.mTextviewArray2[0]);
                        break;
                    case R.id.RadioButton2:
                        tabHost.setCurrentTabByTag(ConValue2.mTextviewArray2[1]);
                        break;
                }
            }
        });
        ((RadioButton) radioGroup.getChildAt(0)).toggle();
    }


    private Intent getTabItemIntent(int index) {
        Intent intent = new Intent(this, ConValue2.mTabClassArray2[index]);
        return intent;
    }

}
