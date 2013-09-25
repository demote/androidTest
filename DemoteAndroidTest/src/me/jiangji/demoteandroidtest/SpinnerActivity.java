package me.jiangji.demoteandroidtest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends Activity {

	private static final String[] items = { "hello", "world", "Dont", "panic" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_spinner);

		Spinner mySpinner = (Spinner) findViewById(R.id.simple_spinner);
		// ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
		// R.layout.simple_list_item,items);
		MySpinnerAdapter spinnerAdapter = new MySpinnerAdapter(this, R.layout.simple_list_item,
						items);

		mySpinner.setAdapter(spinnerAdapter);
	}

	private class MySpinnerAdapter extends ArrayAdapter<String> {

		public MySpinnerAdapter(Context context, int textViewResourceId, String[] objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView v = (TextView) super.getView(position, convertView, parent);
			v.setTextColor(Color.BLUE);
			return v;
		}

		@Override
		public View getDropDownView(int position, View convertView, ViewGroup parent) {
			Typeface myFont = Typeface.createFromAsset(getAssets(), "fonts/Igualb.ttf");
			TextView v = (TextView) super.getDropDownView(position, convertView, parent);
			v.setTextColor(Color.RED);
			v.setTypeface(myFont);
			return v;
		}

	}

}
