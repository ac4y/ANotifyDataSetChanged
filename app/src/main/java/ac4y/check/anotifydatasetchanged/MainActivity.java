package ac4y.check.anotifydatasetchanged;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private ListView mCompleteListView;
    private Button mAddItemToList;
    private List<String> mItems;
    private CompleteListAdapter mListAdapter;
    private static final int MIN = 0, MAX = 10000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_list);
        initViews();
        mItems = new ArrayList<String>();
        mListAdapter = new CompleteListAdapter(this, mItems);
        mCompleteListView.setAdapter(mListAdapter);
    }
    private void initViews() {
        mCompleteListView = (ListView) findViewById(R.id.completeList);
        mAddItemToList = (Button) findViewById(R.id.addItemToList);
        mAddItemToList.setOnClickListener(this);
    }
    private void addItemsToList() {
        int randomVal = MIN + (int) (Math.random() * ((MAX - MIN) + 1));
        mItems.add(String.valueOf(randomVal));
        mListAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addItemToList:
                addItemsToList();
                break;
        }
    }



    public class CompleteListAdapter extends BaseAdapter {
        private Activity mContext;
        private List<String> mList;
        private LayoutInflater mLayoutInflater = null;
        public CompleteListAdapter(Activity context, List<String> list) {
            mContext = context;
            mList = list;
            mLayoutInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {
            return mList.size();
        }
        @Override
        public Object getItem(int pos) {
            return mList.get(pos);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            CompleteListViewHolder viewHolder;
            if (convertView == null) {
                LayoutInflater li = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = li.inflate(R.layout.list_layout, null);
                viewHolder = new CompleteListViewHolder(v);
                v.setTag(viewHolder);
            } else {
                viewHolder = (CompleteListViewHolder) v.getTag();
            }
            viewHolder.mTVItem.setText(mList.get(position));
            return v;
        }
    }

    class CompleteListViewHolder {
        public TextView mTVItem;
        public CompleteListViewHolder(View base) {
            mTVItem = (TextView) base.findViewById(R.id.listTV);
        }
    }


}

