package com.vinaylogics.restappkotlin.listeners

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by vinaylogics on 29-05-2017.
 */

interface OnRecyclerItemClickListeners<H : RecyclerView.ViewHolder, D> {
    fun onRecyclerItemClick(h: H, v: View, d: D, pos: Int)
}
