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

import cn.hsd.college.tcatch.basefragment.BaseTagPager1;
import cn.hsd.college.tcatch.basefragment.c_add_teacher_contentbBaseTagPager;
import cn.hsd.college.tcatch.basefragment.c_delect_teacher_contentbBaseTagPager;
import cn.hsd.college.tcatch.basefragment.c_teacher_leader_contentbBaseTagPager;
import cn.hsd.college.tcatch.basefragment.c_update_teacher_contentbBaseTagPager;
import cn.hsd.student.R;


public class coach_teacher extends AppCompatActivity {
    private int pager_index;
    private ViewPager view_slide;
    private RadioGroup rd_group;
    private List<BaseTagPager1> list_pager1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_teacher);
        rd_group = (RadioGroup) findViewById(R.id.rg_content_radios1);
        view_slide = (ViewPager) findViewById(R.id.vp_main_content_pages1);
        list_pager1 = new ArrayList<BaseTagPager1>();
        initData();

        initEvent();
    }


    public void initEvent() {
        rd_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main_content_home1:
                        Intent intent = new Intent(coach_teacher.this, CollgegeActivity.class);
                        startActivity(intent);
                        //   pager_index = 0;
                        break;

                    case R.id.rb_main_content_seting1:
                        pager_index = 0;
                        break;

                    case R.id.rb_main_content_newscenter1:
                        pager_index = 1;
                        break;
                    case R.id.rb_main_content_smartservice1:
                        pager_index = 2;
                        break;
                    case R.id.rb_main_content_fovaffirs1:
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
        list_pager1.clear();

        // list_pager.add(new Home_contentbBaseTagPager(teacher_sds_leader.this));
        list_pager1.add(new c_teacher_leader_contentbBaseTagPager(coach_teacher.this));
        list_pager1.add(new c_add_teacher_contentbBaseTagPager(coach_teacher.this));

        list_pager1.add(new c_delect_teacher_contentbBaseTagPager(coach_teacher.this));
        list_pager1.add(new c_update_teacher_contentbBaseTagPager(coach_teacher.this));
        Myadpter myadpter = new Myadpter();
        view_slide.setAdapter(myadpter);

    }


    public class Myadpter extends PagerAdapter {


        public int getCount() {
            return list_pager1.size();
        }

        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);


        }


        public Object instantiateItem(ViewGroup container, int position) {
            BaseTagPager1 baseTagPager = list_pager1.get(position);
            View view = baseTagPager.getRoot();
            container.addView(view);
            return view;
        }
    }
}
