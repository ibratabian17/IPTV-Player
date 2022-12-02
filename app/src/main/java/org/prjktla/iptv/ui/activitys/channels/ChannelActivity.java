package org.prjktla.iptv.ui.activitys.channels;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.prjktla.iptv.adapters.ChannelListAdapter;
import org.prjktla.iptv.databinding.ActivityChannelBinding;
import org.prjktla.iptv.ui.activitys.player.PlayerActivity;
import org.prjktla.iptv.utils.ChannelOnClick;
import org.prjktla.iptv.utils.Helper;

public class ChannelActivity extends AppCompatActivity {

    private ActivityChannelBinding binding;
    private ChannelViewModel channelViewModel;
    private String category;
    private final ChannelOnClick channelOnClick = channel -> {
        Intent intent = new Intent(ChannelActivity.this, PlayerActivity.class);
        intent.putExtra(Helper.CHANNEL, channel);
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        category = getIntent().getStringExtra(Helper.CATEGORY);
        if (category != null && !category.equals("")) {
            channelViewModel = new ViewModelProvider(this, new ChannelViewFactory(getApplication(), category))
                    .get(ChannelViewModel.class);
        }
        binding = ActivityChannelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
    }

    private void initialize() {
        Helper.getToolbarStyle(this, binding.channelListToolbar, category);
        setFavoriteData();
    }

    private void setFavoriteData() {
        channelViewModel.getChannelLiveData().observe(this, channelList -> {
            binding.channelRecycler.setAdapter(new ChannelListAdapter(ChannelActivity.this, channelList, channelOnClick));
            binding.channelRecycler.setLayoutManager(new LinearLayoutManager(ChannelActivity.this));
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}