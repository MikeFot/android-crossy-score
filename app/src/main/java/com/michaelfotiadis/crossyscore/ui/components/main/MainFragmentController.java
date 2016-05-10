package com.michaelfotiadis.crossyscore.ui.components.main;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.michaelfotiadis.crossyscore.common.models.score.Score;
import com.michaelfotiadis.crossyscore.common.responses.CrossyCallback;
import com.michaelfotiadis.crossyscore.common.responses.CrossyDeliverable;
import com.michaelfotiadis.crossyscore.common.responses.CrossyError;
import com.michaelfotiadis.crossyscore.data.error.UiDataLoadError;
import com.michaelfotiadis.crossyscore.data.factory.ScoreUiWrapperFactory;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderAbstract;
import com.michaelfotiadis.crossyscore.data.loader.DataFeedLoaderCallback;
import com.michaelfotiadis.crossyscore.data.loader.ScoreLoader;
import com.michaelfotiadis.crossyscore.data.models.ScoreUiWrapper;
import com.michaelfotiadis.crossyscore.ui.components.main.recycler.ScoreUiWrapperRecyclerViewAdapter;
import com.michaelfotiadis.crossyscore.ui.core.common.controller.BaseController;
import com.michaelfotiadis.crossyscore.ui.core.common.recyclerview.manager.RecyclerManager;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.SimpleUiStateKeeper;
import com.michaelfotiadis.crossyscore.ui.core.common.viewmanagement.UiStateKeeper;
import com.michaelfotiadis.crossyscore.utils.AppLog;

import java.util.List;

/**
 *
 */
/*package*/ class MainFragmentController extends BaseController {

    private final MainFragmentViewHolder mHolder;
    private final RecyclerManager<ScoreUiWrapper> mRecyclerManager;
    private final ScoreUiWrapperFactory mFactory;

    public MainFragmentController(final Activity activity, final View view) {
        super(activity, view);

        mFactory = new ScoreUiWrapperFactory(activity);

        mHolder = new MainFragmentViewHolder(view);

        mHolder.getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                createIntentDispatcher().openCreateActivity(mHolder.getFab());
            }
        });


        // call the utilities to get the appropriate layout manager
        mHolder.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // initialise the adapter
        final ScoreUiWrapperRecyclerViewAdapter adapter = new ScoreUiWrapperRecyclerViewAdapter(getActivity(), createIntentDispatcher());

        final UiStateKeeper uiStateKeeper = new SimpleUiStateKeeper(
                view,
                mHolder.recyclerView);

        mRecyclerManager = new RecyclerManager.Builder<>(adapter)
                .setRecycler(mHolder.recyclerView)
                .setStateKeeper(uiStateKeeper)
                .setEmptyMessage(null)
                .build();

    }

    public void loadData() {

        final DataFeedLoaderAbstract<Score> scoreLoader = new ScoreLoader(getActivity());

        scoreLoader.setCallback(new DataFeedLoaderCallback<Score>() {
            @Override
            public void onError(final UiDataLoadError error) {
                AppLog.e("Failed to load scores: " + error);
            }

            @Override
            public void onSuccess(final List<Score> result) {

                mFactory.createScoreUiWrappers(result, new CrossyCallback<List<ScoreUiWrapper>>() {
                    @Override
                    public void onFailure(final CrossyError error) {
                        mRecyclerManager.setError(error.getErrorMessage());
                    }

                    @Override
                    public void onSuccess(final CrossyDeliverable<List<ScoreUiWrapper>> deliverable) {
                        if (deliverable.getContent().size() == 0) {
                            mRecyclerManager.setError("Nothing to see here yet");
                        } else {
                            mRecyclerManager.setItems(deliverable.getContent());
                        }
                    }
                });


            }
        });
        scoreLoader.loadData();
    }


}
