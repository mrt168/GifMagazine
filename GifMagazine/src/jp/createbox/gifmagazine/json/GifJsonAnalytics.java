package jp.createbox.gifmagazine.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GifJsonAnalytics {
    /**
     * JSON��͗p�^�O
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
     * Youtube Data api ���X�|���X�́uentry�v�̉�͂��s���܂��B
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

            // �T���l�C��URL 5���邤���̂͂��߂̃f�[�^���g�p
            JSONArray thumbnailArray = mediaGroup.optJSONArray(KEY_THUMBNAIL);
            if (thumbnailArray != null && 0 < thumbnailArray.length()) {
                JSONObject thumbnailInfo = thumbnailArray.optJSONObject(0);
                if (thumbnailInfo != null) {
                    thumbnailUrl = thumbnailInfo.optString(KEY_URL);
                }
            }

            // �Đ�����
            final JSONObject durationObject = mediaGroup.optJSONObject(KEY_DURATION);
            if (durationObject != null) {
                seconds = durationObject.optInt(KEY_SECONDS);
            }
        }


        // �^�C�g��
        final JSONObject titleObject = item.optJSONObject(KEY_TITLE);
        if (titleObject != null) {
            title = titleObject.optString(KEY_$T);
        }


        // �r���[��
        JSONObject viewInfo = item.optJSONObject(KEY_COUNT_INFO);
        if (viewInfo != null) {
            viewCount = viewInfo.optInt(KEY_VIEW_COUNT);
        }

        // �����ˁA�悭�Ȃ����
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
     * ������̉�͂��s���܂��B
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
     * Youtuebe�̌������ʂ���͂��܂��B
     * @param result
     */
    static public List<GifData> analysisYoutubeSearchResultList(JSONObject result) {

        // Youtube�������ʗp���X�g
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