package edu.wschina.a02.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import edu.wschina.a02.R;
import edu.wschina.a02.api.GetInfoApi;
import edu.wschina.a02.api.GetMyPictureApi;
import edu.wschina.a02.databinding.FragmentHomeBinding;
import edu.wschina.a02.databinding.FragmentMyBinding;
import edu.wschina.a02.util.MyImageLoader;


public class MyFragment extends Fragment {
	FragmentMyBinding binding;
	
	
	public static MyFragment newInstance() {
		MyFragment fragment = new MyFragment();
		return fragment;
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		binding = FragmentMyBinding.inflate(getLayoutInflater());
		return binding.getRoot();
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		GetInfoApi.get(getContext(), data -> {
			if (data.code == 0) {
				binding.username.setText(data.data.username);
				binding.email.setText(data.data.email);
			}
			
		});
		
		binding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
		GetMyPictureApi.get(getContext(), data -> {
			binding.recyclerView.setAdapter(new RecyclerView.Adapter<MyPictureViewHolder>() {
				@NonNull
				@Override
				public MyPictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
					return new MyPictureViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.item_my_pic, parent, false));
				}
				
				@Override
				public void onBindViewHolder(@NonNull MyPictureViewHolder holder, int position) {
					GetMyPictureApi.MyPicMo item = data.data.list.get(position);
					MyImageLoader.loadImage(holder.imageView, "http://whisperx.cn:9100" + item.url);
				}
				
				@Override
				public int getItemCount() {
					return data.data.list.size();
				}
			});
		});
		binding.upload.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				new UploadActivity().show(getChildFragmentManager(), "Upload");
				
			}
		});
		
	}
	
	private static class MyPictureViewHolder extends RecyclerView.ViewHolder {
		ImageView imageView;
		
		public MyPictureViewHolder(@NonNull View itemView) {
			super(itemView);
			imageView = itemView.findViewById(R.id.image);
		}
	}
}