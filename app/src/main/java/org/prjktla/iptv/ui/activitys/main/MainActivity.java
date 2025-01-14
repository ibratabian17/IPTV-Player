package org.prjktla.iptv.ui.activitys.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.prjktla.iptv.R;
import org.prjktla.iptv.adapters.SearchAdapter;
import org.prjktla.iptv.database.IPTvRealm;
import org.prjktla.iptv.databinding.ActivityMainBinding;
import org.prjktla.iptv.models.Channel;
import org.prjktla.iptv.ui.activitys.player.PlayerActivity;
import org.prjktla.iptv.ui.fragments.about.AboutFragment;
import org.prjktla.iptv.ui.fragments.favorite.FavoriteFragment;
import org.prjktla.iptv.ui.fragments.home.CategoriesFragment;
import org.prjktla.iptv.utils.ChannelOnClick;
import org.prjktla.iptv.utils.Helper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    private ActivityMainBinding binding;
    private IPTvRealm ipTvRealm;

    private final ChannelOnClick channelOnClick = channel -> {
        Intent intent = new Intent(MainActivity.this, PlayerActivity.class);
        intent.putExtra(Helper.CHANNEL, channel);
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialize();
    }

    private void initialize() {
        ipTvRealm = new IPTvRealm();
        binding.mainChannelSize.setText(String.format(getString(R.string.channel_count), ipTvRealm.allChannelCount()));
        BottomNavigationView bottomNavigationView = binding.bottomNavigationView;
        openFragment(CategoriesFragment.newInstance());
        bottomNavigationView.setOnItemSelectedListener(this);

        binding.mainSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() != 0) {
                    searchSet(s);
                } else {
                    searchLinearGone();
                }
                return false;
            }
        });

    }

    private void searchSet(String searchKey) {
        List<Channel> channels = ipTvRealm.searchChannel(searchKey);
        if (channels != null && channels.size() > 0) {
            binding.searchRecycler
                    .setAdapter(new SearchAdapter(MainActivity.this, channels, channelOnClick));
            binding.searchRecycler.setLayoutManager(new LinearLayoutManager(this));
            searchLinearVisible();
        }
    }

    private void searchLinearGone() {
        binding.searchLinear.setVisibility(View.GONE);
    }

    private void searchLinearVisible() {
        binding.searchLinear.setVisibility(View.VISIBLE);
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(binding.bottomFrameLayout.getId(), fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mainMenu) {
            openFragment(CategoriesFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.favoriteMenu) {
            openFragment(FavoriteFragment.newInstance());
            return true;
        } else if (item.getItemId() == R.id.aboutMenu) {
            openFragment(AboutFragment.newInstance());
            return true;
        }

        return false;
    }
}