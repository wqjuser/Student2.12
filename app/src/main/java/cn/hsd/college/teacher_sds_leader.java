package cn.hsd.college;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import cn.hsd.college.teacher.baseFragment.BaseTagPager;
import cn.hsd.college.teacher.baseFragment.add_teacher_contentbBaseTagPager;
import cn.hsd.college.teacher.baseFragment.delect_teacher_contentbBaseTagPager;
import cn.hsd.college.teacher.baseFragment.teacher_leader_contentbBaseTagPager;
import cn.hsd.college.teacher.baseFragment.update_teacher_contentbBaseTagPager;
import cn.hsd.student.R;

public class teacher_sds_leader extends AppCompatActivity {
    private ViewPager view_slide;
    private RadioGroup rd_group;
    private int pager_index;
    private List<BaseTagPager> list_pager;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        //requestWindowFeature(WindowManager.LayoutParams.);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sds_leader);
        rd_group = (RadioGroup) findViewById(R.id.rg_content_radios);
        view_slide = (ViewPager) findViewById(R.id.vp_main_content_pages);
        list_pager = new ArrayList<BaseTagPager>();
        initData();

        initEvent();
    }

    public void initEvent() {
        rd_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main_content_home:
                        Intent intent = new Intent(teacher_sds_leader.this, CollgegeActivity.class);
                        startActivity(intent);
                        //   pager_index = 0;
                        break;

                    case R.id.rb_main_content_seting:
                        pager_index = 0;
                        break;

                    case R.id.rb_main_content_newscenter:
                        pager_index = 1;
                        break;
                    case R.id.rb_main_content_smartservice:
                        pager_index = 2;
                        break;
                    case R.id.rb_main_content_fovaffirs:
                        pager_index = 3;
                        break;

                }
                switchPager();
            }
        });


    }

    public void switchPager() {
        view_slide.setCurrentItem(pager_index);
    }


    private void initData() {
        list_pager.clear();

        // list_pager.add(new Home_contentbBaseTagPager(teacher_sds_leader.this));

        list_pager.add(new teacher_leader_contentbBaseTagPager(teacher_sds_leader.this));
        list_pager.add(new add_teacher_contentbBaseTagPager(teacher_sds_leader.this));
        list_pager.add(new delect_teacher_contentbBaseTagPager(teacher_sds_leader.this));
        list_pager.add(new update_teacher_contentbBaseTagPager(teacher_sds_leader.this));
        Myadpter myadpter = new Myadpter();
        view_slide.setAdapter(myadpter);

    }


    public class Myadpter extends PagerAdapter {


        @Override
        public int getCount() {
            return list_pager.size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);


        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseTagPager baseTagPager = list_pager.get(position);
            View view = baseTagPager.getRoot();
            container.addView(view);
            return view;
        }
    }
}
