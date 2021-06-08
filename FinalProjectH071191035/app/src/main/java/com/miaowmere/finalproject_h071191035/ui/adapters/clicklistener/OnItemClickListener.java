package com.miaowmere.finalproject_h071191035.ui.adapters.clicklistener;

import com.miaowmere.finalproject_h071191035.data.models.Movie;
import com.miaowmere.finalproject_h071191035.data.models.TvShow;

public interface OnItemClickListener {
    void onClick(Movie movie);
    void onClick(TvShow tvShow);
}
