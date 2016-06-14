package com.syntax.material_demo;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sures on 6/8/2016.
 */
public class MainFragmest extends Fragment {
    ViewPager viewPager;
    MyPagerAdapter myPagerAdapter;
    Activity activity = getActivity();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment,container,false);
        viewPager = (ViewPager)v.findViewById(R.id.myviewpager);
        myPagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(myPagerAdapter);
        return v;
    }

    private class MyPagerAdapter extends PagerAdapter {

        int NumberOfPages = 5;

        int[] res = {
                R.drawable.image_1,
                R.drawable.image_2,
                R.drawable.image_3,
                R.drawable.image_4,
                R.drawable.image_5};
        int[] backgroundcolor = {
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
                R.color.cardview_dark_background,
                R.color.cardview_light_background};

        @Override
        public int getCount() {
            return NumberOfPages;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


//            TextView textView = new TextView(getActivity());
//            textView.setTextColor(Color.WHITE);
//            textView.setTextSize(30);
//            textView.setTypeface(Typeface.DEFAULT_BOLD);
//            textView.setText(String.valueOf(position));

            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(res[position]);
            ViewGroup.LayoutParams imageParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            imageView.setLayoutParams(imageParams);

            LinearLayout layout = new LinearLayout(getActivity());
            layout.setOrientation(LinearLayout.VERTICAL);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
            layout.setBackgroundColor(backgroundcolor[position]);
            layout.setLayoutParams(layoutParams);
//            layout.addView(textView);
            layout.addView(imageView);

            final int page = position;
            layout.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Page " + page + " clicked", Toast.LENGTH_LONG).show();
                }});

            container.addView(layout);
            return layout;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout)object);
        }

    }
}