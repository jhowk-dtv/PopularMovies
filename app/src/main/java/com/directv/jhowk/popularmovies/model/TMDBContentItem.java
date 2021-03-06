package com.directv.jhowk.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Vector;

/**
 * Object representing a piece of content in TMDB's system.
 * Created by Jason Howk
 */
@SuppressWarnings("ALL")
public class TMDBContentItem implements Parcelable {
    private static final String LOG_TAG = TMDBContentItem.class.getSimpleName();

    // JSON API Keys
    private static final String POSTER_PATH = "poster_path";
    private static final String ADULT = "adult";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";
    private static final String GENRE_IDS = "genre_ids";
    private static final String ID = "id";
    private static final String ORIGINAL_TITLE = "original_title";
    private static final String ORIGINAL_LANGUAGE = "original_language";
    private static final String TITLE = "title";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String POPULARITY = "popularity";
    private static final String VOTE_COUNT = "vote_count";
    private static final String VIDEO = "video";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String HOMEPAGE = "homepage";
    private static final String RUNTIME = "runtime";
    private static final String TRAILERS = "trailers";
    private static final String REVIEWS = "reviews";

    private JSONObject mJSONObject;
    private String mId;
    private String mTitle;
    private String mOriginalTitle;
    private String mOriginalLanguage;
    private Boolean mAdult;
    private String mOverview;
    private String mPosterPath;
    private Date mReleaseDate;
    private Vector<Integer> mGenreIds;
    private String mBackdropPath;
    private Float mPopularity;
    private Long mVoteCount;
    private Boolean mVideo;
    private Float mVoteAverage;
    private String mHomepage;
    private Integer mRuntime;
    private HashSet<TMDBContentTrailer> mContentTrailers = new HashSet<>();
    private HashSet<TMDBContentReview> mContentReviews = new HashSet<>();

    public TMDBContentItem() {}

    public TMDBContentItem(JSONObject JSONObject) {
        mJSONObject = JSONObject;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            mId = mJSONObject.getString(ID);
            mTitle = mJSONObject.getString(TITLE);
            mOriginalTitle = mJSONObject.getString(ORIGINAL_TITLE);
            mOriginalLanguage = mJSONObject.getString(ORIGINAL_LANGUAGE);
            mPosterPath = mJSONObject.getString(POSTER_PATH);
            mBackdropPath = mJSONObject.getString(BACKDROP_PATH);
            if (mBackdropPath.equalsIgnoreCase("null")) {
                mBackdropPath = null;
            }
            mAdult = mJSONObject.getBoolean(ADULT);
            mOverview = mJSONObject.getString(OVERVIEW);
            mReleaseDate = dateFormat.parse(mJSONObject.getString(RELEASE_DATE), new ParsePosition(0));
            mPopularity = Float.parseFloat(mJSONObject.getString(POPULARITY));
            mVoteCount = mJSONObject.getLong(VOTE_COUNT);
            mVideo = mJSONObject.getBoolean(VIDEO);
            mVoteAverage = Float.parseFloat(mJSONObject.getString(VOTE_AVERAGE));
            mHomepage = mJSONObject.optString(HOMEPAGE);
            mRuntime = mJSONObject.optInt(RUNTIME);
            // TODO: finish genre ids.
//             mGenreIds = mJSONObject.getJSONArray(GENRE_IDS).

            if (mJSONObject.optJSONObject(TRAILERS) != null) {
                JSONArray trailers = mJSONObject.getJSONObject(TRAILERS).optJSONArray("youtube");
                if (trailers != null) {
                    for (int i = 0; i < trailers.length(); i++) {
                        TMDBContentTrailer tmpTrailer = new TMDBContentTrailer(trailers.getJSONObject(i));
                        //Log.d(LOG_TAG, "TMDBContentItem: TRAILER: " + tmpTrailer);
                        mContentTrailers.add(tmpTrailer);
                    }
                }
            }

            if (mJSONObject.optJSONObject(REVIEWS) != null) {
                JSONArray reviews = mJSONObject.getJSONObject(REVIEWS).optJSONArray("results");
                if (reviews != null) {
                    for (int i = 0; i < reviews.length(); i++) {
                        TMDBContentReview tmpReview = new TMDBContentReview(reviews.getJSONObject(i));
                        //Log.d(LOG_TAG, "TMDBContentItem: REVIEW: " + tmpReview);
                        mContentReviews.add(tmpReview);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return String.format("TMDBContentItem[Title:%s]", mTitle);
    }

    ///////////////////////////////////////////////////////////////////////////
    // Properties
    ///////////////////////////////////////////////////////////////////////////

    public JSONObject getJSONObject() {
        return mJSONObject;
    }

    public void setJSONObject(JSONObject JSONObject) {
        mJSONObject = JSONObject;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public Boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        mAdult = adult;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public Date getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        mReleaseDate = releaseDate;
    }

    public Vector<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(Vector<Integer> genreIds) {
        mGenreIds = genreIds;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public Float getPopularity() {
        return mPopularity;
    }

    public void setPopularity(Float popularity) {
        mPopularity = popularity;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(Long voteCount) {
        mVoteCount = voteCount;
    }

    public Boolean getVideo() {
        return mVideo;
    }

    public void setVideo(Boolean video) {
        mVideo = video;
    }

    public Float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getHomepage() {
        return mHomepage;
    }

    public void setHomepage(String homepage) {
        mHomepage = homepage;
    }

    public Integer getRuntime() {
        return mRuntime;
    }

    public void setRuntime(Integer runtime) {
        mRuntime = runtime;
    }

    public HashSet<TMDBContentTrailer> getContentTrailers() {
        return mContentTrailers;
    }

    public void setContentTrailers(HashSet<TMDBContentTrailer> contentTrailers) {
        mContentTrailers = contentTrailers;
    }

    public HashSet<TMDBContentReview> getContentReviews() {
        return mContentReviews;
    }

    public void setContentReviews(HashSet<TMDBContentReview> contentReviews) {
        mContentReviews = contentReviews;
    }


    ///////////////////////////////////////////////////////////////////////////
    // Instance Methods
    ///////////////////////////////////////////////////////////////////////////

    /**
     * Returns a String representing the TMDB page for the item.
     *
     * @return
     */
    public String getItemURL() {
        return String.format("http://themoviedb.org/movie/%s", mId);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Parcelable
    ///////////////////////////////////////////////////////////////////////////


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (this.mJSONObject != null) {
            dest.writeString(this.mJSONObject.toString());
        }
        dest.writeString(this.mId);
        dest.writeString(this.mTitle);
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mOriginalLanguage);
        dest.writeValue(this.mAdult);
        dest.writeString(this.mOverview);
        dest.writeString(this.mPosterPath);
        dest.writeLong(mReleaseDate != null ? mReleaseDate.getTime() : -1);
        //dest.writeList(this.mGenreIds);
        dest.writeString(this.mBackdropPath);
        dest.writeValue(this.mPopularity);
        dest.writeValue(this.mVoteCount);
        dest.writeValue(this.mVideo);
        dest.writeValue(this.mVoteAverage);
        dest.writeString(this.mHomepage);
        dest.writeValue(this.mRuntime);
        dest.writeArray(this.mContentTrailers.toArray());
        dest.writeArray(this.mContentReviews.toArray());
    }

    protected TMDBContentItem(Parcel in) {
        try {
            this.mJSONObject = new JSONObject(in.readString());
        } catch (JSONException e) {
            e.printStackTrace();
            this.mJSONObject = null;
        }
        this.mId = in.readString();
        this.mTitle = in.readString();
        this.mOriginalTitle = in.readString();
        this.mOriginalLanguage = in.readString();
        this.mAdult = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mOverview = in.readString();
        this.mPosterPath = in.readString();
        long tmpMReleaseDate = in.readLong();
        this.mReleaseDate = tmpMReleaseDate == -1 ? null : new Date(tmpMReleaseDate);
        //this.mGenreIds = new ArrayList<Integer>();
        //in.readList(this.mGenreIds, Integer.class.getClassLoader());
        this.mBackdropPath = in.readString();
        this.mPopularity = (Float) in.readValue(Float.class.getClassLoader());
        this.mVoteCount = (Long) in.readValue(Long.class.getClassLoader());
        this.mVideo = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.mVoteAverage = (Float) in.readValue(Float.class.getClassLoader());
        this.mHomepage = in.readString();
        this.mRuntime = (Integer) in.readValue(Integer.class.getClassLoader());
        this.mContentTrailers = new HashSet(Arrays.asList(in.readArray(TMDBContentTrailer.class.getClassLoader())));
        this.mContentReviews = new HashSet(Arrays.asList(in.readArray(TMDBContentReview.class.getClassLoader())));
    }

    public static final Parcelable.Creator<TMDBContentItem> CREATOR = new Parcelable.Creator<TMDBContentItem>() {
        @Override
        public TMDBContentItem createFromParcel(Parcel source) {
            return new TMDBContentItem(source);
        }

        @Override
        public TMDBContentItem[] newArray(int size) {
            return new TMDBContentItem[size];
        }
    };
}
