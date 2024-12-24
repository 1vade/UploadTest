package edu.wschina.a02.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.wschina.a02.R;
import edu.wschina.a02.databinding.ActivityMainBinding;
import edu.wschina.a02.fragment.HomeFragment;
import edu.wschina.a02.fragment.MyFragment;

public class MainActivity extends AppCompatActivity {
	ActivityMainBinding binding;
	private List<Fragment> fragmentList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		initView();
		
	}
	
	private void initView() {
		
		fragmentList = new ArrayList<>();
		fragmentList.add(new HomeFragment());
		fragmentList.add(new MyFragment());
		
		binding.tabLayout.setupWithViewPager(binding.viewPager);
		binding.viewPager.setOffscreenPageLimit(fragmentList.size());
		
		binding.viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
			@NonNull
			@Override
			public Fragment getItem(int position) {
				return fragmentList.get(position);
			}
			
			@Override
			public int getCount() {
				return fragmentList.size();
			}
		});
		
		binding.tabLayout.getTabAt(0).setText("首页").setIcon(R.drawable.ic_home);
		binding.tabLayout.getTabAt(1).setText("我的").setIcon(R.drawable.ic_person);
		
		
	}
}