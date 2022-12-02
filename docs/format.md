# Supported file format (DRM-or-Default)

## Example for m3u8 Playlist
`#EXTM3U
#EXTINF:-1 tvg-id="" tvg-name="===(( TR SPOR ))===" tvg-logo="" group-title="TR SPOR",===(( TR SPOR ))===
http://server_url/username/password/23640
#EXTINF:-1 tvg-id="" tvg-name="CHANNEL NAME" tvg-logo="http://server_url/images/95235e9c030dde800eaa38740e09457a.png" group-title="TR SPOR",CHANNEL NAME HD
http://server_url/username/password/23679
#EXTINF:-1 tvg-id="tr.TR| " tvg-name="CHANNEL NAME +" tvg-logo="http://server_url/images/95235e9c030dde800eaa38740e09457a.png" group-title="TR SPOR",CHANNEL NAME HD +
http://server_url/username/password/23714
#EXTINF:-1 tvg-id="tr.TR| " tvg-name="CHANNEL NAME 576p" tvg-logo="http://server_url/images/95235e9c030dde800eaa38740e09457a.png" group-title="TR SPOR",CHANNEL NAME 576p
http://server_url/username/password/23715
#EXTINF:-1 tvg-id="tr.TR| " tvg-name="CHANNEL NAME 480p" tvg-logo="http://server_url/images/643776191832652a8c8561810f37f60e.png" group-title="TR SPOR",CHANNEL NAME 480p
http://server_url/username/password/30418
#EXTINF:-1 tvg-id="" tvg-name="===(( YORESEL KANALLAR ))===" tvg-logo="" group-title="TR YEREL",===(( YORESEL KANALLAR ))===
http://server_url/username/password/10724
#EXTINF:-1 tvg-id="" tvg-name="TR: CHANNEL NAME" tvg-logo="" group-title="TR YEREL",TR: CHANNEL NAME
http://server_url/username/password/37730
#EXTINF:-1 tvg-id="" tvg-name="TR: CHANNEL NAME" tvg-logo="" group-title="TR YEREL",TR: CHANNEL NAME
http://server_url/username/password/37735
#EXTINF:-1 tvg-id="" tvg-name="TR: CHANNEL NAME" tvg-logo="" group-title="TR YEREL",TR: CHANNEL NAME
http://server_url/username/password/37731
`

## Example for DRM MPEG-Dash Playlist

` #EXTM3U #KODIPROP:inputstream.adaptive.license_type=com.widevine.alpha #KODIPROP:inputstream.adaptive.license_key=https://proxy.uat.widevine.com/proxy?provider=widevine_test #EXTINF:-1 tvg-logo="https://aaa.-400.png" group-title="TestGroup",AAAA SPORTS https://bitmovin-a.akamaihd.net/content/art-of-motion_drm/mpds/11331.mpd #KODIPROP:inputstream.adaptive.license_type=com.widevine.alpha #KODIPROP:inputstream.adaptive.license_key=https://proxy.uat.widevine.com/proxy?provider=widevine_test #EXTINF:-1 tvg-logo="https://bbb.--400.png" group-title="TestGroup",BBBB SPORTS https://bitmovin-a.akamaihd.net/content/art-of-motion_drm/mpds/11331.mpd #KODIPROP:inputstream.adaptive.license_type=com.widevine.alpha #KODIPROP:inputstream.adaptive.license_key=https://proxy.uat.widevine.com/proxy?provider=widevine_test #EXTINF:-1 tvg-logo="https://ccc.-400.png" group-title="TestGroup",CCC SPORTS https://bitmovin-a.akamaihd.net/content/art-of-motion_drm/mpds/11331.mpd #KODIPROP:inputstream.adaptive.license_type=com.widevine.alpha #KODIPROP:inputstream.adaptive.license_key=https://proxy.uat.widevine.com/proxy?provider=widevine_test #EXTINF:-1 tvg-logo="https://ccc.-400.png" group-title="TestGroup",DDD SPORTS https://bitmovin-a.akamaihd.net/content/art-of-motion_drm/mpds/11331.mpd
`