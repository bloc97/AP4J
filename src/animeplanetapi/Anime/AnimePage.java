/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animeplanetapi.Anime;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author bowen
 */
public class AnimePage extends AnimePreview {
    private final String minutesPerEpisode, studioUrl, beginYearUrl, endYearUrl, season, seasonUrl, ratingCount, rank;
    private final AnimeUserStats userStats;
    private final List<AnimePreview> recommendedList;
    
    public AnimePage() {
        this(-1, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", new AnimeUserStats(0, 0, 0, 0, 0, 0), "", "", Arrays.asList(new String[] {"None"}), "", new LinkedList<>());
    }
    
    public AnimePage(int id, String url, String title, String altTitle, String type, String episodes, String minutesPerEpisode, String studio, String studioUrl,
            String beginYear, String endYear, String beginYearUrl, String endYearUrl, String season, String seasonUrl, 
            String rating, String ratingCount, String rank, AnimeUserStats userStats, String description, String source, List<String> tags, String thumbnailUrl, List<AnimePreview> recommendedList) {
        super(id, url, title, altTitle, type, episodes, studio, beginYear, endYear, rating, description, source, tags, thumbnailUrl);
        
        this.minutesPerEpisode = minutesPerEpisode;
        this.studioUrl = studioUrl;
        this.beginYearUrl = beginYearUrl;
        this.endYearUrl = endYearUrl;
        this.season = season;
        this.seasonUrl = seasonUrl;
        this.ratingCount = ratingCount;
        this.rank = rank;
        this.userStats = userStats;
        this.recommendedList = recommendedList;
    }
    
    public boolean hasMinutesPerEpisode() {
        return !getMinutesPerEpisode().equals("N/A");
    }
    public String getMinutesPerEpisode() {
        return minutesPerEpisode;
    }
    public boolean hasStudioUrl() {
        return !getStudioUrl().equals("N/A");
    }
    public String getStudioUrl() {
        return studioUrl;
    }
    public boolean hasBeginYearUrl() {
        return !getBeginYearUrl().equals("N/A");
    }
    public String getBeginYearUrl() {
        return beginYearUrl;
    }
    public boolean hasEndYearUrl() {
        return !getEndYearUrl().equals("N/A");
    }
    public String getEndYearUrl() {
        return endYearUrl;
    }
    public boolean hasSeason() {
        return !getSeason().equals("N/A");
    }
    public String getSeason() {
        return season;
    }
    public boolean hasSeasonUrl() {
        return !getSeasonUrl().equals("N/A");
    }
    public String getSeasonUrl() {
        return seasonUrl;
    }
    
    public boolean hasRatingCount() {
        return !getRatingCount().equals("N/A");
    }
    public String getRatingCount() {
        return ratingCount;
    }
    public boolean hasRank() {
        return !getRank().equals("N/A");
    }
    public String getRank() {
        return rank;
    }

    public AnimeUserStats getUserStats() {
        return userStats;
    }

    public List<AnimePreview> getRecommendedList() {
        return recommendedList;
    }
    
    
    @Override
    public String toString() {
        return getId() + " " + getTitle() + " (" + getAltTitle() + ")\n" +
               getUrl() + "\n" +
               getType() + ", " + getEpisodes() + " e" + ", " + getMinutesPerEpisode() + " min/e" + ", " + getStudio() + ", " + (hasMultipleYears() ? getBeginYear() + " - " + getEndYear() : getBeginYear()) + ", " + getRating() + "/5" + "\n" +
               getDescription() + " (" + getDescriptionSource() + ")\n" +
               getTags() + "\n" +
               getThumbnailUrl() + "\n" +
               studioUrl + "\n" +
               beginYearUrl + "\n" +
               endYearUrl + "\n" +
               season + "\n" +
               seasonUrl + "\n" +
               ratingCount + "\n" +
               rank + "\n" +
               getUserStats() + "\n";
    }
    
}
