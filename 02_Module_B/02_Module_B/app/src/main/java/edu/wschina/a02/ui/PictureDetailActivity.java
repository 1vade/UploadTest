package edu.wschina.a02.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.wschina.a02.R;
import edu.wschina.a02.api.PictureDetailApi;
import edu.wschina.a02.databinding.ActivityPictureDetailBinding;
import edu.wschina.a02.util.MyImageLoader;

public class PictureDetailActivity extends AppCompatActivity {
	ActivityPictureDetailBinding binding;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		binding = ActivityPictureDetailBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		int id = getIntent().getIntExtra("id", 1);
		PictureDetailApi.get(PictureDetailActivity.this, id, data -> {
			if (data.code == 0) {
				binding.time.setText(data.data.time);
				binding.title.setText(data.data.title);
				binding.description.setText(data.data.description);
				MyImageLoader.loadImage(binding.img, data.data.url);
			}
		});
	}
}