package org.prjktla.iptv.database;

import org.prjktla.iptv.models.Channel;

import java.util.List;
import java.util.stream.Collectors;

import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class IPTvRealm {

    private Realm realm;

    public IPTvRealm() {

    }

    private void ipTvListInstance() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("iptv_list.realm")
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .build();

        realm = Realm.getInstance(config);
    }

    private void favoriteInstance() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("iptv_favorite.realm")
                .allowWritesOnUiThread(true)
                .allowQueriesOnUiThread(true)
                .build();
        realm = Realm.getInstance(config);
    }

    private Realm ipTvListInstanceRealm() {
        ipTvListInstance();
        return this.realm;
    }

    private Realm ipTvFavoriteInstanceRealm() {
        favoriteInstance();
        return this.realm;
    }

    public boolean channelListSave(List<Channel> channelList) {
        realm = ipTvListInstanceRealm();
        realm.beginTransaction();
        for (Channel chl : channelList) {
            Channel channel = realm.createObject(Channel.class);
            channel.setChannelName(chl.getChannelName());
            channel.setChannelImg(chl.getChannelImg());
            channel.setChannelUrl(chl.getChannelUrl());
            channel.setChannelGroup(chl.getChannelGroup());
            channel.setChannelDrmKey(chl.getChannelDrmKey());
            channel.setChannelDrmType(chl.getChannelDrmType());
        }
        realm.commitTransaction();
        return true;
    }

    public List<Channel> getAllChannelList() {
        realm = ipTvListInstanceRealm();
        realm.beginTransaction();
        List<Channel> channelList = realm.where(Channel.class).findAll();
        realm.commitTransaction();
        return channelList;
    }

    public List<String> getCategories() {
        realm = ipTvListInstanceRealm();
        realm.beginTransaction();
        RealmResults<Channel> categoriesQ = realm.where(Channel.class)
                .distinct("channelGroup").findAll();
        realm.commitTransaction();
        return categoriesQ.stream().distinct().map(Channel::getChannelGroup).collect(Collectors.toList());
    }

    public List<Channel> getCategoriesChannel(String category) {
        realm = ipTvListInstanceRealm();
        realm.beginTransaction();
        RealmResults<Channel> channels = realm.where(Channel.class)
                .equalTo("channelGroup", category).findAll();
        realm.commitTransaction();
        return channels;
    }

    public List<Channel> searchChannel(String searchKey) {
        realm = ipTvListInstanceRealm();
        realm.beginTransaction();
        RealmResults<Channel> channels = realm.where(Channel.class)
                .contains("channelName", searchKey, Case.INSENSITIVE)
                .limit(5)
                .findAll();
        realm.commitTransaction();
        return channels;
    }

    public long allChannelCount() {
        realm = ipTvListInstanceRealm();
        realm.beginTransaction();
        long count = realm.where(Channel.class).count();
        realm.commitTransaction();
        return count;
    }

    /***@implNote Favorite System*/
    public boolean isFavorite(String channelName) {
        realm = ipTvFavoriteInstanceRealm();
        realm.beginTransaction();
        Channel result = realm.where(Channel.class).equalTo("channelName", channelName)
                .findFirst();
        realm.commitTransaction();
        return result != null;
    }

    public boolean setFavorite(Channel chl) {
        realm = ipTvFavoriteInstanceRealm();
        realm.beginTransaction();
        Channel channel = realm.createObject(Channel.class);
        channel.setChannelName(chl.getChannelName());
        channel.setChannelImg(chl.getChannelImg());
        channel.setChannelUrl(chl.getChannelUrl());
        channel.setChannelGroup(chl.getChannelGroup());
        channel.setChannelDrmKey(chl.getChannelDrmKey());
        channel.setChannelDrmType(chl.getChannelDrmType());
        realm.commitTransaction();
        return true;
    }

    public boolean deleteFavorite(Channel channel) {
        realm = ipTvFavoriteInstanceRealm();
        realm.beginTransaction();
        boolean result = realm.where(Channel.class).equalTo("channelName", channel.getChannelName())
                .findAll().deleteFirstFromRealm();
        realm.commitTransaction();
        return result;
    }

    public List<Channel> getFavoriteList() {
        realm = ipTvFavoriteInstanceRealm();
        realm.beginTransaction();
        RealmResults<Channel> channel = realm.where(Channel.class).findAll();
        realm.commitTransaction();
        return channel;
    }
}

