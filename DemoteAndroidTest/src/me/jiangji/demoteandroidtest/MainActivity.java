package me.jiangji.demoteandroidtest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


class MyAdapter extends BaseAdapter {
	
	private String[] items;
	private LayoutInflater layoutInflater;
	
	public MyAdapter(Context context, String[] results) {
		items = results;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return items.length;
	}

	@Override
	public Object getItem(int position) {
		return items[position];
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.simple_list_item, null);
            holder = new ViewHolder();
            holder.txtFullName = (TextView) convertView.findViewById(R.id.list_item_text);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        
        String strText = (String) getItem(position);

        holder.txtFullName.setText(strText);
        return convertView;
	}
	
	 static class ViewHolder {
	        TextView txtFullName;
	    }
	
}

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_list);


		final ListView lv = (ListView) findViewById(R.id.item_list);
		(new TaskFetchData(1)).execute();

	}

	private class TaskFetchData extends AsyncTask<String, Integer, String[]> {
		private Integer mParam;
		
		public TaskFetchData(Integer param) {
			mParam = param;
		}

		@Override
		protected String[] doInBackground(String... params) {
			String[] result1 = {"Hello", "World"};
			String[] result2 = {"Dont", "Panic"};
			if (mParam == 1) {
				return result1;
			} else {
				return result2;
			}
		}

		@Override
		protected void onPostExecute(String[] result) {
			final ListView lv = (ListView)findViewById(R.id.item_list); 
			lv.setAdapter(new MyAdapter(MainActivity.this, result));
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Toast.makeText(MainActivity.this, "item "+String.valueOf(position)+" is clicked!", Toast.LENGTH_SHORT).show();
					(new TaskFetchData(2)).execute();
				}
			});
		}
		
		

	}

}