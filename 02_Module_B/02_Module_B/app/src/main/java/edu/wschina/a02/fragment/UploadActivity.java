package edu.wschina.a02.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import coil.Coil;
import edu.wschina.a02.R;
import edu.wschina.a02.databinding.ActivityUploadBinding;

public class UploadActivity extends DialogFragment {
	ActivityUploadBinding binding;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@NonNull
	@Override
	public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
		binding = ActivityUploadBinding.inflate(getLayoutInflater());
		AlertDialog dialog = new AlertDialog.Builder(requireActivity())
				.setView(binding.getRoot())
				.setCancelable(true)
				.create();
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		return dialog;
	}
}