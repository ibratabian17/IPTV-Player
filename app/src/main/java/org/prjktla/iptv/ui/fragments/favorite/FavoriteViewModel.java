package org.prjktla.iptv.ui.fragments.favorite;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.prjktla.iptv.database.IPTvRealm;
import org.prjktla.iptv.models.Channel;

import java.util.List;

public class FavoriteViewModel extends ViewModel {
    private final MutableLiveData<List<Channel>> listFavoriteLiveData;
    private final IPTvRealm ipTvRealm;

    public FavoriteViewModel() {
        this.listFavoriteLiveData = new MutableLiveData<>();
        ipTvRealm = new IPTvRealm();
        setLiveData();
    }

    private void setLiveData() {
        List<Channel> channelList = ipTvRealm.getFavoriteList();
        if (channelList != null) {
            listFavoriteLiveData.setValue(channelList);
        }
    }

    public MutableLiveData<List<Channel>> getFavoriteLiveData() {
        return listFavoriteLiveData;
    }

    public void updateFavorite() {
        List<Channel> channelList = ipTvRealm.getFavoriteList();
        if (channelList != null) {
            listFavoriteLiveData.setValue(channelList);
        }
    }

}