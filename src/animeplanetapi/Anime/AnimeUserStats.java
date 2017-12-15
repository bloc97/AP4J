/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package animeplanetapi.Anime;

/**
 *
 * @author bowen
 */
public class AnimeUserStats {
    private final Integer watched, watching, wantToWatch, stalled, dropped, wontWatch; 

    public AnimeUserStats(Integer watched, Integer watching, Integer wantToWatch, Integer stalled, Integer dropped, Integer wontWatch) {
        this.watched = watched;
        this.watching = watching;
        this.wantToWatch = wantToWatch;
        this.stalled = stalled;
        this.dropped = dropped;
        this.wontWatch = wontWatch;
    }

    public Integer getDropped() {
        return dropped;
    }

    public Integer getStalled() {
        return stalled;
    }

    public Integer getWantToWatch() {
        return wantToWatch;
    }

    public Integer getWatched() {
        return watched;
    }

    public Integer getWatching() {
        return watching;
    }

    public Integer getWontWatch() {
        return wontWatch;
    }
    @Override
    public String toString() {
        return getWatched() + "/" + getWatching() + "/" + getWantToWatch() + "/" + getStalled() + "/" + getDropped() + "/" + getWontWatch();
    }
}
