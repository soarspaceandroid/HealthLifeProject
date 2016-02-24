package com.health.life.utils;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.health.life.R;


/**
 * © 2012 amsoft.cn
 * 名称：AbLoadDialogFragment.java 
 * 描述：弹出加载框
 *
 * @author 还如一梦中
 * @version v1.0
 * @date：2014-07-30 下午16:00:52
 */
public class AbLoadDialogFragment extends DialogFragment {



	/**
	 * Create a new instance of AbDialogFragment, providing "style" as an
	 * argument.
	 */
	public static AbLoadDialogFragment newInstance() {
		AbLoadDialogFragment f = new AbLoadDialogFragment();
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NO_TITLE,R.style.float_progress);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

			View view = LayoutInflater.from(this.getActivity()).inflate(R.layout.loading,null);



		return view;
	}


	
}
