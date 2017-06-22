package ExternalClasses;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.yasser.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by lenovo on 20/06/2017.
 */

public class MyListAdapter extends BaseAdapter {
    ArrayList<Item> mylist=new ArrayList<>();
    Context context;
    public MyListAdapter(Context context,ArrayList<Item> mylist) {
        this.context=context;
        this.mylist=mylist;
    }

    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int position) {
        return mylist.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.list_item,null);

        TextView title=(TextView)view.findViewById(R.id.title);
        TextView published=(TextView)view.findViewById(R.id.publised_date);
        ImageView imageView=(ImageView)view.findViewById(R.id.image_view);

        title.setText(mylist.get(position).getTitle());
        published.setText(mylist.get(position).getPublished_date());

        if(mylist.get(position).getUrl()!="") {
            Picasso.with(context).load(mylist.get(position).getUrl())
                    .placeholder(R.drawable.feature_error)
                    .error(R.drawable.feature_error)
                    .into(imageView);
        }
        else {
            Picasso.with(context).load(R.drawable.feature_error)

                    .into(imageView);
        }
        return view;

    }
}
