package edu.wschina.a02.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import coil.ImageLoader;
import edu.wschina.a02.R;
import edu.wschina.a02.api.GetHomePicApi;
import edu.wschina.a02.databinding.FragmentHomeBinding;
import edu.wschina.a02.ui.PictureDetailActivity;
import edu.wschina.a02.util.MyImageLoader;


public class HomeFragment extends Fragment {
	FragmentHomeBinding binding;
	
	
	public static HomeFragment newInstance() {
		HomeFragment fragment = new HomeFragment();
		return fragment;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		binding = FragmentHomeBinding.inflate(getLayoutInflater());
		return binding.getRoot();
		
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		GetHomePicApi.getPic(getContext(), data -> {
			if (data.code == 0) {
				binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
				binding.recyclerView.setAdapter(new RecyclerView.Adapter<PicViewHolder>() {
					@NonNull
					@Override
					public PicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
						return new PicViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_pic_ist, parent, false));
					}
					
					@Override
					public void onBindViewHolder(@NonNull PicViewHolder holder, int position) {
						GetHomePicApi.PicMo item = data.data.list.get(position);
						MyImageLoader.loadImage(holder.pic, item.url);
						holder.itemView.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View view) {
								Intent intent = new Intent(getContext(), PictureDetailActivity.class);
								intent.putExtra("id", item.id);
								startActivity(intent);
							
							}
						});
					}
					
					@Override
					public int getItemCount() {
						return data.data.list.size();
					}
				});
			}
		});
	}
	
	private static class PicViewHolder extends RecyclerView.ViewHolder {
		ImageView pic;
		
		public PicViewHolder(@NonNull View itemView) {
			super(itemView);
			pic = itemView.findViewById(R.id.pic);
			
			int width = ((Activity) pic.getContext()).getWindowManager().getDefaultDisplay().getWidth();
			ViewGroup.LayoutParams params = pic.getLayoutParams();
			params.width = width / 2;
			params.height = (int) (200 + Math.random() * 400);
			pic.setLayoutParams(params);
			
		}
	}
}