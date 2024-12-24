package edu.wschina.a02.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.wschina.a02.R;
import edu.wschina.a02.api.LoginApi;
import edu.wschina.a02.databinding.ActivityLoginBinding;
import edu.wschina.a02.databinding.ActivityMainBinding;
import edu.wschina.a02.util.SPUtil;

public class LoginActivity extends AppCompatActivity {
	ActivityLoginBinding binding;
	Timer timer;
	List<Integer> integerList;
	
	int currentItem = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityLoginBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		initList();
		setViewPager();
		startAutoScroll();
		
		binding.register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			}
		});
		
		binding.login.setOnClickListener(new View.OnClickListener() {
			String username = binding.username.getText().toString();
			String password = binding.password.getText().toString();
			
			@Override
			public void onClick(View view) {
				LoginApi.login(LoginActivity.this, username, password, data -> {
					if (data.code == 0) {
						SPUtil.putString(LoginActivity.this, "token", data.data);
						startActivity(new Intent(LoginActivity.this, MainActivity.class));
					} else {
						Toast.makeText(LoginActivity.this, data.msg, Toast.LENGTH_SHORT).show();
					}
				});
			}
		});
	}
	private void initList() {
		integerList = new ArrayList<>();
		integerList.add(R.drawable.img1);
		integerList.add(R.drawable.img2);
		integerList.add(R.drawable.img3);
	}
	
	private void setViewPager() {
		
		binding.viewPager.setAdapter(new PagerAdapter() {
			@Override
			public int getCount() {
				return integerList.size();
			}
			
			@Override
			public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
				return view == object;
			}
			
			@NonNull
			@Override
			public Object instantiateItem(@NonNull ViewGroup container, int position) {
				
				ImageView imageView = new ImageView(LoginActivity.this);
				imageView.setImageResource(integerList.get(position));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				container.addView(imageView);
				return imageView;
			}
			
			@Override
			public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
				container.removeView((View) object);
			}
		});
		
	}
	
	private void startAutoScroll() {
		
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				
				if (integerList != null && !integerList.isEmpty()) {
					
					currentItem = (currentItem + 1) % integerList.size();
					
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							
							binding.viewPager.setCurrentItem(currentItem, true);
							
						}
					});
					
				}
				
				
			}
		}, 2000, 2000);
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopAutoScroll();
		
	}
	
	private void stopAutoScroll() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}
}