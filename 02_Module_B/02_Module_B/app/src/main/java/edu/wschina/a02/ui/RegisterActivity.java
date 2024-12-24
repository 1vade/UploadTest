package edu.wschina.a02.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import edu.wschina.a02.api.RegisterApi;
import edu.wschina.a02.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
	ActivityRegisterBinding binding;
	Timer timer;
	List<Integer> integerList;
	
	int currentItem = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityRegisterBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		
		initList();
		setViewPager();
		startAutoScroll();
		
		binding.register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String username = binding.username.getText().toString();
				String password = binding.password.getText().toString();
				String email = binding.email.getText().toString();
				String confirm = binding.email.getText().toString();
				RegisterApi.register(RegisterActivity.this, username, password, email, data -> {
					if (username.isEmpty()) {
						Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
					} else if (email.isEmpty()) {
						Toast.makeText(RegisterActivity.this, "邮箱不能为空", Toast.LENGTH_SHORT).show();
					} else if (!isValidEmail(email)) {
						Toast.makeText(RegisterActivity.this, "不是一个有效的邮箱", Toast.LENGTH_SHORT).show();
					} else if (password.isEmpty()) {
						Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
					} else if (!confirm.equals(password)) {
						Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
					} else if (data.code == 0) {
						Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(RegisterActivity.this, data.msg, Toast.LENGTH_SHORT).show();
					}
				});
				
			}
		});
		
		binding.reLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}
	
	private boolean isValidEmail(String email) {
		return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
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
				
				ImageView imageView = new ImageView(RegisterActivity.this);
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