package about;

import imageDownloader.UrlImageViewHelper;
import tai.rapidconsultingusa.rapidSuiteNative.R;

import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AboutFragment extends ListFragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup, Bundle savedInstanceState)
	{


		//	 setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, List));
		View view = inflater.inflate(R.layout.custom_list_layout, null);
		ListView lv = (ListView)view.findViewById(android.R.id.list);

		String[] about_info =  view.getResources().getStringArray(R.array.about_info);

		
	//	View footer = inflater.inflate(R.layout.about_footer_view_layout, null);
	//	lv.addFooterView(footer);
	//	lv.setFooterDividersEnabled(false);
		
		lv.setAdapter(new AboutInfoListAdapter(getActivity(), about_info));

		return view;
	}


	private class AboutInfoListAdapter extends BaseAdapter
	{
		private Context context;
		private String[] about_info;
		private LayoutInflater mInflater;

		private static final int NUMBER_OF_VIEW_TYPE = 2;

		public AboutInfoListAdapter (Context context, String[] about_info)
		{
			this.context = context;
			this.about_info = about_info;
			mInflater = LayoutInflater.from (context);
		}


		public int getCount()
		{
			// TODO Auto-generated method stub
			return about_info.length;
		}

		public Object getItem(int position)
		{
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int position)
		{
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getViewTypeCount()
		{
			return NUMBER_OF_VIEW_TYPE;
		}

		@Override
		public int getItemViewType(int position)
		{
			return BaseAdapter.IGNORE_ITEM_VIEW_TYPE;
		}


		public View getView(int position, View convertView, ViewGroup parent)
		{
			// TODO Auto-generated method stub
			ViewHolder holder;
			if (convertView == null)
			{
				convertView = mInflater.inflate(R.layout.about_row_view, null);

				holder = new ViewHolder ((TextView) convertView.findViewById(R.id.textView_about_field),
						(TextView) convertView.findViewById(R.id.textView_about_value),
						(Drawable) context.getResources().getDrawable(R.drawable.rounded_corner_top),
						(Drawable) context.getResources().getDrawable(R.drawable.rounded_corner_bottom),
						(Drawable) context.getResources().getDrawable(R.drawable.rounded_corner));

				convertView.setTag(holder);
			}
			else
				holder = (ViewHolder) convertView.getTag();

			holder.about_field.setText(about_info[position]);

			Intent intent = null;
			switch(position)
			{
			case 0:	//App Name
				holder.about_value.setText(APP_NAME);
				convertView.setBackgroundDrawable(holder.top_corner);
				break;
			case 1:	//App Version
				holder.about_value.setText(APP_VERSION);
				break;
			case 2:	// Release Date
				holder.about_value.setText(RELEASE_DATE);
				break;
			case 3:	// Developed By
				holder.about_value.setText(DEVELOPED_BY);
				convertView.setBackgroundDrawable(holder.bottom_corner);
				break;

			case 5:	// Developed By
				holder.about_value.setText(DESCRIPTION);
				convertView.setBackgroundDrawable(holder.all_corner);
				break;
			case 7:	// Contact
				holder.about_value.setText(CONTACT);
				convertView.setBackgroundDrawable(holder.all_corner);
				
				intent = new Intent (android.content.Intent.ACTION_CHOOSER);

				Intent send_intent = new Intent (Intent.ACTION_SEND);
				send_intent.setType("plain/text");

				send_intent.putExtra(Intent.EXTRA_EMAIL, new String[]{CONTACT});
				intent.putExtra(android.content.Intent.EXTRA_INTENT, send_intent);
				
				break;

			default:
				convertView = mInflater.inflate (R.layout.separator_layout, null);
				convertView.setVisibility (View.INVISIBLE);
				break;
				
			}

			if (intent != null)
				convertView.setOnClickListener(new ContactOnClickListener(intent, context));


			return convertView;
		}


	}
	
	private class ContactOnClickListener implements OnClickListener
	{
		private Intent intent;

		public ContactOnClickListener (Intent intent, Context context)
		{
			this.intent = intent;
		}

		public void onClick(View v) 
		{
			startActivity(intent);

		}

	}

	static class ViewHolder
	{
		private TextView about_field;
		private TextView about_value;
		private Drawable top_corner;
		private Drawable bottom_corner;
		private Drawable all_corner;


		public ViewHolder (TextView about_field, TextView about_value, 
				Drawable top_corner, Drawable bottom_corner, 
				Drawable all_corner)
		{
			this.about_field = about_field;
			this.about_value = about_value;
			this.all_corner = all_corner;
			this.top_corner = top_corner;
			this.bottom_corner = bottom_corner;
		}
	}


	private static final String APP_NAME = "RapidSuite";
	private static final String APP_VERSION = "1.0";
	private static final String RELEASE_DATE = "03-23-2012";
	private static final String DEVELOPED_BY = "Rapid Consulting Services";
	private static final String DESCRIPTION = "Executive users are able to browse " +
			"the Contacts' information, check inventory, approve or reject requests, " +
			"view approval history, and monitor various sales reports within one application.";

	private static final String CONTACT = "sales@rapidconsultingusa.com";


}
