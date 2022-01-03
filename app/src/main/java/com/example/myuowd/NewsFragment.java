package com.example.myuowd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    ArrayList<NewsArticle> newsArticles;
    int newsArticleCount;

    public NewsFragment() {
        newsArticleCount = 4;
        newsArticles = new ArrayList<NewsArticle>(newsArticleCount);
    }

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // only for testing the layout with dummy text
        String title = getString(R.string.lorem_ipsum_short), articlePreviewText = getString(R.string.lorem_ipsum_medium), articleText = getString(R.string.lorem_ipsum_long), age = getString(R.string.newsAge);
        int imageSrcId = R.drawable.campus;
        NewsArticle newsArticle;
        for (int i = 0; i < newsArticleCount; i++)
        {
            newsArticle = new NewsArticle(title, imageSrcId, articlePreviewText, articleText, age);
            newsArticles.add(newsArticle);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_feed, container, false);
        LinearLayout newsFeedContainer = view.findViewById(R.id.newsFeedContainer);
        for (NewsArticle newsArticle : newsArticles) {
            LinearLayout newsArticleContainer = (LinearLayout) inflater.inflate(R.layout.news_article_container, newsFeedContainer, false);
            fillNewsArticleContent(newsArticleContainer, newsArticle);
            newsFeedContainer.addView(newsArticleContainer);
        }
        return view;
    }

    public void fillNewsArticleContent(LinearLayout newsArticleContainer, NewsArticle newsArticle) {
        TextView newsTitle = newsArticleContainer.findViewById(R.id.newsTitle);
        TextView newsPreview = newsArticleContainer.findViewById(R.id.newsPreview);
        TextView newsAge = newsArticleContainer.findViewById(R.id.newsAge);
        ImageView newsImage = newsArticleContainer.findViewById(R.id.newsImage);
        TextView readMoreLink = newsArticleContainer.findViewById(R.id.readMoreLink);
        readMoreLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsArticleFragment newsArticleFragment = new NewsArticleFragment(newsArticle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom, R.anim.enter_from_bottom, R.anim.exit_to_bottom)
                        .add(R.id.fragmentContainer, newsArticleFragment, "News Article Fragment")
                        .addToBackStack("News Article Fragment")
                        .commit();
            }
        });
        newsTitle.setText(newsArticle.title);
        newsPreview.setText(newsArticle.articlePreviewText);
        newsImage.setImageResource(newsArticle.imageSrcId);
        newsAge.setText(newsArticle.age);
    }
}