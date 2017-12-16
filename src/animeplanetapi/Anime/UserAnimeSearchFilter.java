/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animeplanetapi.Anime;

import animeplanetapi.Tag;
import animeplanetapi.Levenshtein;
import animeplanetapi.Parsers;
import animeplanetapi.SearchFilter;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author bowen
 */
public class UserAnimeSearchFilter implements SearchFilter {
    
    
    public static enum StatusType {
        ALL, WATCHED, WATCHING, WANT_TO_WATCH, STALLED, DROPPED, WONT_WATCH
    }
    private static String getStatusType(StatusType type) {
        switch (type) {
            case ALL:
                return "";
            case WATCHED:
                return "/watched";
            case WATCHING:
                return "/watching";
            case WANT_TO_WATCH:
                return "/wanttowatch";
            case STALLED:
                return "/stalled";
            case DROPPED:
                return "/dropped";
            case WONT_WATCH:
                return "/wontwatch";
            default:
                return "";
        }
    }
    public static StatusType getStatusType(String statusType) {
        switch (statusType) {
            case "status1":
                return StatusType.WATCHED;
            case "status2":
                return StatusType.WATCHING;
            case "status3":
                return StatusType.DROPPED;
            case "status4":
                return StatusType.WANT_TO_WATCH;
            case "status5":
                return StatusType.STALLED;
            case "status6":
                return StatusType.WONT_WATCH;
            default:
                return StatusType.ALL;
        }
    }
    
    
    public static enum SortType {
        POPULAR, NAME, YEAR, MOST_WATCHED, MOST_WATCHING, RATING, RECENTLY_UPDATED
    }
    
    public static enum SortOrder {
        ASCENDING, DESCENDING
    }
    
    private static String getSortType(SortType type) {
        switch (type) {
            case NAME:
                return "title";
            case YEAR:
                return "year";
            case POPULAR:
                return "average";
            case MOST_WATCHED:
                return "status_1";
            case MOST_WATCHING:
                return "status_2";
            case RATING:
                return "rating";
            case RECENTLY_UPDATED:
                return "user_updated";
            default:
                return "";
        }
    }
    private static SortOrder getDefaultSortOrder(SortType type) {
        switch (type) {
            case NAME:
                return SortOrder.ASCENDING;
            default:
                return SortOrder.DESCENDING;
        }
    }
    private static String getSortOrder(SortOrder order) {
        switch (order) {
            case ASCENDING:
                return "asc";
            case DESCENDING:
                return "desc";
            default:
                return "";
        }
    }
    
    public static List<Tag> tagsList = new LinkedList<>();
    
    public static Tag searchTag(String tagName) {
        if (tagsList.isEmpty()) {
            tagsList = AnimeSearchers.fetchAnimeTags();
        }
        
        Tag bestTag = new Tag("None", "None", "None");
        int score = Integer.MAX_VALUE;
        
        for (Tag tag : tagsList) {
            int currentScore = Levenshtein.subwordDistance(tag.getName().toLowerCase(), tagName.toLowerCase());
            if (score > currentScore) {
                score = currentScore;
                bestTag = tag;
            }
            if (tag.getName().equalsIgnoreCase(tagName)) {
                return tag;
            }
        }
        return bestTag;
    }
    
    public static enum Type {
        DVD_SPECIAL, MOVIE, MUSIC_VIDEO, OTHER, OVA, TV, TV_SPECIAL, WEB
    }
    public static String getType(Type type) {
        switch (type) {
            case DVD_SPECIAL:
                return "8";
            case MOVIE:
                return "2";
            case MUSIC_VIDEO:
                return "7";
            case OTHER:
                return "4";
            case OVA:
                return "1";
            case TV:
                return "6";
            case TV_SPECIAL:
                return "3";
            case WEB:
                return "5";
            default:
                return "6";
                
        }
    }
    
    public static enum Season {
        SPRING, SUMMER, FALL, WINTER
    }
    
    public static enum Other {
        COMPLETED, ONGOING, UNAIRED, NO_SCREENSHOTS, ENGLISH_DUBBED, NO_SYNOPSIS, MARKED_WITH_NO_RECS
    }
    public static String getOther(Other other) {
        switch (other) {
            case COMPLETED:
                return "is_completed=1";
            case ONGOING:
                return "is_ongoing=1";
            case UNAIRED:
                return "is_unaired=1";
            case NO_SCREENSHOTS:
                return "no_screens=1";
            case ENGLISH_DUBBED:
                return "en_dub=1";
            case NO_SYNOPSIS:
                return "no_description=1";
            case MARKED_WITH_NO_RECS:
                return "no_recs=1";
            default:
                return "";
                
        }
    }
    private final String userName;
    private String filterType = "";
    
    private String sortType = "";
    private String sortOrder = "";
    
    private String nameFilter = "";
    private String minRatingFilter = "";
    private String maxRatingFilter = "";
    private String studioFilter = "";
    private String typeFilter = "";
    private String tagsFilter = "";
    private String minEpisodesFilter = "";
    private String maxEpisodesFilter = "";
    private String minYearFilter = "";
    private String maxYearFilter = "";
    private String seasonFilter = "";
    private String otherFilter = "";
    
    private String pageFilter = "";
    
    private boolean isPageRange = false;
    private int minPage = 0;
    private int maxPage = 0;
    
    public UserAnimeSearchFilter(String userName) {
        this.userName = userName;
    }
    
    
    public UserAnimeSearchFilter setStatusType(StatusType type) {
        filterType = getStatusType(type);
        return this;
    }
    public UserAnimeSearchFilter clearStatusType() {
        filterType = "";
        return this;
    }
    
    
    public UserAnimeSearchFilter setSortType(SortType type) {
        sortType = "sort=" + getSortType(type);
        setSortOrder(getDefaultSortOrder(type));
        return this;
    }
    public UserAnimeSearchFilter setSortOrder(SortOrder order) {
        sortOrder = "order=" + getSortOrder(order);
        return this;
    }
    public UserAnimeSearchFilter clearSort() {
        sortType = "";
        sortOrder = "";
        return this;
    }
    
    
    public UserAnimeSearchFilter setName(String name) {
        nameFilter = "name=" + name;
        return this;
    }
    public UserAnimeSearchFilter clearName() {
        nameFilter = "";
        return this;
    }
    
    public UserAnimeSearchFilter setMinRating(String min) {
        minRatingFilter = "rating_greater=" + min;
        return this;
    }
    public UserAnimeSearchFilter setMaxRating(String max) {
        maxRatingFilter = "rating_less=" + max;
        return this;
    }
    public UserAnimeSearchFilter setRating(String min, String max) {
        setMinRating(min);
        setMaxRating(max);
        return this;
    }
    public UserAnimeSearchFilter clearRating() {
        minRatingFilter = "";
        maxRatingFilter = "";
        return this;
    }
    
    public UserAnimeSearchFilter setStudio(String studioName) {
        studioFilter = "studio=" + studioName;
        return this;
    }
    public UserAnimeSearchFilter clearStudio() {
        studioFilter = "";
        return this;
    }
    
    public UserAnimeSearchFilter addType(Type type) {
        if (typeFilter.isEmpty()) {
            typeFilter = "include_types=";
        }
        typeFilter += getType(type) + ",";
        return this;
    }
    public UserAnimeSearchFilter clearType() {
        typeFilter = "";
        return this;
    }
    
    public UserAnimeSearchFilter addTag(String tagName) {
        if (tagsFilter.isEmpty()) {
            tagsFilter = "include_tags=";
        }
        tagsFilter += searchTag(tagName).getId() + ",";
        return this;
    }
    public UserAnimeSearchFilter clearTags() {
        tagsFilter = "";
        return this;
    }
    
    
    public UserAnimeSearchFilter setMinEpisodes(String min) {
        minEpisodesFilter = "episodes=" + min;
        return this;
    }
    public UserAnimeSearchFilter setMaxEpisodes(String max) {
        maxEpisodesFilter = "to_episodes=" + max;
        return this;
    }
    public UserAnimeSearchFilter setEpisodes(String min, String max) {
        setMinEpisodes(min);
        setMaxEpisodes(max);
        return this;
    }
    public UserAnimeSearchFilter clearEpisodes() {
        minEpisodesFilter = "";
        maxEpisodesFilter = "";
        return this;
    }
    
    public UserAnimeSearchFilter setMinYear(String min) {
        minYearFilter = "year=" + min;
        return this;
    }
    public UserAnimeSearchFilter setMaxYear(String max) {
        maxYearFilter = "to_year=" + max;
        return this;
    }
    public UserAnimeSearchFilter setYear(String min, String max) {
        setMinYear(min);
        setMaxYear(max);
        return this;
    }
    public UserAnimeSearchFilter clearYear() {
        minYearFilter = "";
        maxYearFilter = "";
        return this;
    }
    
    public UserAnimeSearchFilter setSeason(String year, Season season) {
        seasonFilter = "season=" + season.toString().toLowerCase() + "-" + year;
        return this;
    }
    public UserAnimeSearchFilter clearSeason() {
        seasonFilter = "";
        return this;
    }
    
    public UserAnimeSearchFilter addOther(Other other) {
        otherFilter += getOther(other) + "&";
        return this;
    }
    public UserAnimeSearchFilter clearOther() {
        otherFilter = "";
        return this;
    }
    
    public UserAnimeSearchFilter setPage(String page) {
        pageFilter = "page=" + page;
        return this;
    }
    
    public UserAnimeSearchFilter clearPage() {
        pageFilter = "";
        return this;
    }
    
    public UserAnimeSearchFilter setPageRange(int min, int max) {
        isPageRange = true;
        minPage = min;
        maxPage = max;
        return this;
    }

    @Override
    public boolean isPageRange() {
        return isPageRange;
    }

    @Override
    public int getMinPage() {
        return minPage;
    }

    @Override
    public int getMaxPage() {
        return maxPage;
    }
    
    @Override
    public void setPage(int page) {
        pageFilter = "page=" + page;
    }
    
    public UserAnimeSearchFilter clearPageRange() {
        isPageRange = false;
        return this;
    }
    
    @Override
    public String getString() {
        
        if (typeFilter.endsWith(",")) {
            typeFilter = typeFilter.substring(0, typeFilter.length() - 1);
        }
        if (tagsFilter.endsWith(",")) {
            tagsFilter = tagsFilter.substring(0, tagsFilter.length() - 1);
        }
        
        return Parsers.formatIllegalSpace("users/" + userName + "/" + "anime" + filterType + "?" +
                sortType + "&" +
                sortOrder + "&" +
                nameFilter + "&" + 
                minRatingFilter + "&" +
                maxRatingFilter + "&" +
                studioFilter + "&" +
                typeFilter + "&" +
                tagsFilter + "&" +
                minEpisodesFilter + "&" +
                maxEpisodesFilter + "&" +
                minYearFilter + "&" +
                maxYearFilter + "&" +
                seasonFilter + "&" +
                otherFilter + "&" +
                pageFilter
        );
    }
    
}
