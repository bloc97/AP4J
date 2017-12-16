/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animeplanetapi.Anime;

import java.util.List;

/**
 *
 * @author bowen
 */
public class AnimePreview {
    private final int id;
    private final String url, title, altTitle, type, episodes, studio, beginYear, endYear, rating, userRating, description, source, thumb;
    private final UserAnimeSearchFilter.StatusType status;
    private final List<String> tags;
    
    public AnimePreview(int id, String url, String title, String altTitle, String type, String episodes, String studio, String beginYear, String endYear, String rating, String description, String source, List<String> tags, String thumbnailUrl) {
        this(id, url, title, altTitle, type, episodes, studio, beginYear, endYear, rating, "N/A", UserAnimeSearchFilter.StatusType.ALL, description, source, tags, thumbnailUrl);
    }
    public AnimePreview(int id, String url, String title, String altTitle, String type, String episodes, String studio, String beginYear, String endYear, String rating, String userRating, UserAnimeSearchFilter.StatusType status, String description, String source, List<String> tags, String thumbnailUrl) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.altTitle = altTitle;
        this.type = type;
        this.episodes = episodes;
        this.studio = studio;
        this.beginYear = beginYear;
        this.endYear = endYear;
        this.rating = rating;
        this.userRating = userRating;
        this.status = status;
        this.description = description;
        if (source != null) {
            this.source = source;
        } else {
            this.source = "";
        }
        this.tags = tags;
        this.thumb = thumbnailUrl;
    }

    public int getId() {
        return id;
    }
    public String getUrl() {
        return url;
    }
    public String getTitle() {
        return title;
    }
    
    public boolean hasAltTitle() {
        return !getAltTitle().isEmpty();
    }
    public String getAltTitle() {
        return altTitle;
    }
    
    public String getType() {
        return type;
    }
    public boolean hasEpisodes() {
        return !getEpisodes().equals("N/A");
    }
    public String getEpisodes() {
        return episodes;
    }
    
    public boolean hasStudio() {
        return !getStudio().equals("N/A");
    }
    public String getStudio() {
        return studio;
    }
    
    public String getBeginYear() {
        return beginYear;
    }
    public String getEndYear() {
        return endYear;
    }
    public boolean hasMultipleYears() {
        return !getBeginYear().equals(getEndYear());
    }
    public boolean isOngoing() {
        return getEpisodes().contains("+") || (hasMultipleYears() && getEndYear().equals("?"));
    }
    public boolean isTBA() {
        return getBeginYear().equals("TBA") || !hasEpisodes();
    }
    
    public boolean hasRating() {
        return !getRating().equals("N/A");
    }
    public String getRating() {
        return rating;
    }
    
    public boolean hasUserRating() {
        return !getUserRating().equals("N/A");
    }
    public String getUserRating() {
        return userRating;
    }
    
    public boolean hasUserStatus() {
        return status != UserAnimeSearchFilter.StatusType.ALL;
    }
    
    public UserAnimeSearchFilter.StatusType getUserStatus() {
        return status;
    }
    
    public boolean hasDescription() {
        return !(getDescription().contains("Check back soon!") || getDescription().equals("N/A"));
    }
    public String getDescription() {
        return description;
    }
    
    public boolean hasDescriptionSource() {
        return !getDescriptionSource().equals("N/A");
    }
    public String getDescriptionSource() {
        return source;
    }
    
    public boolean hasTags() {
        return !(getTags().isEmpty() || (getTags().size() == 1 && getTags().get(0).equalsIgnoreCase("None")) || getTags().contains("None"));
    }
    public List<String> getTags() {
        return tags;
    }
    
    public boolean hasThumbnail() {
        return !(getThumbnailUrl().contains("blank_main.jpg") || getThumbnailUrl().contains("card-load.gif"));
    }
    public String getThumbnailUrl() {
        return thumb;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnimePreview) {
            AnimePreview preview = (AnimePreview) obj;
            if (getId() == preview.getId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
        return hash;
    }
    
    @Override
    public String toString() {
        return getId() + " " + getTitle() + " (" + getAltTitle() + ")\n" +
               getUrl() + "\n" +
               getType() + ", " + getEpisodes() + " ep" + ", " + getStudio() + ", " + (hasMultipleYears() ? getBeginYear() + " - " + getEndYear() : getBeginYear()) + ", " + getRating() + "/5" + "\n" +
               getDescription() + " (" + getDescriptionSource() + ")\n" +
               getTags() + "\n" +
               getThumbnailUrl();
    }
}
