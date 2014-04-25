package jp.createbox.gifmagazine.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GifJsonAnalytics {
    /**
     * JSON解析用タグ
     */
    final static private String KEY_FEED = "feed";
    final static private String KEY_ENTRY = "entry";
    final static private String KEY_MEDIA_GROUP = "media$group";
    final static private String KEY_VIDEO_ID = "yt$videoid";
    final static private String KEY_TITLE = "title";
    final static private String KEY_DURATION = "yt$duration";
    final static private String KEY_SECONDS = "seconds";
    final static private String KEY_THUMBNAIL = "media$thumbnail";
    final static private String KEY_URL = "url";
    final static private String KEY_COUNT_INFO = "yt$statistics";
    final static private String KEY_VIEW_COUNT = "viewCount";
    final static private String KEY_$T = "$t";
    final static private String KEY_RAITING_INFO = "yt$rating";
    final static private String KEY_RAITING_LIKE = "numLikes";
    final static private String KEY_RAITING_DISLIKE = "numDislikes";

    static final private String KEY_RANKING_LIST = "rankingList";

    /**
     * Youtube Data api レスポンスの「entry」の解析を行います。
     * @param item
     * @return
     */
    static private GifData analysisEntry(JSONObject item) {

        String title = null;
        String videoId = null;
        String thumbnailUrl = null;
        int seconds = 0;
        int likeCount = 0;
        int dislikeCount = 0;
        int viewCount = 0;


        JSONObject mediaGroup = item.optJSONObject(KEY_MEDIA_GROUP);
        if (mediaGroup != null) {
            JSONObject videoIdObject = mediaGroup.optJSONObject(KEY_VIDEO_ID);
            if (videoIdObject != null) {
                videoId = videoIdObject.optString(KEY_$T);
            }

            // サムネイルURL 5つあるうちのはじめのデータを使用
            JSONArray thumbnailArray = mediaGroup.optJSONArray(KEY_THUMBNAIL);
            if (thumbnailArray != null && 0 < thumbnailArray.length()) {
                JSONObject thumbnailInfo = thumbnailArray.optJSONObject(0);
                if (thumbnailInfo != null) {
                    thumbnailUrl = thumbnailInfo.optString(KEY_URL);
                }
            }

            // 再生時間
            final JSONObject durationObject = mediaGroup.optJSONObject(KEY_DURATION);
            if (durationObject != null) {
                seconds = durationObject.optInt(KEY_SECONDS);
            }
        }


        // タイトル
        final JSONObject titleObject = item.optJSONObject(KEY_TITLE);
        if (titleObject != null) {
            title = titleObject.optString(KEY_$T);
        }


        // ビュー数
        JSONObject viewInfo = item.optJSONObject(KEY_COUNT_INFO);
        if (viewInfo != null) {
            viewCount = viewInfo.optInt(KEY_VIEW_COUNT);
        }

        // いいね、よくない情報
        JSONObject ratingInfo = item.optJSONObject(KEY_RAITING_INFO);
        if (ratingInfo != null) {
            likeCount = ratingInfo.optInt(KEY_RAITING_LIKE);
            dislikeCount = ratingInfo.optInt(KEY_RAITING_DISLIKE);
        }

        GifData entry = new GifData(videoId, title,
                thumbnailUrl);

        return entry;
    }

    /**
     * 動画情報の解析を行います。
     * @param result
     */
    static public GifData analysisYoutubeSearchResult(JSONObject result) {

        if (result == null) {
            return null;
        }

        JSONObject entry = result.optJSONObject(KEY_ENTRY);

        if (entry == null || entry.length() == 0) {
            return null;
        }

        return analysisEntry(entry);
    }

    /**
     * Youtuebeの検索結果を解析します。
     * @param result
     */
    static public List<GifData> analysisYoutubeSearchResultList(JSONObject result) {

        // Youtube検索結果用リスト
        List<GifData> resultList = new ArrayList<GifData>();

        if (result == null) {
            return resultList;
        }

        JSONObject feed = result.optJSONObject(KEY_FEED);

        if (feed == null) {
            return resultList;
        }

        JSONArray entry = feed.optJSONArray(KEY_ENTRY);

        if (entry == null || entry.length() == 0) {
            return resultList;
        }


        for (int i = 0; i < entry.length(); i++) {

            final JSONObject item = entry.optJSONObject(i);
            if (item == null) {
                continue;
            }

            resultList.add(analysisEntry(item));
        }

        return resultList;
    }
}