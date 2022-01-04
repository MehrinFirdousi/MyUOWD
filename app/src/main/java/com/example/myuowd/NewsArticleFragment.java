package com.example.myuowd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsArticleFragment extends Fragment {

    NewsArticle newsArticle;
    public NewsArticleFragment(NewsArticle newsArticle) {
        this.newsArticle = newsArticle;
    }

    public static NewsArticleFragment newInstance(NewsArticle newsArticle) {
        NewsArticleFragment fragment = new NewsArticleFragment(newsArticle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_article, container, false);

        ImageView newsImage = view.findViewById(R.id.newsImage);
        TextView newsTitle = view.findViewById(R.id.newsTitle);
        TextView newsArticleText = view.findViewById(R.id.newsArticleText);
        ImageView closeArticleButton = view.findViewById(R.id.closeArticleButton);

        newsTitle.setText(newsArticle.title);
        newsArticleText.setText(newsArticle.articleText);
        newsImage.setImageResource(newsArticle.imageSrcId);

        closeArticleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return view;
    }
}