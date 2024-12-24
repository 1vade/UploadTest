package edu.wschina.a02.util;

import android.widget.ImageView;

import coil.Coil;
import coil.ImageLoader;
import coil.request.ImageRequest;

public class MyImageLoader {
	public static void loadImage(ImageView imageView, String imageUrl) {
		coil.ImageLoader imageLoader =  Coil.imageLoader(imageView.getContext());
		
		ImageRequest request = new ImageRequest.Builder(imageView.getContext())
				.data(imageUrl)
				.target(imageView)
				.build();
		
		imageLoader.enqueue(request);
	}
}
